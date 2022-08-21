package by.nick.activemq.example.task1.subscriber;

import by.nick.activemq.example.task1.config.JMSBeanConfiguration;
import by.nick.activemq.example.task1.listener.TopicMessageListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JMSBeanConfiguration.class, TopicMessageListener.class})
public class SubscriberStarter {

    public static void main(String[] args) {
        System.setProperty("server.port", "8082");
        SpringApplication.run(SubscriberStarter.class);
    }
}
