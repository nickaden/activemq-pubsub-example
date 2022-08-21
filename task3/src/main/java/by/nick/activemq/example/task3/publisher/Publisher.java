package by.nick.activemq.example.task3.publisher;

import lombok.RequiredArgsConstructor;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static by.nick.activemq.example.task3.config.VirtualTopicConstants.VIRTUAL_TOPIC_NAME;
import static java.text.MessageFormat.format;

@Component
@RequiredArgsConstructor
public class Publisher {

    private final ConnectionFactory connectionFactory;
    private Connection connection;

    @PostConstruct
    public void setUp() throws JMSException {
        connection = connectionFactory.createConnection();
        connection.start();
    }

    @PreDestroy
    public void tearDown() throws JMSException {
        connection.close();
    }

    @Scheduled(fixedDelay = 5L, timeUnit = TimeUnit.SECONDS)
    public void sendMessage() throws JMSException {
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(new ActiveMQTopic((VIRTUAL_TOPIC_NAME)));
        producer.send(session.createTextMessage(
                format("Now is {0}", DateTimeFormatter.ISO_TIME.format(LocalDateTime.now()))
        ));
    }
}
