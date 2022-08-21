package by.nick.activemq.example.task1.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@RequiredArgsConstructor
@Slf4j
public class TopicMessageListener implements MessageListener {

    private final String filePath;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            log.info("Message received [{}]", textMessage.getText());
            Files.writeString(Path.of(filePath), textMessage.getText() + "\n",
                    StandardOpenOption.APPEND, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (JMSException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
