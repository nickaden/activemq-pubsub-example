package by.nick.activemq.example.task1.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
@Slf4j
public class TopicMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            log.info("Message received: [{}]", ((TextMessage) message).getText());
        } catch (JMSException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
