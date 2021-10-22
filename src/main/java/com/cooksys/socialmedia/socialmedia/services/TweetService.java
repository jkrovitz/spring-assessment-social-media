package com.cooksys.socialmedia.socialmedia.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cooksys.socialmedia.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;

public interface TweetService {

	ResponseEntity<TweetResponseDto> postTweet(TweetRequestDto tweetRequestDto);
	
	List<TweetResponseDto> getAllTweets();
	
	TweetResponseDto deleteTweet(Long tweetId);

	void addTweetLike(Long tweetId, UserRequestDto userRequestDto);

	List<UserResponseDto> getTweetLikes(Long tweetId);

}

