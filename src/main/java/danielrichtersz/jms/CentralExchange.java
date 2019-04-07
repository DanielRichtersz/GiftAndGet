package danielrichtersz.jms;

import com.rabbitmq.client.DeliverCallback;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import danielrichtersz.RabbitMQListener;

import static danielrichtersz.RabbitMQQueues.CENTRAL_EXCHANGE;

public class CentralExchange {

    private RabbitMQListener rabbitMQListener = new RabbitMQListener();

    private final DeliverCallback CentralExchange_GetAvailableActionGroups = (consumerTag, delivery) -> {
        String json = new String(delivery.getBody(), "UTF-8");
        System.out.println(" [x] Received '" + json + "'");

        try {

            //TODO: Send to client
        }
        catch (SerializationException e) {
            e.printStackTrace();
        }
    };

    public CentralExchange() {

    }

    public void startExchange() {
        //Central Exchange: Handles incoming orders from clients
        rabbitMQListener.Listen(CENTRAL_EXCHANGE, CentralExchange_GetAvailableActionGroups);

    }
}
