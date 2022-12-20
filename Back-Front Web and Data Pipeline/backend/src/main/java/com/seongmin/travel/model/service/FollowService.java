package com.seongmin.travel.model.service;

import com.seongmin.travel.model.dto.User;

import java.util.List;

public interface FollowService {
    public void follow(String following, String follower);

    public void unfollow(String following, String follower);

    public List<User> getFollowById(String user);
}
