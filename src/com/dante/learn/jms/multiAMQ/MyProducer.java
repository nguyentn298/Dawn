package com.dante.learn.jms.multiAMQ;

import java.io.Serializable;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MyProducer {

	@Autowired
	JmsTemplate jmsTemplate;

	static final Logger LOG = LoggerFactory.getLogger(MyProducer.class);
//	public void sendMessage(final String queueName, final Product product) {
//		System.out.println("Begin send message!!");
//		jmsTemplate.send(queueName, new MessageCreator() {
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				ObjectMessage objectMessage = session.createObjectMessage(product);
//				return objectMessage;
//			}
//		});
//		System.out.println("Finished send message!!");
//	}
	
	public void sendMessage(final String queueName, final Object content) {
		LOG.info("====== Begin sendMessage ========");
		System.out.println("Sending message: " + content + " to queue: " + queueName);
		jmsTemplate.send(queueName, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				Message message = null;
				
				
				if (content instanceof String) {
					message = session.createTextMessage((String) content);
				} else if (content instanceof byte[]) {
					message = session.createBytesMessage();
					byte[] contentBytes = (byte[]) content;
					((BytesMessage) message).writeBytes(contentBytes);
				} else if (content instanceof Serializable) {
					message = session.createObjectMessage();
					((ObjectMessage)message).setObject((Serializable)content);
				} else {
					throw new RuntimeException("Non-supported content type found: " + content.getClass().getName());
				}
				
				System.out.println("Message of producer: " + message + " to queue: " + queueName);
				return message;
			}
		});
		LOG.info("====== Finished sendMessage ========");
	}


}