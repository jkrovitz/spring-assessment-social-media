package com.cooksys.socialmedia.socialmedia.services;

import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto createUser(UserRequestDto userRequestDto);

    ResponseEntity<UserResponseDto> getUserUsername(String username);

    UserResponseDto patchUser(UserRequestDto userRequestDto);

    ResponseEntity<UserResponseDto> deleteUser(String username);
}
