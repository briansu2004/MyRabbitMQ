package com.sutek.rabbitmqspringbootexample.consumer;

import com.sutek.rabbitmqspringbootexample.config.MessagingConfig;
import com.sutek.rabbitmqspringbootexample.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void ConsumeMessageFromQueue(OrderStatus orderStatus) {
		System.out.println("Message received from queue : " + orderStatus);
	}
}
