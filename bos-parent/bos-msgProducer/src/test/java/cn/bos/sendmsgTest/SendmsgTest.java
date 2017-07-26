package cn.bos.sendmsgTest;

import java.util.HashMap;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.hibernate.mapping.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bos.sendmsg.SendMsg;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-producer.xml")
public class SendmsgTest {
	@Autowired
	private SendMsg sendMsg;
	@Test
	public void test(){
		/*for (int i = 0; i <10; i++) {
			sendMsg.send("test-queue", "hellow");
		}
		*/
		//map
		
		HashMap<String, String> map = new HashMap<>();
		map.put("company", "船只博客");
		sendMsg.send("text_map", map);
		while(true){
			
		}
		
	}
	
	
	
	

}
