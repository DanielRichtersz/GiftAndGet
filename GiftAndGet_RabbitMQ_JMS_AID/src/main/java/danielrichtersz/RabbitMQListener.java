package danielrichtersz;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static danielrichtersz.RabbitMQQueues.CENTRAL_EXCHANGE;

public class RabbitMQListener {

    public RabbitMQListener() {

    }

    public void Listen(String queueName, DeliverCallback deliverCallback) {
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

    public void ListenCentralExchange(String routingKey, DeliverCallback deliverCallback) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(CENTRAL_EXCHANGE, "direct");

            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, CENTRAL_EXCHANGE, routingKey);

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        }
        catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
