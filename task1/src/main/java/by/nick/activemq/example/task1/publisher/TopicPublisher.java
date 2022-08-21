package by.nick.activemq.example.task1.publisher;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;

import static by.nick.activemq.example.task1.config.TopicConstants.TOPIC_NAME;
import static java.text.MessageFormat.format;
import static javax.jms.Session.AUTO_ACKNOWLEDGE;

@Component
@RequiredArgsConstructor
@Slf4j
public class TopicPublisher {

    private final ConnectionFactory connectionFactory;
    private Connection connection;

    @PostConstruct
    public void setUpConnection() throws JMSException {
        connection = connectionFactory.createConnection();
        connection.start();
    }

    @PreDestroy
    public void tearDown() throws JMSException {
        connection.close();
    }

    public void sendTextMessage(String text) throws JMSException {
        Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
        Destination topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        producer.send(createTextMessage(session, text));
        log.info(format("Message send: [{0}]", text));
    }

    private Message createTextMessage(Session session, String text) throws JMSException {
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(text);
        return textMessage;
    }
}
