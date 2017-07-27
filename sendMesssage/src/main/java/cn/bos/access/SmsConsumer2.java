package cn.bos.access;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.aliyuncs.exceptions.ClientException;

import cn.bos.send.msg.SendMessage;


@Component("smsConsumer2")
public class SmsConsumer2 implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		
		MapMessage mapMassage = (MapMessage)message;
		try {
			String telephone = mapMassage.getString("telephone");
			String code = mapMassage.getString("customer");
			String time = mapMassage.getString("time");
			//引入阿利大于
			SendMessage.sendSms(telephone, code,time);
			
			
			//SendMessage.sendSms(telephone, code);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
