package by.nick.activemq.example.task1.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PublisherStarter {

    public static void main(String[] args) {
        System.setProperty("server.port", "8081");
        SpringApplication.run(PublisherStarter.class);
    }
}
