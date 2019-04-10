package danielrichtersz;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.Date;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static danielrichtersz.RabbitMQQueues.*;

public class RabbitMQSender {

    public void SendToLogger(String message) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(LOGGER, "direct");
            channel.queueDeclare(LOGGER_QUEUE, true, false, false, null);

            channel.basicPublish(LOGGER, "",  MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            //System.out.println(" [x] Sent to logger: '" + message + "'" );
        }
        catch (TimeoutException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SendBroadcast(String exchangeName, String message) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();

             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(exchangeName, "fanout");

            channel.basicPublish(exchangeName, "", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
        catch (TimeoutException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        SendToLogger(message);
    }
}
