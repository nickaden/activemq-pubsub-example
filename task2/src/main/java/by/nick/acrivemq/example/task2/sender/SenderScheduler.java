package by.nick.acrivemq.example.task2.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class SenderScheduler {

    private static final List<String> NAMES = List.of("Alex", "Michael", "Nick", "George");
    Random random = new Random(Instant.now().getEpochSecond());
    private final Sender sender;

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void scheduleSend() throws JMSException {
        sender.send("Hello " + NAMES.get(random.nextInt(4)));
    }
}
