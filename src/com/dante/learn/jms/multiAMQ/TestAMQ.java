package com.dante.learn.jms.multiAMQ;

import java.util.UUID;

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
	
	public static String getUniqueId() {
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
		
		producer.sendMessage("MyConsumer-receive", "are you MyConsumer1 ?");
		producer.sendMessage("MyConsumer2-receive", "are you MyConsumer2 ?");
		
	}
	
}
