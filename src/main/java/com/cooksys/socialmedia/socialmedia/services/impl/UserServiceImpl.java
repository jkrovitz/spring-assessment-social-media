package com.cooksys.socialmedia.socialmedia.services.impl;

import com.cooksys.socialmedia.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;

import com.cooksys.socialmedia.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.socialmedia.entities.User;
import com.cooksys.socialmedia.socialmedia.exceptions.BadRequestException;
import com.cooksys.socialmedia.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.socialmedia.mappers.CredentialsMapper;
import com.cooksys.socialmedia.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.socialmedia.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CredentialsMapper credentialsMapper;

//CALLBACK/HELPER METHODS
    private void checkUser(User user, Credentials credentials) {
        if(!user.getCredentials().equals(credentials)) {
            throw new BadRequestException("The credentials are not valid.");
        }
    }

    private User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
        if (user.isEmpty()) {
            throw new NotFoundException("No user found");
        }
        return user.get();
    }

    //ADD FLAG FOR DELETED to be removed from list
    public List<UserResponseDto> getAllUsers() {
        return userMapper.entitiesToDtos(userRepository.findAll());
    }

    //ADD CATCHES & REFACTOR
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        String username = userRequestDto.getCredentials().getUsername();
        Optional<User> user = userRepository.findByCredentialsUsername(username);
        if (user.isPresent() && user.get().isDeleted() &&
                user.get().getCredentials().getPassword().equals(userRequestDto.getCredentials().getPassword())) {
            user.get().setDeleted(false);
            userRepository.saveAndFlush(user.get());
            return userMapper.entityToDto(userMapper.dtoToEntity(userRequestDto));
        }
        else if (user.isPresent()) {
            throw new BadRequestException("The username is not available.");
        }
        else {
            return userMapper.entityToDto(userRepository.saveAndFlush(userMapper.dtoToEntity(userRequestDto)));
        }
    }

    //ADD CATCHES & REFACTOR
    @Override
    public UserResponseDto getUserUsername(String username) {
        return userMapper.entityToDto(getUserByUsername(username));
    }

    //ADD CATCHES & REFACTOR
    @Override
    public UserResponseDto deleteUser(UserRequestDto userRequestDto) {
        User user = getUserByUsername(userRequestDto.getCredentials().getUsername());
        User check = userMapper.dtoToEntity(userRequestDto);
        checkUser(user, check.getCredentials());
        user.setDeleted(true);
        return userMapper.entityToDto(userRepository.saveAndFlush(user));
    }

    //ADD CATCHES & REFACTOR
    @Override
    public UserResponseDto userNameProfileUpdate(UserRequestDto userRequestDto) {
        User user = getUserByUsername(userRequestDto.getCredentials().getUsername());
        User check = userMapper.dtoToEntity(userRequestDto);
        checkUser(user, check.getCredentials());
        user.setProfile(check.getProfile());
        return userMapper.entityToDto(userRepository.saveAndFlush(user));
    }

    @Override
    public void followUser(String username, CredentialsDto credentialsDto) {
        User follower = getUserByUsername(credentialsDto.getUsername());
        checkUser(follower, credentialsMapper.dtoToEntity(credentialsDto));
        User followee = getUserByUsername(username);
        followee.userFollowing(follower);
        userRepository.saveAndFlush(followee);
    }

    @Override
    public void unFollowUser(String username, CredentialsDto credentialsDto) {
        User follower = getUserByUsername(credentialsDto.getUsername());
        checkUser(follower, credentialsMapper.dtoToEntity(credentialsDto));
        User followee = getUserByUsername(username);
        followee.userUnfollowing(follower);
        userRepository.saveAndFlush(followee);
    }

}
