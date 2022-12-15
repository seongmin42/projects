package com.seongmin.travel.model.dto;

public class Follow {
	String following;
	String follower;
	
	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}

	public String getFollower() {
		return follower;
	}

	public void setFollower(String follower) {
		this.follower = follower;
	}

	public Follow() {}
	
	public Follow(String following, String follower) {
		super();
		this.following = following;
		this.follower = follower;
	}

	@Override
	public String toString() {
		return "Follow [following=" + following + ", follower=" + follower + "]";
	}

}
