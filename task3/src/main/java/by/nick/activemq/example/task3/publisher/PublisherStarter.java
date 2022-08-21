package by.nick.activemq.example.task3.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PublisherStarter {

    public static void main(String[] args) {
        SpringApplication.run(PublisherStarter.class);
    }
}
