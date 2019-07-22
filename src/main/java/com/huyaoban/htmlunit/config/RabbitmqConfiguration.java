package com.huyaoban.htmlunit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {
	public static final String REVIEW_EXCHANGE = "review.exchange";
	public static final String REVIEW_ASIN_INFO_QUEUE = "review.asin.info.queue";
	public static final String REVIEW_PAGE_INFO_QUEUE = "review.page.info.queue";
	public static final String REVIEW_SINGLE_QUEUE = "review.single.queue";
	public static final String REVIEW_ASIN_INFO_QUEUE_ROUTING_KEY = "review.asin.info.queue.key";
	public static final String REVIEW_PAGE_INFO_QUEUE_ROUTING_KEY = "review.page.info.queue.key";
	public static final String REVIEW_SINGLE_QUEUE_ROUTING_KEY = "review.single.queue.key";

    @Bean("rabbitTemplate")
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf) {
        RabbitTemplate template = new RabbitTemplate(cf);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
		template.setExchange(REVIEW_EXCHANGE);

        return template;
    }

	@Bean("asinInfoContainerFactory")
	public SimpleRabbitListenerContainerFactory asinInfoContainerFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory cf) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, cf);
		factory.setConcurrentConsumers(5);
		factory.setMaxConcurrentConsumers(10);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		factory.setPrefetchCount(1);
		return factory;
	}

	@Bean("reviewPageContainerFactory")
	public SimpleRabbitListenerContainerFactory reviewPageContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory cf) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, cf);
		factory.setConcurrentConsumers(3);
		factory.setMaxConcurrentConsumers(5);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setPrefetchCount(1);
        return factory;
    }

	@Bean("singleReviewContainerFactory")
	public SimpleRabbitListenerContainerFactory singleReviewContainerFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory cf) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, cf);
		factory.setConcurrentConsumers(3);
		factory.setMaxConcurrentConsumers(5);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		factory.setPrefetchCount(1);
		return factory;
	}

    @Bean
	public DirectExchange reviewExchange() {
		return new DirectExchange(REVIEW_EXCHANGE);
    }

	@Bean
	public Queue reviewAsinInfoQueue() {
		return new Queue(REVIEW_ASIN_INFO_QUEUE);
	}

    @Bean
	public Queue reviewPageInfoQueue() {
		return new Queue(REVIEW_PAGE_INFO_QUEUE);
    }

	@Bean
	public Queue singleReviewInfoQueue() {
		return new Queue(REVIEW_SINGLE_QUEUE);
	}

	@Bean
	public Binding reviewAsinInfoQueueBinding() {
		return BindingBuilder.bind(reviewAsinInfoQueue()).to(reviewExchange()).with(REVIEW_ASIN_INFO_QUEUE_ROUTING_KEY);
	}

    @Bean
	public Binding reviewPageInfoQueueBinding() {
		return BindingBuilder.bind(reviewPageInfoQueue()).to(reviewExchange()).with(REVIEW_PAGE_INFO_QUEUE_ROUTING_KEY);
    }

	@Bean
	public Binding singleReviewInfoQueueBinding() {
		return BindingBuilder.bind(singleReviewInfoQueue()).to(reviewExchange()).with(REVIEW_SINGLE_QUEUE_ROUTING_KEY);
	}
}
