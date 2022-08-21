package by.nick.acrivemq.example.task2.replier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;

import static by.nick.acrivemq.example.task2.config.JMSConstants.SENDER_QUEUE;

@Component
@RequiredArgsConstructor
@Slf4j
public class Replier implements MessageListener {

    private final ConnectionFactory connectionFactory;
    private Connection connection;

    @PostConstruct
    public void initListener() throws JMSException {
        connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(new ActiveMQQueue(SENDER_QUEUE));
        consumer.setMessageListener(this);
    }

    @PreDestroy
    public void tearDown() throws JMSException {
        connection.close();
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            Session replySession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = replySession.createProducer(message.getJMSReplyTo());
            TextMessage replyMessage = createReplyMessage(textMessage, replySession);

            producer.send(replyMessage);

        } catch (JMSException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private TextMessage createReplyMessage(TextMessage textMessage, Session replySession) throws JMSException {
        TextMessage replyMessage = replySession.createTextMessage();

        if ("Hello Nick".equals(textMessage.getText())) {
            replyMessage.setText("Hello Mike");
        } else {
            replyMessage.setText("I don't know who you are... Get out!");
        }
        replyMessage.setJMSCorrelationID(textMessage.getJMSCorrelationID());
        return replyMessage;
    }
}
