# Task 2: Request/Reply model

The purpose of task is to demonstrate opportunity to create non-blocking request-reply communication with message broker

# Steps to reproduce

1. Install ActiveMQ classic and run on port 61616
2. Run `by.nick.acrivemq.example.task2.replier.ReplierStarter` to register replier
3. Run `by.nick.acrivemq.example.task2.sender.SenderStarter` to start sending messages
4. You can see `Message sent` and `Message replied` logs in Sender application. See correlationID to couple them together