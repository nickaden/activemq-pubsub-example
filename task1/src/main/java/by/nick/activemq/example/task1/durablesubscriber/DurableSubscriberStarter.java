package by.nick.activemq.example.task1.durablesubscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DurableSubscriberStarter {

    public static void main(String[] args) {
        System.setProperty("server.port", "8083");
        SpringApplication.run(DurableSubscriberStarter.class);
    }
}
