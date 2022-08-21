package by.nick.activemq.example.task3.subscriber;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;

import static by.nick.activemq.example.task3.config.VirtualTopicConstants.CONSUMER_NAME_FORMAT;
import static java.text.MessageFormat.format;

@Component
@RequiredArgsConstructor
@Slf4j
public class Subscriber implements MessageListener{

    private final ConnectionFactory connectionFactory;
    @Value("${activemq.consumer.name}")
    private String consumerName;
    private Connection connection;

    @PostConstruct
    public void setUp() throws JMSException {
        connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        String fullConsumerName = format(CONSUMER_NAME_FORMAT, consumerName);
        log.info(format("Create consumer {0}", fullConsumerName));
        MessageConsumer consumer = session.createConsumer(session.createQueue(fullConsumerName));
        consumer.setMessageListener(this);
    }

    @PreDestroy
    public void tearDown() throws JMSException {
        connection.close();
    }

    @Override
    @SneakyThrows
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        log.info("Message received: {}", textMessage.getText());
    }
}
