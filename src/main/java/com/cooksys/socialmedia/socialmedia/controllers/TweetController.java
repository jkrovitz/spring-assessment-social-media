package com.cooksys.socialmedia.socialmedia.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.services.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {

	private final TweetService tweetService;
	
	@GetMapping
	public List<TweetResponseDto> getAllTweets() {
		return tweetService.getAllTweets();
	}

	@DeleteMapping("{/id}")
	public TweetResponseDto deleteTweet(@PathVariable Long tweetId) {
		return tweetService.deleteTweet(tweetId);
	}
}