package cn.bos.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bos.domain.user.User;
import cn.bos.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:applicationContext-domain.xml",
		"classpath:applicationContext-dao.xml",
		"classpath:applicationContext-service.xml",
		"classpath:applicationContext.xml"})
public class UserServiceImplTest {
	@Autowired
	private UserService userService;
	@Test
	public void saveUser() {
		User user =new User();
		user.setPassword("123");
		user.setEmail("123456@163.com");
		user.setTelephone("11111111");
		user.setUsername("lili");
		userService.save(user);
	}

	@Test
	public void testTestDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestFindAllUSer() {
		fail("Not yet implemented");
	}

	@Test
	public void findByUserNameAndPassword() {
		User user = userService.findByEmailAndPassword("linda", "123");
		System.out.println(user);
	}

	@Test
	public void testTestUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestFindUserById() {
		fail("Not yet implemented");
	}
	
	@Test
	public void updatePassword(){
		userService.updatePassword("123456","limei");
	}
}
