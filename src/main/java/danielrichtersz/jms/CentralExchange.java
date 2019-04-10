package danielrichtersz.jms;

import com.owlike.genson.Genson;
import com.rabbitmq.client.*;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import danielrichtersz.RabbitMQListener;
import danielrichtersz.RabbitMQSender;
import danielrichtersz.models.ActionGroup;
import danielrichtersz.services.interfaces.ActionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static danielrichtersz.RabbitMQQueues.*;

@Service
public class CentralExchange {

    private RabbitMQListener rabbitMQListener = new RabbitMQListener();
    private RabbitMQSender rabbitMQSender = new RabbitMQSender();

    @Autowired
    private ActionGroupService actionGroupService;

    public CentralExchange() {
        this.startExchange();
        this.listenForAvailableGroupsRequests();
    }

    private void startExchange() {
        //Central Exchange: Handles incoming orders from clients
        DeliverCallback generalDeliveryCallback = (consumerTag, delivery) -> {
            String json = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Central exchange received '" + json + "'");

            try {
                //TODO: Send available actiongroups
            }
            catch (SerializationException e) {
                e.printStackTrace();
            }
            finally {
                System.out.println(" [x] Sent back available groups");
                //channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(CENTRAL_EXCHANGE_REQUESTS, "direct");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, CENTRAL_EXCHANGE_REQUESTS, "");

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            channel.basicConsume(queueName, true, generalDeliveryCallback, consumerTag -> { });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    private void listenForAvailableGroupsRequests() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(CENTRAL_EXCHANGE_REQUESTS, "direct");
            channel.queueDeclare(CE_AVAILABLE_ACTIONGROUPS_QUEUE, true, false, false, null);
            channel.queueBind(CE_AVAILABLE_ACTIONGROUPS_QUEUE, CENTRAL_EXCHANGE_REQUESTS, CE_AVAILABLE_ACTIONGROUPS_QUEUE);

            System.out.println(" [*] Waiting for requests of type 'AvailableGroups'. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {

                try {
                    String username = new String(delivery.getBody(), "UTF-8");
                    System.out.println(" [x] Received new request of type 'AvailableGroups' by: '" + username + "'");
                    this.sendAvailableGroupsReply(username);
                }
                finally {
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(CE_AVAILABLE_ACTIONGROUPS_QUEUE, false, deliverCallback, consumerTag -> { });
        }
        catch (TimeoutException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendAvailableGroupsReply(String username) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();

             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(CENTRAL_EXCHANGE_REQUESTS, "direct");
            channel.queueDeclare(CE_AVAILABLE_ACTIONGROUPS_QUEUE + "_" + username, true, false, false, null);

            List<ActionGroup> actionGroups = actionGroupService.getAvailableActionGroups();
            Genson genson = new Genson();
            String allAvailableGroups = genson.serialize(actionGroups);

            channel.basicPublish(CENTRAL_EXCHANGE_REQUESTS, CE_AVAILABLE_ACTIONGROUPS_QUEUE + "_" + username, MessageProperties.PERSISTENT_TEXT_PLAIN, allAvailableGroups.getBytes("UTF-8"));

            String logMessage =  " [x] User '" + username + "' has been sent a reply with all available action groups";
            System.out.println(logMessage);
            rabbitMQSender.SendToLogger(logMessage);
        }
        catch (TimeoutException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
