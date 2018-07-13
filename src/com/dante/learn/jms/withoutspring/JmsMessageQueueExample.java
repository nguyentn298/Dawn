package com.dante.learn.jms.withoutspring;

import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsMessageQueueExample {
	public static void main(String[] args) throws URISyntaxException, Exception {
		String url = "tcp://localhost:61616";
		String text = "this is a text message";
		String queueName = "danteQueueTest";
		Connection connection = null;
		
		// Use broker if dont have ActiveMQ server
		// BrokerService brokerService = BrokerFactory.createBroker(new URI("broker:(" +
		// url + ")"));
		// brokerService.start();
		try {
			

			// 1. Connection factory
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

			// 2. Create new connection from factory
			connection = connectionFactory.createConnection();

			// 3. Create session from connection
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

					// 3.1. Create queue from session
					Queue queue = session.createQueue(queueName);
		
					// 3.2. Convert output message to jms message by session
					Message msg = session.createTextMessage(text);

			// 4. Create producer and send jms message
			MessageProducer producer = session.createProducer(queue);
			
			// default active is in persistence model (messages are persisted to disk/database so that they will survive a broker restart)
//			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			producer.send(msg);

			// 5. Create consumer and receive message (Note: connection must be started to reveive message)
			MessageConsumer consumer = session.createConsumer(queue);
			
			// 5.1 Use Message listener to auto receive message (Note: set it before start connection)
			consumer.setMessageListener(new ConsumerMessageListener("danteConsumer"));
			connection.start();

			// 5.2 Not use Message listener to auto receive message
//			if (msg instanceof TextMessage) {
//				// I dont know what kind of message, so it must be convert to my kind
//				TextMessage textMsg = (TextMessage) consumer.receive();
//				System.out.println("Original Text: " + textMsg);
//				System.out.println("Get Text: " + textMsg.getText());
//			}
			
			Thread.sleep(1000);
//			session.close();
		} catch (Exception e) {

		} finally {
			if(connection != null) {
//				connection.close();
			}
		}

	}
}
