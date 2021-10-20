package com.cooksys.socialmedia.socialmedia.services;

import java.util.List;

import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;

public interface TweetService {

	TweetResponseDto deleteTweet(Long tweetId);

	List<TweetResponseDto> getAllTweets();
}
