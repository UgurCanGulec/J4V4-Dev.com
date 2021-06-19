package com.j4v4developers.rabbitmq.listener;

import com.j4v4developers.rabbitmq.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @RabbitListener(queues = "j4v4-dev-queue") //Bir mesaj geldiğinde rabbit listener tetiklensin
    public void handleMessage(Notification notification) {
        System.out.println("Mesaj alındı :)");
        System.out.println(notification.toString());
    }
}
