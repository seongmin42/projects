package com.seongmin.travel.service;

import java.util.List;

import com.seongmin.travel.model.dto.Review;

public interface ReviewService {
	public List<Review> getAllReviews();

	public List<Review> getReviewsByVideoIndex(int videoIndex);
	
	public Review getReviewByIndex(int reviewIndex);

	public void registReview(Review review);

	public void modifyReview(Review review);

	public void removeReviewByIndex(int reviewIndex);

	public void updateViewCnt(int reviewIndex);

	public void likeReview(int reviewIndex);

	public void dislikeReview(int reviewIndex);
}
