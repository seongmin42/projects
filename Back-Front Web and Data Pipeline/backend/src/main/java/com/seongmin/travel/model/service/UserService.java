package com.seongmin.travel.model.service;

import java.util.List;

import com.seongmin.travel.model.dto.User;

public interface UserService {
	
	public List<User> getAllUsers();
	
	public User getUserById(String id);
	
	public void registUser(User user);
	
	public void modifyUser(User user);
	
	public void removeUser(String id);

	public User login(String id, String password);

}
