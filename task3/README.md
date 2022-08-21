# Task 3: Consumer scaling

The purpose of task is to demonstrate opportunity to scale queue consumers with creating virtual topic

# Steps to reproduce

1. Install ActiveMQ classic and run on port 61616
2. Run `by.nick.activemq.example.task3.publisher.PublisherStarter` to start publishing
3. Run `by.nick.activemq.example.task3.subscriber.SubscriberStarter` with following JMV params: `-Dactivemq.consumer.name={name_you_like} -Dserver.port=8081`
4. Run `by.nick.activemq.example.task3.subscriber.SubscriberStarter` once more with following JMV params: `-Dactivemq.consumer.name={another_name} -Dserver.port=8082` 
5. You can see logs of both subscribers that they're receiving equal messages