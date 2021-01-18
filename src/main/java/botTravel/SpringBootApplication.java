package botTravel;

import org.springframework.boot.SpringApplication;
import org.telegram.telegrambots.ApiContextInitializer;


@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(SpringBootApplication.class, args);
    }

}