package danielrichtersz;

import com.rabbitmq.client.DeliverCallback;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import danielrichtersz.jms.CentralExchange;
import danielrichtersz.repositories.UserRepository;
import danielrichtersz.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static danielrichtersz.RabbitMQQueues.CENTRAL_EXCHANGE;

@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args -> {
            CentralExchange centralExchange = new CentralExchange();
            centralExchange.startExchange();

        });
    }
}
