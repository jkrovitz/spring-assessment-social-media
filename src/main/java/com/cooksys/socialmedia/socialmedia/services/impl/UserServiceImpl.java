package com.cooksys.socialmedia.socialmedia.services.impl;

import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;

import com.cooksys.socialmedia.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.socialmedia.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDto> getAllUsers() {

        return userMapper.entitiesToDtos(userRepository.findAll());
    }

    //ADD CATCHES
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return userMapper.entityToDto(userMapper.dtoToEntity(userRequestDto));
    }
}
