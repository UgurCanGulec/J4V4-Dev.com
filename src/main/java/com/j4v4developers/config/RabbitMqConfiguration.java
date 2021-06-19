package com.j4v4developers.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${sr.rabbit.queue.name}")
    private String queueName;
    @Value("${sr.rabbit.routing.name}")
    private String routingName;
    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;
    /**
     * Construct a new queue, given a name, durability, exclusive and auto-delete flags.
     *  "name" the name of the queue.
     *  "durable" true if we are declaring a durable queue (the queue will survive a server restart)
     *  "exclusive" true if we are declaring an exclusive queue (the queue will only be used by the declarer's
     * connection)
     *  "autoDelete" true if the server should delete the queue when it is no longer in use
     */

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(routingName);
    }



}
