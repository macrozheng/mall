package com.macro.mall;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.macro.mall.model.PmsProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallDemoApplicationTests {
	private Logger logger = LoggerFactory.getLogger(MallDemoApplicationTests.class);
	@Test
	public void contextLoads() {
	}

	@Test
	public void testLogStash() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		PmsProduct product = new PmsProduct();
		product.setId(1L);
		product.setName("小米手机");
		product.setBrandName("小米");
		logger.info(mapper.writeValueAsString(product));
		logger.error(mapper.writeValueAsString(product));
	}

}
