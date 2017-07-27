package com.dante.learn.jms.multiAMQ;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dante.config.ProfileType;
import com.dante.test.TestApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestApplicationContext.class })
@ActiveProfiles(ProfileType.TEST)
public class TestAMQ {
	
	@Autowired
	private MyProducer producer;
	
	@Autowired
	private MyListener listener;
	
	private static AtomicInteger id = new AtomicInteger();
	
	public static Product getProduct() {
		Product p = new Product();
		p.setName("Product " + id.incrementAndGet());
		p.setProductId(getUniqueId());
		p.setQuantity(2);
		
		return p;
	}
	
	public static String getUniqueId(){
        return UUID.randomUUID().toString();
    }
	
	@Test
	public void testSendMessage() {
		/*
		 * Check defined bean
		 */
//		AbstractApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationContext.class);
//		String[] myBeans = context.getBeanDefinitionNames();
//		for (String string : myBeans) {
//			System.out.println("My bean: " + string);
//		}
		
		producer.sendMessage("message-send-from-producer", "{'name': 'nguyen6'}");
		
	}
	
}
