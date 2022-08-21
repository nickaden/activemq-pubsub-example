package by.nick.acrivemq.example.task2.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SenderStarter {

    public static void main(String[] args) {
        System.setProperty("server.port","8082");
        SpringApplication.run(SenderStarter.class);
    }
}
