package com.cooksys.socialmedia.socialmedia.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.socialmedia.entities.User;
import com.cooksys.socialmedia.socialmedia.exceptions.BadRequestException;
import com.cooksys.socialmedia.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.socialmedia.mappers.HashtagMapper;
import com.cooksys.socialmedia.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.socialmedia.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

	private final TweetRepository tweetRepository;

	private final TweetMapper tweetMapper;
	
	private final HashtagMapper hashtagMapper;

	private Tweet getTweet(Long tweetId) {
		Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);
		if (optionalTweet.isEmpty()) {
			throw new NotFoundException("No tweet found with id " + tweetId);
		}
		Tweet tweet = optionalTweet.get();
		if (tweet.isDeleted() == true) {
			throw new BadRequestException("Tweet with id " + tweetId + " has already been flagged as deleted");
		}
		return tweet;
	}
	

	public ResponseEntity<TweetResponseDto> postTweet(TweetRequestDto tweetRequestDto) {
        Tweet tweetToSave = tweetMapper.dtoToEntity(tweetRequestDto);
        return new ResponseEntity<>(tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToSave)), HttpStatus.CREATED);
    }
	
	
	@Override
	public List<TweetResponseDto> getAllTweets() {
		List<Tweet> tweetList = new ArrayList<Tweet>();
		for (Tweet tweet : tweetRepository.findAll((Sort.by(Sort.Direction.DESC, "posted")))){
			if (tweet.isDeleted() == false) {
				tweetList.add(tweet);
			}
		}
		return tweetMapper.entitiesToDtos(tweetList);
	}

	@Override
	public TweetResponseDto deleteTweet(Long tweetId) {
		Tweet tweetToDelete = getTweet(tweetId);
		tweetToDelete.setDeleted(true);
		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToDelete));
	}

    @Override
    public ResponseEntity<HashtagDto> getTweetTags(Long tweetId) {
        Optional<Tweet> chosenTweet = tweetRepository.findById(tweetId);
        if (chosenTweet.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hashtagMapper.entitiesToDtos(chosenTweet.getHashtags()), HttpStatus.OK);
    }
}
