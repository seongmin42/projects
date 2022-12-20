package com.seongmin.travel.model.service;

import com.seongmin.travel.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seongmin.travel.model.dao.FollowDao;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService{

    private final FollowDao followDao;

    @Autowired
    public FollowServiceImpl(FollowDao followDao){
        this.followDao = followDao;
    }

    @Override
    public void follow(String following, String follower) {
        followDao.follow(following, follower);
    }

    @Override
    public void unfollow(String following, String follower) {
        followDao.unfollow(following,follower);
    }

    @Override
    public List<User> getFollowById(String user) {
        return followDao.getFollowById(user);
    }
}
