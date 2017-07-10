package cn.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bos.dao.UserDao;
import cn.bos.domain.user.User;
import cn.bos.service.UserService;

@Service(value="userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;	
	@Override
	public void saveUser(User user) {
		userDao.save(user);
		
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
		
	}

	@Override
	public List<User> findAllUSer() {
		 userDao.findAll();
		return null;
	}

	@Override
	public User findUserById(User user) {
		return null;
	}

	@Override
	public void UpdateUser(User user) {
		//userDao.saveAndFlush(user);
	}

}
