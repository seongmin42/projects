package com.seongmin.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seongmin.travel.model.dao.VideoDao;
import com.seongmin.travel.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {

	private final VideoDao videoDao;
	
	@Autowired
	public VideoServiceImpl(VideoDao videoDao){
		this.videoDao = videoDao;
	}
	
	@Override
	public List<Video> getAllVideos() {
		System.out.println(videoDao.selectAll());
		return videoDao.selectAll();
	}
	
	@Override
	public List<Video> getVideosByViewCnt(){
		return videoDao.selectByViewCnt();
	}

	@Override
	public Video getVideoByIndex(int index) {
		return videoDao.selectByIndex(index);
	}

	@Override
	public void registVideo(Video video) {
		videoDao.insertVideo(video);
	}

	@Override
	public void modifyVideo(Video video) {
		Video originVideo = videoDao.selectByIndex(video.getVideoIndex());
		originVideo.setTitle(video.getTitle());
		originVideo.setCategory(video.getCategory());
		originVideo.setUrl(video.getUrl());
		videoDao.updateVideo(originVideo);
	}

	@Override
	public void removeVideoByIndex(int index) {
		videoDao.deleteVideoByIndex(index);
	}

	@Override
	public void updateViewCnt(int videoIndex) {
		videoDao.updateViewCnt(videoIndex);
	}

	@Override
	public List<Video> getVideosByPart(String part) {
		return videoDao.getVideosByPart(part);
	}

	@Override
	public void likeVideo(int videoIndex) {
		videoDao.likeVideo(videoIndex);
	}

	@Override
	public void dislikeVideo(int videoIndex) {
		videoDao.dislikeVideo(videoIndex);
	}

	@Override
	public List<Video> searchVideo(String query) {
		return videoDao.searchVideo(query);
	}

}
