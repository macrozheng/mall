package com.macro.mall.search.component;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.macro.mall.search.domain.EsProduct;
import com.macro.mall.search.service.EsProductService;

@Component
public class EsProductMsgListener {

	@Autowired
	public EsProductService esProductService;
	
	@RabbitListener(queues = "${queue.es-product}")
    public void handle(Message message){
		String smsg=new String(message.getBody());
		System.out.printf("%s", smsg);
		ObjectMapper mapper=new ObjectMapper();
		EsProduct product;
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			product = mapper.readValue(smsg, EsProduct.class);
			esProductService.create(product);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
