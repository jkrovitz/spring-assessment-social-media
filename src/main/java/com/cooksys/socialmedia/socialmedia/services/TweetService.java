package com.cooksys.socialmedia.socialmedia.services;

import java.util.List;

import com.cooksys.socialmedia.socialmedia.dtos.*;
import org.springframework.http.ResponseEntity;

public interface TweetService {

	ResponseEntity<TweetResponseDto> postTweet(TweetRequestDto tweetRequestDto);
	
	List<TweetResponseDto> getAllTweets();
	
	TweetResponseDto deleteTweet(Long tweetId);

	List<HashtagDto> getTweetTags(Long tweetId);

	List<UserResponseDto> getTweetMentionedUsers(Long tweetId);
	
	List<TweetResponseDto> tweetReplies(Long tweetId);

	List<TweetResponseDto> getTweetReposts(Long tweetId);
	
	void addTweetLike(Long tweetId, UserRequestDto userRequestDto);

	List<UserResponseDto> getTweetLikes(Long tweetId);
}

