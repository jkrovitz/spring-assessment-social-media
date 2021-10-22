package com.cooksys.socialmedia.socialmedia.services.impl;

import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;

import com.cooksys.socialmedia.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.socialmedia.entities.User;
import com.cooksys.socialmedia.socialmedia.exceptions.BadRequestException;
import com.cooksys.socialmedia.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.socialmedia.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final UserMapper userMapper;
    private final TweetMapper tweetMapper;

    //CALLBACK/HELPER METHODS
    private void checkUser(User user, Credentials credentials) {
        if (!user.getCredentials().equals(credentials)) {
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

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAllUsersByDeletedFalse();
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
        } else if (user.isPresent()) {
            throw new BadRequestException("The username is not available.");
        } else {
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
    public void followUser(String username, UserRequestDto userRequestDto) {
        User follower = getUserByUsername(userRequestDto.getCredentials().getUsername());
        User check = userMapper.dtoToEntity(userRequestDto);
        checkUser(follower, check.getCredentials());
        User followee = getUserByUsername(username);
        if (!followee.getFollowers().contains(follower)) {
            follower.userFollow(followee);
            followee.userFollowing(follower);
            userRepository.saveAndFlush(followee);
            userRepository.saveAndFlush(follower);
        } else {
            throw new BadRequestException("Already following user");
        }
    }


    @Override
    public void unFollowUser(String username, UserRequestDto userRequestDto) {
        User follower = getUserByUsername(userRequestDto.getCredentials().getUsername());
        User check = userMapper.dtoToEntity(userRequestDto);
        checkUser(follower, check.getCredentials());
        User followee = getUserByUsername(username);
        if (followee.getFollowers().contains(follower)) {
            follower.userUnfollow(followee);
            followee.userUnfollowing(follower);
            userRepository.saveAndFlush(followee);
        } else {
            throw new BadRequestException("You're already not following user");
        }
    }

    @Override
    public List<UserResponseDto> getUserFollowers(String username) {
        User user = getUserByUsername(username);
        List<User> followers = user.getFollowers();
        return userMapper.entitiesToDtos(followers);
    }

    @Override
    public List<UserResponseDto> getUserFollowing(String username) {
        User user = getUserByUsername(username);
        List<User> following = user.getFollowing();
        return userMapper.entitiesToDtos(following);
    }

    @Override
    public List<TweetResponseDto> getUserTweets(String username) {
        return tweetRepository.findByDeletedFalseAndAuthorOrderByPostedDesc(username);
    }

    @Override
    public List<TweetResponseDto> getUserMentions(String username) {
        User user = getUserByUsername(username);
        List<Tweet> userMentions = user.getMentions();
        return tweetMapper.entitiesToDtos(userMentions);
    }

    @Override
    public List<Tweet> getUserFeed(String username) {
        User tweetUser = getUserByUsername(username);
        if (tweetUser.getIsActive().equals(true)) {
            Set<User> tweetUsers = tweetUser.getUserFollowing().stream()
                    .filter(user -> user.getIsActive().equals(true))
                    .collect(Collectors.toSet());
            tweetUsers.add(tweetUser);
            List<Tweet> tweetU = new ArrayList<>();

            for(User tweetUser2 : tweetUsers) {
                tweetU.addAll(tweetUser2.getTweets());
            }
            return tweetU;
        }
        throw new EntityNotFoundException();
    }
}

