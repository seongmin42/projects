package com.seongmin.travel.model.dto;

import java.util.Date;

public class Review {
	private int reviewIndex;
	private int videoIndex;
	private String title;
	private String content;
	private Date regDate;
	private int viewCnt;
	private int like;
	private int dislike;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getReviewIndex() {
		return reviewIndex;
	}
	public void setReviewIndex(int reviewIndex) {
		this.reviewIndex = reviewIndex;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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
	
	// 등록 생성자
	public Review(int videoIndex, String title, String content, User user) {
		this.videoIndex = videoIndex;
		this.title = title;
		this.content = content;
		this.user = user;
		this.regDate = new Date();
	}
	
	// 수정 생성자
	public Review(int reviewIndex, String title, String content) {
		this.reviewIndex = reviewIndex;
		this.title = title;
		this.content = content;
	}
	
	public Review() {
	}
	
	@Override
	public String toString() {
		return "Review [reviewIndex=" + reviewIndex + ", videoIndex=" + videoIndex + ", title=" + title + ", content="
				+ content + ", user=" + user + ", regDate=" + regDate + ", viewCnt=" + viewCnt + ", like="
				+ like + ", dislike=" + dislike + "]";
	}
	
}
