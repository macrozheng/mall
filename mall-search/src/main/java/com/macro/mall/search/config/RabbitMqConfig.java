package com.macro.mall.search.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.client.Channel;

@Configuration
public class RabbitMqConfig {

	@Value("${spring.rabbitmq.host}:${spring.rabbitmq.port}")
	public String rabbitHost;
	@Value("${spring.rabbitmq.username}")
	public String rabbitUserName;
	@Value("${spring.rabbitmq.password}")
	public String rabbitPassword;
	
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        return new RabbitTemplate(factory);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
    	CachingConnectionFactory conn = new CachingConnectionFactory();
    	conn.setAddresses(this.rabbitHost);
    	conn.setUsername(this.rabbitUserName);
    	conn.setPassword(this.rabbitPassword);
    	return conn;
    }

  @Bean
  Channel getChannel(ConnectionFactory factory) {
  	Connection conn=factory.createConnection();
  	
  	Channel channel=conn.createChannel(false);

  	return channel;
  }
}
