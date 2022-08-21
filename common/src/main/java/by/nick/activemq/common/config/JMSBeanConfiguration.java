package by.nick.activemq.example.task1.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import java.util.List;

@Configuration
public class JMSBeanConfiguration {

    @Bean
    public ConnectionFactory durableSubConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        activeMQConnectionFactory.setTrustedPackages(List.of("by.nick"));
        return activeMQConnectionFactory;
    }
}
