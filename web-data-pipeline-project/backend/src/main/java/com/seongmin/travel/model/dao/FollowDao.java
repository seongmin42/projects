package com.seongmin.travel.model.dao;

import com.seongmin.travel.model.dto.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowDao {

    public void follow(@Param("following_id") String following,@Param("follower_id") String follower);

    public void unfollow(@Param("following_id") String following,@Param("follower_id") String follower);

    public List<User> getFollowById(String user);
}
