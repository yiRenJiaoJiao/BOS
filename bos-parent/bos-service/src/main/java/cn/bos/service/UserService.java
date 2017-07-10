package cn.bos.service;

import java.util.List;

import cn.bos.domain.user.User;

public interface UserService {
	public void saveUser(User user);
	public void deleteUser(User user);
	public List<User> findAllUSer();
	public User findUserById(User user);
	public void UpdateUser(User user);
}
	