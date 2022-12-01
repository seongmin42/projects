package com.seongmin.travel.service;

import java.util.List;

import com.seongmin.travel.model.dto.Video;

public interface VideoService {
	public List<Video> getAllVideos();
	
	public Video getVideoByIndex(int videoIndex);
	
	public void registVideo(Video video);
	
	public void modifyVideo(Video video);
	
	public void removeVideoByIndex(int videoIndex);

	public void updateViewCnt(int videoIndex);

	List<Video> getVideosByViewCnt();

	List<Video> getVideosByPart(String part);

	public void likeVideo(int videoIndex);

	public void dislikeVideo(int videoIndex);

	List<Video> searchVideo(String query);
}
