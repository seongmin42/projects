package com.seongmin.travel.model.service;

import java.util.List;

import com.seongmin.travel.model.dao.UserDao;
import com.seongmin.travel.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public List<User> getAllUsers() {
		return userDao.selectAll();
	}

	@Override
	public User getUserById(String id) {
		return userDao.selectById(id);
	}

	@Override
	public void registUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public void modifyUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void removeUser(String id) {
		userDao.deleteUser(id);
	}
	
	@Override
	public User login(String id, String password) {
		if(userDao.selectById(id) != null) {
			if(userDao.selectById(id).getPassword().equals(password)) {
				return userDao.selectById(id);
			}
			return null;
		} else {
			return null;
		}
	}

}
