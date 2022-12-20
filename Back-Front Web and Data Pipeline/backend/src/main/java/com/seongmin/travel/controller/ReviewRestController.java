package com.seongmin.travel.controller;

import com.seongmin.travel.model.dto.Review;
import com.seongmin.travel.model.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/review-api")
public class ReviewRestController {
	
	private ReviewService reviewService;
	
	@Autowired
	public ReviewRestController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Review>> getAllReviews(){
		return new ResponseEntity<List<Review>>(reviewService.getAllReviews(), HttpStatus.OK);
	}
	
	@GetMapping("/detail/{reviewIndex}")
	public ResponseEntity<Review> getReviewByIndex(@PathVariable int reviewIndex){
		if(reviewService.getReviewByIndex(reviewIndex) != null){
			reviewService.updateViewCnt(reviewIndex);
			return new ResponseEntity<Review>(reviewService.getReviewByIndex(reviewIndex), HttpStatus.OK);
		} else {
			return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/list/video")
	public ResponseEntity<List<Review>> getReviewByVideoIndex(@RequestParam int videoIndex){
		return new ResponseEntity<List<Review>>(reviewService.getReviewsByVideoIndex(videoIndex), HttpStatus.OK);
	}
	
	
	@PostMapping("/review")
	public ResponseEntity<String> registReview(@RequestBody Review review){
		review.setRegDate(new Date());
		System.out.println(review);
		reviewService.registReview(review);
		return new ResponseEntity<String>("registed " + review.getTitle(), HttpStatus.CREATED);
	}
	
	@PutMapping("/review")
	public ResponseEntity<Review> modifyReview(Review review){
		reviewService.modifyReview(review);
		return new ResponseEntity<Review>(reviewService.getReviewByIndex(review.getReviewIndex()), HttpStatus.OK);
	}
	
	@DeleteMapping("/review/{reviewIndex}")
	public ResponseEntity<String> removeReview(@PathVariable int reviewIndex){
		reviewService.removeReviewByIndex(reviewIndex);
		return new ResponseEntity<String>("removed " + reviewIndex, HttpStatus.OK);
	}

	@PutMapping("/review/like/{reviewIndex}")
	public ResponseEntity<Review> likeReview(@PathVariable int reviewIndex){
		reviewService.likeReview(reviewIndex);
		return new ResponseEntity<Review>(reviewService.getReviewByIndex(reviewIndex), HttpStatus.OK);
	}

	@PutMapping("/review/dislike/{reviewIndex}")
	public ResponseEntity<Review> dislikeReview(@PathVariable int reviewIndex){
		reviewService.dislikeReview(reviewIndex);
		return new ResponseEntity<Review>(reviewService.getReviewByIndex(reviewIndex), HttpStatus.OK);
	}

}
