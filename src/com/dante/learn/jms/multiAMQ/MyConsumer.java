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
public class MyConsumer {

	@Autowired
	private MyProducer producer;

	static final Logger log = LoggerFactory.getLogger(MyConsumer.class);

	@JmsListener(destination = "MyConsumer-receive")
	public void receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		log.info("=====================================================");
		
		log.info("MyConsumer1's starting receive message from producer");
		
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
		}
			log.info("My received message of MyConsumer1: " + messageData);
		log.info("MyConsumer1 finished receive message from producer");
		
		log.info("MyConsumer1's starting send message to MyConsumer9");
		
			producer.sendMessage("MyConsumer9", "Are you MyConsumer9 ?");
		
		log.info("MyConsumer1 finished send message from MyConsumer9");
		
		log.info("=====================================================");
	}
	
	public static void main(String[] args) {
		log.info("====== test11 ========");
	}
}
