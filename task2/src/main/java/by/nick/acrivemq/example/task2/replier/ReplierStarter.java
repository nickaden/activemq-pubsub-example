package by.nick.acrivemq.example.task2.replier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReplierStarter {

    public static void main(String[] args) {
        System.setProperty("server.port","8081");
        SpringApplication.run(ReplierStarter.class);
    }
}
