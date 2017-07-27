package cn.bos.access;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import cn.bos.send.msg.SendMessage;

@Component("smsConsumer1")
public class SmsConsumer1 implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		
		MapMessage mapMassage = (MapMessage)message;
		try {
			String telephone = mapMassage.getString("telephone");
			String code = mapMassage.getString("code");
			//引入阿利大于
			SendMessage.sendSms(telephone, code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
