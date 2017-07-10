package cn.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.bos.domain.user.User;

public interface UserDao extends JpaRepository<User, String>{
	
}
