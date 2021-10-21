package com.cooksys.socialmedia.socialmedia.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmedia.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.services.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {

	private final TweetService tweetService;
	
	@PostMapping
	public ResponseEntity<TweetResponseDto> postTweet(@RequestBody TweetRequestDto tweetRequestDto) {
		 return tweetService.postTweet(tweetRequestDto);
	}

	
	@GetMapping
	public List<TweetResponseDto> getAllTweets() {
		return tweetService.getAllTweets();
	}

	@DeleteMapping("/{tweetId}")
	public TweetResponseDto deleteTweet(@PathVariable Long tweetId) {
		return tweetService.deleteTweet(tweetId);
	}
	
    @GetMapping("/{tweetId}/tags")
    public ResponseEntity<TweetResponseDto> getTweetTags(@PathVariable Long tweetId) {
        return tweetService.getTweetTags(tweetId);
    }
	
}