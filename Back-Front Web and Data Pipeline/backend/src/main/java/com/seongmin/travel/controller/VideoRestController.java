package com.seongmin.travel.controller;

import com.seongmin.travel.model.dto.Video;
import com.seongmin.travel.model.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video-api")
public class VideoRestController {
	
	private VideoService videoService;
	
	@Autowired
	public VideoRestController(VideoService videoService) {
		this.videoService = videoService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Video>> getAllVideos(){
		List<Video> videos = videoService.getAllVideos();
		return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
	}
	
	@GetMapping("/detail/{videoIndex}")
	public ResponseEntity<Video> getVideoByIndex(@PathVariable int videoIndex){
		if(videoService.getVideoByIndex(videoIndex) != null) {
			videoService.updateViewCnt(videoIndex);
			return new ResponseEntity<Video>(videoService.getVideoByIndex(videoIndex), HttpStatus.OK);
		} else {
			return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/video")
	public ResponseEntity<String> registVideo(Video video){
		videoService.registVideo(video);
		return new ResponseEntity<String>("registed " + video.getTitle(), HttpStatus.CREATED);
	}
	
	@PutMapping("/video")
	public ResponseEntity<Video> modifyVideo(Video video){
		videoService.modifyVideo(video);
		return new ResponseEntity<Video>(videoService.getVideoByIndex(video.getVideoIndex()), HttpStatus.OK);
	}
	
	@DeleteMapping("/video/{videoIndex}")
	public ResponseEntity<String> removeVideo(@PathVariable int videoIndex){
		videoService.removeVideoByIndex(videoIndex);
		return new ResponseEntity<String>("removed " + videoIndex, HttpStatus.OK);
	}
	
	@PutMapping("/video/like/{videoIndex}")
	public ResponseEntity<Video> likeVideo(@PathVariable int videoIndex){
		videoService.likeVideo(videoIndex);
		return new ResponseEntity<Video>(videoService.getVideoByIndex(videoIndex), HttpStatus.OK);
	}
	
	@PutMapping("/video/dislike/{videoIndex}")
	public ResponseEntity<Video> dislikeVideo(@PathVariable int videoIndex){
		videoService.dislikeVideo(videoIndex);
		return new ResponseEntity<Video>(videoService.getVideoByIndex(videoIndex), HttpStatus.OK);
	}

	@GetMapping("/video/search/{query}")
	public ResponseEntity<List<Video>> searchVideo(@PathVariable String query){
		return new ResponseEntity<List<Video>>(videoService.searchVideo(query), HttpStatus.OK);
	}
}
