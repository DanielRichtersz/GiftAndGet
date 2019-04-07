package danielrichtersz;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static danielrichtersz.RabbitMQQueues.CENTRAL_EXCHANGE;

public class RabbitMQSender {

    public static void SendBroadcast(String queueName, String message) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, false, false, false, null);

            channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
