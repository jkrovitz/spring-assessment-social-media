package com.cooksys.socialmedia.socialmedia.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.socialmedia.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

	private final TweetRepository tweetRepository;

	private final TweetMapper tweetMapper;

	private Tweet getTweet(Long tweetId) {
		Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);
		if (optionalTweet.isEmpty()) {
			throw new NotFoundException("No tweet found with id " + tweetId);
		}
		return optionalTweet.get();
	}
	
	@Override
	public List<TweetResponseDto> getAllTweets() {
		List<Tweet> tweetList = new ArrayList<Tweet>();
		List<Tweet> tweetListSorted = new ArrayList<Tweet>();
		for (Tweet tweet : tweetRepository.findAll((Sort.by(Sort.Direction.DESC, "posted")))){
			if (tweet.isDeleted() == false) {
				tweetList.add(tweet);
			}
//		// Sort in dessending order
//        Collections.sort(tweetList, new Comparator<Tweet>() {
//            public int compare(Tweet tweet1, Tweet tweet2) {
//                return Long.valueOf(tweet1.getPosted().getTime()).compareTo(tweet2.getPosted().getTime());
//            }
//        });
//        
//        System.out.println("After Descending sort");
//        for(Tweet t: tweetList){
//            tweetListSorted.add(t);
//        }
		}
		return tweetMapper.entitiesToDtos(tweetList);
	}

	@Override
	public TweetResponseDto deleteTweet(Long tweetId) {
		Tweet tweetToDelete = getTweet(tweetId);
		tweetToDelete.setDeleted(true);
		return tweetMapper.entityToDto(tweetToDelete);
	}

}
