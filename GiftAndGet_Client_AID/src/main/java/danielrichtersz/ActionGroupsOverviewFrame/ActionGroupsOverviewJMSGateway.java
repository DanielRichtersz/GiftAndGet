package danielrichtersz.ActionGroupsOverviewFrame;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import danielrichtersz.RabbitMQListener;
import danielrichtersz.RabbitMQSender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static danielrichtersz.RabbitMQQueues.CENTRAL_EXCHANGE;

public class ActionGroupsOverviewJMSGateway {

    public ActionGroupsOverviewJMSGateway() {

    }

    public void getAvailableGroups() {
        listenForNewGroups();
        RabbitMQSender.SendBroadcast(CENTRAL_EXCHANGE, "clientSpecificId");
    }

    private void listenForNewGroups() {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String json = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] New available ActionGroup received '" + json + "'");

            try {

                //TODO: Send to client
            }
            catch (SerializationException e) {
                e.printStackTrace();
            }
        };
        //RabbitMQListener rabbitMQListener = new RabbitMQListener();
        //rabbitMQListener.Listen(CENTRAL_EXCHANGE,deliverCallback);
        this.Listen(CENTRAL_EXCHANGE, deliverCallback);
    }

    private void Listen(String queueName, DeliverCallback deliverCallback) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(queueName, true, false, false, null);
            System.out.println(" [*] Waiting for messages.");

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        }
        catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
