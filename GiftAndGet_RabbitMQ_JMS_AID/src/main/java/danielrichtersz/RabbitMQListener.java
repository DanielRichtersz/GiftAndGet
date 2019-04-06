package danielrichtersz;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQListener {

    public static void Listen(String queueName, DeliverCallback deliverCallback) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(queueName, false, false, false, null);
            System.out.println(" [*] Waiting for messages.");

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        }
        catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
