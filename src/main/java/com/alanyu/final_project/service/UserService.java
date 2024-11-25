package com.alanyu.final_project.service;

import com.alanyu.final_project.models.User;
import java.util.List;

public interface UserService {

	User getUserById(Integer userId);

	List<User> getUserList();

	User getUserByAccount(String account);

	User insertUser(User user);

	void deleteUser(Integer UserId);

	User updateUserById(User user);

}
