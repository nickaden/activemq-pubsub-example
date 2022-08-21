package by.nick.activemq.example.task1.subscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SubscriberStarter {

    public static void main(String[] args) {
        System.setProperty("server.port", "8082");
        SpringApplication.run(SubscriberStarter.class);
    }
}
