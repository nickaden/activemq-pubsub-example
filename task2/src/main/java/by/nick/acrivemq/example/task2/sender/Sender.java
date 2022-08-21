package by.nick.acrivemq.example.task2.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;
import java.util.UUID;

import static by.nick.acrivemq.example.task2.config.JMSConstants.REPLY_QUEUE;
import static by.nick.acrivemq.example.task2.config.JMSConstants.SENDER_QUEUE;

@Component
@RequiredArgsConstructor
@Slf4j
public class Sender implements MessageListener {

    private final static Destination REPLY_DESTINATION = new ActiveMQQueue(REPLY_QUEUE);
    private final ConnectionFactory connectionFactory;
    private Connection connection;

    @PostConstruct
    public void setUpReplyListener() throws JMSException {
        connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(REPLY_DESTINATION);
        consumer.setMessageListener(this);
    }

    @PreDestroy
    public void tearDown() throws JMSException {
        connection.close();
    }

    public void send(String messageToSend) throws JMSException {
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(new ActiveMQQueue(SENDER_QUEUE));
        TextMessage textMessage = session.createTextMessage(messageToSend);
        textMessage.setJMSReplyTo(REPLY_DESTINATION);
        textMessage.setJMSCorrelationID(UUID.randomUUID().toString());

        producer.send(textMessage);
        log.info("Message sent: CorrelationID: {}, Text: {}", textMessage.getJMSCorrelationID(), textMessage.getText());
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            log.info("Message replied: CorrelationID: {}, Text: {}", message.getJMSCorrelationID(), textMessage.getText());
        } catch (JMSException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
