package com.seongmin.travel.model.dao;

import java.util.List;

import com.seongmin.travel.model.dto.Review;

public interface ReviewDao {
	
	public List<Review> selectAll();
	
	public List<Review> selectByVideoIndex(int videoIndex);

	public Review selectByIndex(int reviewIndex);

	public void insertReview(Review review);

	public void updateReview(Review review);

	public void deleteReviewByIndex(int reviewIndex);

	public void updateViewCnt(int reviewIndex);

	public void likeReview(int reviewIndex);

	public void dislikeReview(int reviewIndex);

}
