package com.cooksys.socialmedia.socialmedia.controllers;

import java.util.List;

import com.cooksys.socialmedia.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmedia.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.socialmedia.services.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {

	private final TweetService tweetService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
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

	@PostMapping("/{tweetId}/like")
	@ResponseStatus(HttpStatus.CREATED)
	public void addTweetLike(@PathVariable Long tweetId, @RequestBody UserRequestDto userRequestDto) {
		tweetService.addTweetLike(tweetId, userRequestDto);
	}

	@GetMapping("/{tweetId}/likes")
	public List<UserResponseDto> getTweetLikes(@PathVariable Long tweetId) {
		return tweetService.getTweetLikes(tweetId);
	}
}
	
    @GetMapping("/{tweetId}/tags")
    public List<HashtagDto> getTweetTags(@PathVariable Long tweetId) {

		return tweetService.getTweetTags(tweetId);
    }

	@GetMapping("/{tweetId}/mentions")
	public List<UserResponseDto> getTweetMentionedUsers(@PathVariable Long tweetId) {
		return tweetService.getTweetMentionedUsers(tweetId);
	}
	
}
