package com.example.storeapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    public static final String QUEUE = "order_queue";
    public static final String EXCHANGE= "order_exchange";
    public static final String ROUTING_KEY= "order_routingKey";
    public static final String PAYMENT_QUEUE = "payment_queue";
    public static final String PAYMENT_ROUTING_KEY= "payment_routingKey";

    @Bean
    public Queue orderQueue (){
        return new Queue(QUEUE);
    }

    @Bean
    public Queue paymentQueue (){
        return new Queue(PAYMENT_QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingOrder(TopicExchange exchange){
        return BindingBuilder.bind(orderQueue()).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingPayment(TopicExchange exchange){
        return BindingBuilder.bind(paymentQueue()).to(exchange).with(PAYMENT_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template (ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
