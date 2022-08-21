# Task 1: Pub/Sub model with Durable subsctiption

The purpose of task is to demonstrate difference between durable/non-durable subscription on specific topic

# Steps to reproduce

1. Install ActiveMQ classic and run on port 61616
2. Run `by.nick.activemq.example.task1.durablesubscriber.DurableSubscriberStarter` to create durable subscription
3. Run `by.nick.activemq.example.task1.subscriber.SubscriberStarter` to create non-durable subscription
4. Run `by.nick.activemq.example.task1.publisher.PublisherStarter` to start message publishing
5. Now you're able to see received messages by each subscriber in log and created log files (`durableLog.txt`, `nonDurableLog.txt`)
6. Terminate DurableSubscriber
7. Terminate Subscriber
8. Run them again
9. Wait for a while and terminate Publisher
10. Check log files: there should be a counting gap in `nonDurableLog.txt` and shouldn't in `durableLog.txt`