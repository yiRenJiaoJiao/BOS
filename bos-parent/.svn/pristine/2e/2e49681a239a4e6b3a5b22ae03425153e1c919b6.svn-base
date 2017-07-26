package cn.bos.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
@Component("queueReceiver1")
public class MsgConsumer implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("consumer接收到信息"+((TextMessage)message).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
