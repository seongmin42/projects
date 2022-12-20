package com.seongmin.travel.controller;

import java.util.List;

import com.seongmin.travel.model.dto.Video;
import com.seongmin.travel.model.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main-api")
public class MainRestController {

	private VideoService videoService;

	@Autowired
	public MainRestController(VideoService videoService) {
		this.videoService = videoService;
	}
	
	@GetMapping("/view-cnt")
	public ResponseEntity<List<Video>> getVideosByViewCnt(){
		return new ResponseEntity<List<Video>>(videoService.getVideosByViewCnt(), HttpStatus.OK);
	}
	
	@GetMapping("{part}")
	public ResponseEntity<List<Video>> getVideosByPart(@PathVariable String part){
		return new ResponseEntity<List<Video>>(videoService.getVideosByPart(part), HttpStatus.OK);
	}
}
