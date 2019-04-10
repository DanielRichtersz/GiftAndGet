import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import danielrichtersz.RabbitMQListener;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import static danielrichtersz.RabbitMQQueues.*;

public class Logging {

    private RabbitMQListener rabbitMQListener = new RabbitMQListener();

    public Logging() {

    }


    public void startLogging(String routingKey) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(LOGGER, "direct");
            channel.queueBind(LOGGER_QUEUE, LOGGER, routingKey);

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {

                try {
                    String message = new String(delivery.getBody(), "UTF-8");
                    Date date = new Date();
                    System.out.println(date.toString() + " [x] " + (routingKey != "" ? routingKey + " - " : "") + "Logger received: '" + message + "'");
                }
                finally {
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(LOGGER_QUEUE, false, deliverCallback, consumerTag -> { });
        }
        catch (TimeoutException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
