package danielrichtersz;

import danielrichtersz.models.User;
import danielrichtersz.repositories.UserRepository;
import danielrichtersz.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            log.info("--------------------------------------");
            log.info("Application running");
            log.info("--------------------------------------");
            log.info("");

            log.info("--------------------------------------");
            log.info("Creating and saving user");
            userRepository.save(new User("1@mail.com", "username1", "password1"));
            userRepository.save(new User("2@mail.com", "username2", "password2"));
            userRepository.save(new User("3@mail.com", "username3", "password3"));
            userRepository.save(new User("4@mail.com", "username4", "password4"));
            log.info("--------------------------------------");
            log.info("");

            log.info("--------------------------------------");
            log.info("Retrieving all users from repository");
            userRepository.findAll().forEach(user -> {
                log.info(user.toString());
                log.info("");
            });
            log.info("--------------------------------------");
            log.info("");

            log.info("--------------------------------------");
            log.info("Testing @Autowired userService, saving user through service");
            userService.createOrUpdateUser("5@mail.com", "username5", "password5");
            log.info("Created user, now retrieving all users to check if it is in the list");
            log.info("--------------------------------------");
            userRepository.findAll().forEach(user -> {
                log.info(user.toString());
                log.info("");
            });
            log.info("--------------------------------------");
            log.info("");

        });
    }
}
