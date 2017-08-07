package com.dante.learn.jms.multiAMQ;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer2 {

		@Autowired
		private MyProducer producer;
		
		final Logger log = LoggerFactory.getLogger(MyConsumer2.class);
		
		@JmsListener(destination = "MyConsumer2-receive")
		public void receiveMessage(final Message message) throws JMSException {
			log.info("=====================================================");
			log.info("MyConsumer2's starting receive message from producer");
				String messageData = null;
					if(message instanceof TextMessage) {
						messageData = ((TextMessage) message).getText();
					}
				log.info("My received message of MyConsumer2: " + messageData);
			
			log.info("MyConsumer2 finished receive message from producer");
			
			log.info("MyConsumer2's starting send message to MyConsumer3");
			
				producer.sendMessage("MyConsumer3", "are you MyConsumer3 ?");
			
			log.info("MyConsumer2 finished send message from MyConsumer3");
			log.info("=====================================================");
		}
}
