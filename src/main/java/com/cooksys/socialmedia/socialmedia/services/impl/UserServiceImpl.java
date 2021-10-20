package com.cooksys.socialmedia.socialmedia.services.impl;

import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;

import com.cooksys.socialmedia.socialmedia.entities.User;
import com.cooksys.socialmedia.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.socialmedia.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public ResponseEntity<UserResponseDto> getUserUsername(String username) {
        Optional<User> chosenUser = userRepository.findByCredentialsUsername(username);
        if (chosenUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.entityToDto(chosenUser.get()), HttpStatus.OK);
    }

    @Override
    public UserResponseDto patchUser(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponseDto> deleteUser(String username) {
        Optional<User> chosenUser = userRepository.findByCredentialsUsername(username);
        Long id = chosenUser.get().getId();
        userRepository.deleteById(id);
        if (chosenUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.GONE);
        }
        return new ResponseEntity<>(userMapper.entityToDto(chosenUser.get()), HttpStatus.NOT_MODIFIED);
    }
}
