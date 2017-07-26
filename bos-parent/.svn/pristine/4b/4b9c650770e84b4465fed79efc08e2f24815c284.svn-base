package cn.bos.listener;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;
@Component
public class QueueReceiver3 implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println(((MapMessage)message).getString("company"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
