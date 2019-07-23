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

	public static final String ASIN_INFO_QUEUE = "asin.info.queue";
	public static final String ASIN_REVIEW_PAGE_INFO_QUEUE = "asin.review.page.info.queue";
	public static final String REVIEW_INFO_QUEUE = "review.info.queue";
	public static final String REVIEW_INFO_CHECK_DELETE_QUEUE = "review.info.check.delete.queue";

	public static final String ASIN_INFO_QUEUE_KEY = "asin.info.queue.key";
	public static final String ASIN_REVIEW_PAGE_INFO_QUEUE_KEY = "asin.review.page.info.queue.key";
	public static final String REVIEW_INFO_QUEUE_KEY = "review.info.queue.key";
	public static final String REVIEW_INFO_CHECK_DELETE_QUEUE_KEY = "review.info.check.delete.queue.key";

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

	@Bean("asinReviewPageInfoContainerFactory")
	public SimpleRabbitListenerContainerFactory asinReviewPageInfoContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory cf) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, cf);
		factory.setConcurrentConsumers(10);
		factory.setMaxConcurrentConsumers(25);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setPrefetchCount(1);
        return factory;
    }

	@Bean("reviewInfoContainerFactory")
	public SimpleRabbitListenerContainerFactory reviewInfoContainerFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory cf) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, cf);
		factory.setConcurrentConsumers(5);
		factory.setMaxConcurrentConsumers(10);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		factory.setPrefetchCount(1);
		return factory;
	}

	@Bean("reviewInfoCheckDeleteContainerFactory")
	public SimpleRabbitListenerContainerFactory reviewInfoCheckDeleteContainerFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory cf) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, cf);
		factory.setConcurrentConsumers(5);
		factory.setMaxConcurrentConsumers(10);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		factory.setPrefetchCount(1);
		return factory;
	}

    @Bean
	public DirectExchange reviewExchange() {
		return new DirectExchange(REVIEW_EXCHANGE);
    }

	@Bean
	public Queue asinInfoQueue() {
		return new Queue(ASIN_INFO_QUEUE);
	}

    @Bean
	public Queue asinReviewPageInfoQueue() {
		return new Queue(ASIN_REVIEW_PAGE_INFO_QUEUE);
    }

	@Bean
	public Queue reviewInfoQueue() {
		return new Queue(REVIEW_INFO_QUEUE);
	}

	@Bean
	public Queue reviewInfoCheckDeleteQueue() {
		return new Queue(REVIEW_INFO_CHECK_DELETE_QUEUE);
	}

	@Bean
	public Binding asinInfoQueueBinding() {
		return BindingBuilder.bind(asinInfoQueue()).to(reviewExchange()).with(ASIN_INFO_QUEUE_KEY);
	}

    @Bean
	public Binding asinReviewPageInfoQueueBinding() {
		return BindingBuilder.bind(asinReviewPageInfoQueue()).to(reviewExchange())
				.with(ASIN_REVIEW_PAGE_INFO_QUEUE_KEY);
    }

	@Bean
	public Binding reviewInfoQueueBinding() {
		return BindingBuilder.bind(reviewInfoQueue()).to(reviewExchange()).with(REVIEW_INFO_QUEUE_KEY);
	}

	@Bean
	public Binding reviewInfoCheckDeleteQueueBinding() {
		return BindingBuilder.bind(reviewInfoCheckDeleteQueue()).to(reviewExchange())
				.with(REVIEW_INFO_CHECK_DELETE_QUEUE_KEY);
	}
}
