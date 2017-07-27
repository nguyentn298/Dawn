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
public class MyListener {

	@Autowired
	private MyProducer producer;

	static final Logger LOG = LoggerFactory.getLogger(MyListener.class);

//	@JmsListener(destination = "message-send-from-producer-test")
//	public void receiveMessage(final Message<Product> message) throws JMSException {
//		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		MessageHeaders headers = message.getHeaders();
//		LOG.info("Application : headers received : {}", headers);
//
//		Product product = message.getPayload();
//		LOG.info("Application : product received : {}", product);
//		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
//
//		 producer.sendMessage("message-send-from-listener", product);
//	}

	@JmsListener(destination = "message-send-from-producer")
	public void receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		LOG.info("====== Begin receiveMessage ========");
		System.out.println("Received message " + jsonMessage);
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			
		}
		System.out.println("Received messageData " + messageData);
		producer.sendMessage("message-send-from-listener", messageData);
		LOG.info("====== Finished receiveMessage ========");
	}
	
}
