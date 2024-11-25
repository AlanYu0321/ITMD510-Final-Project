package com.alanyu.final_project.service.impl;

import com.alanyu.final_project.dao.UserDao;
import com.alanyu.final_project.models.User;
import com.alanyu.final_project.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDao();

	@Override
	public User getUserById(Integer UserId) {
		return userDao.getUserById(UserId);
	}

	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}

	@Override
	public User getUserByAccount(String account) {
		return userDao.getUserByAccount(account);
	}

	@Override
	@Transactional
	public User insertUser(User user) {
		Integer userId = userDao.insertUser(user);
		return userDao.getUserById(userId);
	}

	@Override
	@Transactional
	public void deleteUser(Integer userId) {
		userDao.deleteUser(userId);
	}

	@Override
	@Transactional
	public User updateUserById(User user) {
		return userDao.updateUserById(user);
	}
}