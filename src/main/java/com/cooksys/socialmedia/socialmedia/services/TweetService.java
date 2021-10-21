package com.cooksys.socialmedia.socialmedia.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cooksys.socialmedia.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;

public interface TweetService {

	ResponseEntity<TweetResponseDto> postTweet(TweetRequestDto tweetRequestDto);
	
	List<TweetResponseDto> getAllTweets();
	
	TweetResponseDto deleteTweet(Long tweetId);

	List<HashtagDto> getTweetTags(Long tweetId);
}

