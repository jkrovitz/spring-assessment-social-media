package com.cooksys.socialmedia.socialmedia.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmedia.socialmedia.dtos.ContextDto;
import com.cooksys.socialmedia.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.socialmedia.dtos.HashtagDto;
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
	public ResponseEntity<TweetResponseDto> postTweet(@RequestBody TweetRequestDto tweetRequestDto) {
		 return tweetService.postTweet(tweetRequestDto);
	}

	
	@GetMapping
	public List<TweetResponseDto> getAllTweets() {
		return tweetService.getAllTweets();
	}
	
	@GetMapping("/{tweetId}")
	public TweetResponseDto getTweetById(@PathVariable Long tweetId) {
		return tweetService.getTweetById(tweetId);
	}

	@DeleteMapping("/{tweetId}")
	public TweetResponseDto deleteTweet(@PathVariable Long tweetId) {
		return tweetService.deleteTweet(tweetId);
	}
	
    @GetMapping("/{tweetId}/tags")
    public List<HashtagDto> getTweetTags(@PathVariable Long tweetId) {
		return tweetService.getTweetTags(tweetId);
    }

	@GetMapping("/{tweetId}/mentions")
	public List<UserResponseDto> getTweetMentionedUsers(@PathVariable Long tweetId) {
		return tweetService.getTweetMentionedUsers(tweetId);
	}

	@GetMapping("/{tweetId}/reposts")
	public List<TweetResponseDto> getTweetReposts(@PathVariable Long tweetId) {
		return tweetService.getTweetReposts(tweetId);
	}
	
	@PostMapping("/{tweetId}/repost")
	public TweetResponseDto createTweetRepost(@PathVariable Long tweetId, @RequestBody CredentialsDto credentialsDto) {
		return tweetService.createTweetRepost(tweetId, credentialsDto);
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
	
	@GetMapping("/{tweetId}/replies")
	public List<TweetResponseDto> tweetReplies(@PathVariable Long tweetId){
		return tweetService.tweetReplies(tweetId);
	}
	
	@PostMapping("{tweetId}/reply")
	public TweetResponseDto addTweetReply(@PathVariable Long tweetId, @RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.addTweetReply(tweetId, tweetRequestDto);
	}
	
	@GetMapping("/{id}/context")
	public ContextDto context(@PathVariable Long id) {
		return tweetService.context(id);
	}
}