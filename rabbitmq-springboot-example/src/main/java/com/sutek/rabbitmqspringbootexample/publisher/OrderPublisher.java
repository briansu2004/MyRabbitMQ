package com.sutek.rabbitmqspringbootexample.publisher;

import com.sutek.rabbitmqspringbootexample.config.MessagingConfig;
import com.sutek.rabbitmqspringbootexample.dto.Order;
import com.sutek.rabbitmqspringbootexample.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

	public static final String ORDER_SUCCESS = "SUCCESS";

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostMapping("/{restaurantName}")
	public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
		order.setOrderId(UUID.randomUUID().toString());
		// restaurant_service
		// payment_service
		OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed successfully in " + restaurantName);
		rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);

		return ORDER_SUCCESS;
	}
}
