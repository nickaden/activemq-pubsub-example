package by.nick.activemq.example.task1.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.text.MessageFormat.format;

@Component
@RequiredArgsConstructor
public class TopicMessageScheduler {

    private static final Integer INIT_VALUE = 0;
    private final AtomicInteger counter = new AtomicInteger(INIT_VALUE);
    private final TopicPublisher topicPublisher;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void sendBySchedule() throws JMSException {
        topicPublisher.sendTextMessage(format("Counter: initial value - {0}, current value - {1}",
                INIT_VALUE, counter.getAndIncrement()));
    }
}
