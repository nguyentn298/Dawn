package com.dante.learn.jms.withoutspring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {

	String consumerName;
	public ConsumerMessageListener(String consumerName) {
		this.consumerName = consumerName;
	}
	@Override
	public void onMessage(Message msg) {
		TextMessage textMsg = null;
			if (msg instanceof TextMessage) {
				textMsg = (TextMessage) msg;
			}
			try {
				System.out.println(consumerName + "receive: " + textMsg.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
