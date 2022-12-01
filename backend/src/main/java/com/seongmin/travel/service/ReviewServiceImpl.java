package com.seongmin.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seongmin.travel.model.dao.ReviewDao;
import com.seongmin.travel.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	private final ReviewDao reviewDao;

	@Autowired
	public ReviewServiceImpl(ReviewDao reviewDao){
		this.reviewDao = reviewDao;
	}

	@Override
	public List<Review> getAllReviews() {
		return reviewDao.selectAll();
	}

	@Override
	public Review getReviewByIndex(int index) {
		return reviewDao.selectByIndex(index);
	}

	@Override
	public void registReview(Review review) {
		reviewDao.insertReview(review);
	}

	@Override
	public void modifyReview(Review review) {
		Review originReview = reviewDao.selectByIndex(review.getReviewIndex());
		originReview.setTitle(review.getTitle());
		originReview.setContent(review.getContent());
		reviewDao.updateReview(originReview);
	}

	@Override
	public void removeReviewByIndex(int index) {
		reviewDao.deleteReviewByIndex(index);
	}

	@Override
	public void updateViewCnt(int reviewIndex) {
		reviewDao.updateViewCnt(reviewIndex);
	}

	@Override
	public void likeReview(int reviewIndex) {
		reviewDao.likeReview(reviewIndex);
	}

	@Override
	public void dislikeReview(int reviewIndex) {
		reviewDao.dislikeReview(reviewIndex);
	}

	@Override
	public List<Review> getReviewsByVideoIndex(int videoIndex) {
		return reviewDao.selectByVideoIndex(videoIndex);
	}

}
