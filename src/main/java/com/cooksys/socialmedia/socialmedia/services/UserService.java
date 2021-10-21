package com.cooksys.socialmedia.socialmedia.services;

import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserUsername(String username);

    UserResponseDto deleteUser(UserRequestDto userRequestDto);

    UserResponseDto userNameProfileUpdate(UserRequestDto userRequestDto);

    void followUser(String username, UserRequestDto userRequestDto);

    void unFollowUser(String username, UserRequestDto userRequestDto);

    List<UserResponseDto> getUserFollowers(String username);

    List<UserResponseDto> getUserFollowing(String username);
}
