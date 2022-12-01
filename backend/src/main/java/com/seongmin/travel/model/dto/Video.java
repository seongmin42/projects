package com.seongmin.travel.model.dto;

import java.util.Date;

public class Video {
	private int videoIndex;
	private String title;
	private String category;

	private User user;

	private String url;
	private Date regDate;
	private int viewCnt;
	private int like;
	private int dislike;
	public int getVideoIndex() {
		return videoIndex;
	}

	public void setVideoIndex(int videoIndex) {
		this.videoIndex = videoIndex;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	public Video() {}
	// 조회 생성자
	public Video(int videoIndex, String title, String category, User user, String url, Date regDate, int viewCnt,
			int like, int dislike) {
		super();
		this.videoIndex = videoIndex;
		this.title = title;
		this.category = category;
		this.user = user;
		this.url = url;
		this.regDate = regDate;
		this.viewCnt = viewCnt;
		this.like = like;
		this.dislike = dislike;
	}
	
	// 등록 생성자
	public Video(String title, String category, User user, String url) {
		super();
		this.title = title;
		this.category = category;
		this.user = user;
		this.url = url;
		this.regDate = new Date();
	}
	
	// 수정 생성자
	public Video(int videoIndex, String title, String category, String url) {
		this.videoIndex = videoIndex;
		this.title = title;
		this.category = category;
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Video [videoIndex=" + videoIndex + ", title=" + title + ", category=" + category + ", User="
				+ user + ", url=" + url + ", regDate=" + regDate + ", viewCnt=" + viewCnt + ", like=" + like
				+ ", dislike=" + dislike + "]\n";
	}
	
	
	
}
