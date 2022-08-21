package by.nick.activemq.example.task1.subscriber;

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
public class TopicSubscriber {

    private final ConnectionFactory connectionFactory;
    private final TopicMessageListener messageListener;
    private Connection connection;

    @PostConstruct
    public void setUp() throws JMSException {
        connection = connectionFactory.createConnection();
        connection.setClientID("NonDurableClient");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination topic = session.createTopic(TOPIC_NAME);
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(messageListener);
    }

    @PreDestroy
    public void tearDown() throws JMSException {
        connection.close();
    }
}
