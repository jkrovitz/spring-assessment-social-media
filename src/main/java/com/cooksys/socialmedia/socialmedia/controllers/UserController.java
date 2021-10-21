package com.cooksys.socialmedia.socialmedia.controllers;

import java.util.List;


import com.cooksys.socialmedia.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.socialmedia.services.UserService;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @GetMapping("/@{username}")
    public UserResponseDto getUserUsername(@PathVariable String username) {
        return userService.getUserUsername(username);
    }

    @DeleteMapping("/@{username}")
    public UserResponseDto deleteUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.deleteUser(userRequestDto);
    }

    @PatchMapping("/@{username}")
    public UserResponseDto userNameProfileUpdate(@RequestBody UserRequestDto userRequestDto) {
        return userService.userNameProfileUpdate(userRequestDto);
    }

    @PostMapping("/@{username}/follow")
    public void followUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        userService.followUser(username, credentialsDto);
    }

    @PostMapping("/@{username}/unfollow")
    public void unFollowUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        userService.unFollowUser(username, credentialsDto);
    }
}