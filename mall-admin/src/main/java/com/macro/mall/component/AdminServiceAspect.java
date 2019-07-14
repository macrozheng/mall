package com.macro.mall.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class AdminServiceAspect {
	private final String queueName="com.macro.mall.service.PmsProductService";
	private final String exchange="com.macro.mall.service";

    @Autowired
	public RabbitTemplate rabbitTemplate;
    
	@After("execution(public * com.macro.mall.service.PmsProductService.create(..))")
	public void sendAmqpMsg(JoinPoint joinPoint) throws Throwable {
		System.out.println("AdminServiceAspect: "+joinPoint.toString());
		ObjectMapper mapper=new ObjectMapper();
		String arg=mapper.writeValueAsString(joinPoint.getArgs()[0]);
		this.rabbitTemplate.convertAndSend(exchange,  this.exchange+"#", arg);
	}
}
