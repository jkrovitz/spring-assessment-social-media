package com.cooksys.socialmedia.socialmedia.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.socialmedia.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @GetMapping("/@{username}")
    public UserResponseDto getUserUsername(@PathVariable String username) {
        return userService.getUserUsername(username);
    }

    @DeleteMapping("/@{username}")
    public UserResponseDto deleteUser(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        return userService.deleteUser(username, userRequestDto);
    }

    @PatchMapping("/@{username}")
    public UserResponseDto userNameProfileUpdate(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        return userService.userNameProfileUpdate(username, userRequestDto);

    }

    @PostMapping("/@{username}/follow")
    @ResponseStatus(HttpStatus.CREATED)
    public void followUser(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        userService.followUser(username, userRequestDto);
    }

    @PostMapping("/@{username}/unfollow")
    @ResponseStatus(HttpStatus.CREATED)
    public void unFollowUser(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        userService.unFollowUser(username, userRequestDto);
    }

    @GetMapping("/@{username}/followers")
    public List<UserResponseDto> getUserFollowers(@PathVariable String username) {
        return userService.getUserFollowers(username);
    }

    @GetMapping("/@{username}/following")
    public List<UserResponseDto> getUserFollowing(@PathVariable String username) {
        return userService.getUserFollowing(username);
    }

    @GetMapping("/@{username}/tweets")
    public List<TweetResponseDto> getUserTweets(@PathVariable String username) {
        return userService.getUserTweets(username);
    }

    @GetMapping("/@{username}/mentions")
    public List<TweetResponseDto> getUserMentions(@PathVariable String username) {
        return userService.getUserMentions(username);
    }

    @GetMapping("/@{username}/feed")
    public List<Tweet> getUserFeed(@PathVariable String username) {
        return userService.getUserFeed(username);
    }
}