package by.nick.activemq.example.task1.durablesubscriber;

import by.nick.activemq.example.task1.listener.TopicMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;

import static by.nick.activemq.example.task1.config.TopicConstants.TOPIC_NAME;

@Component
@RequiredArgsConstructor
@Slf4j
public class DurableTopicSubscriber {

    private final ConnectionFactory connectionFactory;
    private Connection connection;

    @PostConstruct
    public void setUp() throws JMSException {
        connection = connectionFactory.createConnection();
        connection.setClientID("DurableSubscriber");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageConsumer consumer = session.createDurableSubscriber(topic, "VeryDurable");
        consumer.setMessageListener(new TopicMessageListener("durableLog.txt"));
    }

    @PreDestroy
    public void tearDown() throws JMSException {
        connection.close();
    }
}
