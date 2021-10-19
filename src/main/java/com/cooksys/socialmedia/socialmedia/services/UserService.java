package com.cooksys.socialmedia.socialmedia.services;

import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    ResponseEntity<UserResponseDto> createUser(UserRequestDto userRequestDto);
}
