package com.seongmin.travel.model.dao;

import java.util.List;

import com.seongmin.travel.model.dto.Video;

public interface VideoDao {
	
	public List<Video> selectAll();
	
	public Video selectByIndex(int index);
	
	public List<Video> selectByViewCnt();
	
	public void insertVideo(Video video);
	
	public void updateVideo(Video video);
	
	public void deleteVideoByIndex(int index);
	
	public void updateViewCnt(int index);

	public List<Video> getVideosByPart(String part);

	public void likeVideo(int index);

	public void dislikeVideo(int index);

	List<Video> searchVideo(String query);
}
