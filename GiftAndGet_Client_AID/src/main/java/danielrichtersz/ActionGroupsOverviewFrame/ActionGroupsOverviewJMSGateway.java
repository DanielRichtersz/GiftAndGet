package danielrichtersz.ActionGroupsOverviewFrame;

import com.owlike.genson.Genson;
import com.rabbitmq.client.*;
import danielrichtersz.RabbitMQListener;
import danielrichtersz.RabbitMQSender;
import danielrichtersz.models.ActionGroup;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static danielrichtersz.RabbitMQQueues.*;
import static danielrichtersz.RabbitMQQueues.LOGGER_QUEUE;

public class ActionGroupsOverviewJMSGateway {

    private String loggedInUsername;
    private RabbitMQListener rabbitMQListener = new RabbitMQListener();
    private RabbitMQSender rabbitMQSender = new RabbitMQSender();
    private Genson genson = new Genson();

    public ActionGroupsOverviewJMSGateway(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
    }

    public void getAvailableGroups() {
        sendAvailableGroupsRequest();
        listenForAvailableGroupsAnswer();
    }

    private void sendAvailableGroupsRequest() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();

             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(CENTRAL_EXCHANGE_REQUESTS, "direct");
            channel.queueDeclare(CE_AVAILABLE_ACTIONGROUPS_QUEUE, true, false, false, null);

            channel.basicPublish(CENTRAL_EXCHANGE_REQUESTS, CE_AVAILABLE_ACTIONGROUPS_QUEUE, MessageProperties.PERSISTENT_TEXT_PLAIN, this.loggedInUsername.getBytes("UTF-8"));

            String logMessage =  " [x] User '" + this.loggedInUsername + "' has sent a request for available action groups";
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

    private void listenForAvailableGroupsAnswer() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(CENTRAL_EXCHANGE_REQUESTS, "direct");

            final String USER_QUEUE = CE_AVAILABLE_ACTIONGROUPS_QUEUE + "_" + this.loggedInUsername;
            channel.queueDeclare(USER_QUEUE, true, false, false, null);
            channel.queueBind(USER_QUEUE, CENTRAL_EXCHANGE_REQUESTS, USER_QUEUE);

            System.out.println(" [*] Waiting for reply of type 'AvailableGroups. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                try {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println(" [x] " + USER_QUEUE + " - Reply of type 'AvailableGroups' received: '" + message + "'");

                    List<ActionGroup> availableActionGroups = genson.deserialize(message, List.class);
                }
                finally {
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(USER_QUEUE, false, deliverCallback, consumerTag -> { });
        }
        catch (TimeoutException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
