package com.seongmin.travel.controller;

import java.util.List;

import com.seongmin.travel.model.dto.Follow;
import com.seongmin.travel.model.dto.User;
import com.seongmin.travel.model.service.FollowService;
import com.seongmin.travel.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow-api")
public class FollowRestController {

    private UserService userService;
    private FollowService followService;

    @Autowired
    public FollowRestController(UserService userService, FollowService followService) {
        this.followService = followService;
        this.userService = userService;
    }

    @GetMapping("/list/{user}")
    public ResponseEntity<List<User>> getFollow(@PathVariable String user){
        return new ResponseEntity<List<User>>(followService.getFollowById(user), HttpStatus.OK);
    }

    @PutMapping("/follow")
    public ResponseEntity<List<User>> follow(Follow follow) {
        followService.follow(follow.getFollowing(), follow.getFollower());
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/unfollow")
    public ResponseEntity<List<User>> unfollow(Follow follow) {
        followService.unfollow(follow.getFollowing(), follow.getFollower());
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }
}
