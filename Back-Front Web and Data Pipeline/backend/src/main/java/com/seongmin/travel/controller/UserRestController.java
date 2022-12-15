package com.seongmin.travel.controller;

import com.seongmin.travel.model.dto.User;
import com.seongmin.travel.service.UserService;
import com.seongmin.travel.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-api")
public class UserRestController {

	private UserService userService;
	private JwtUtil jwtUtil;
//	private KafkaTemplate<String, String> kafkaTemplate;

	private final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	public UserRestController(UserService userService, JwtUtil jwtUtil) {
//		, KafkaTemplate<String, String> kafkaTemplate
		this.userService = userService;
		this.jwtUtil = jwtUtil;
//		this.kafkaTemplate = kafkaTemplate;
	}

	@GetMapping("/list")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id) {
		if (userService.getUserById(id) != null) {
			return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/signin")
	public ResponseEntity<User> signin(User user) {
		userService.registUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@PutMapping("/user")
	public ResponseEntity<User> updateUser(User user) {
		userService.modifyUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> updateUser(@PathVariable String id) {
		userService.removeUser(id);
		return new ResponseEntity<String>("Deleted " + id, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(String id, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> res = new HashMap<>();
		User loginUser = userService.login(id, password);
		if (loginUser == null) {
			res.put("message", "failed to login");
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.UNAUTHORIZED);
		}
		session.setAttribute("loginUser", loginUser.getId());
		res.put("loginUser", loginUser.getId());
		/*
		kafkaTemplate.send("login.kafka", loginUser.getId() + " logined at " + new Date().toString()).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onFailure(Throwable ex) {
				logger.error(ex.getMessage(), ex);
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info(result.toString());
			}
		});
		*/
		try {
			res.put("access-token", jwtUtil.createToken("id", loginUser.getId()));
		} catch (UnsupportedEncodingException e) {
			res.put("message", "failed to login");
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.UNAUTHORIZED);
		}
		res.put("message", "login success");
		return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session){
		session.setAttribute("loginUser", null);
		return new ResponseEntity<String>("로그아웃", HttpStatus.OK);
	}

	@GetMapping("/nickname/{nickname}")
	public ResponseEntity<String> getId(@PathVariable String nickname){
		return new ResponseEntity<String>(userService.getId(nickname), HttpStatus.OK);
	}
}
