package com.seongmin.travel.model.dao;

import java.util.List;
import com.seongmin.travel.model.dto.User;

public interface UserDao {
	
	public List<User> selectAll();

	public User selectById(String id);
	
	public void insertUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(String id);

	public String selectId(String nickname);
}
