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

	public Review() {}

	public Review(int reviewIndex, int videoIndex, String title, String content, Date regDate, int viewCnt, int like, int dislike, User user) {
		this.reviewIndex = reviewIndex;
		this.videoIndex = videoIndex;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.viewCnt = viewCnt;
		this.like = like;
		this.dislike = dislike;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Review [reviewIndex=" + reviewIndex + ", videoIndex=" + videoIndex + ", title=" + title + ", content="
				+ content + ", user=" + user + ", regDate=" + regDate + ", viewCnt=" + viewCnt + ", like="
				+ like + ", dislike=" + dislike + "]";
	}
	
}
