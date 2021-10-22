package com.cooksys.socialmedia.socialmedia.services.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cooksys.socialmedia.socialmedia.dtos.*;
import com.cooksys.socialmedia.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.socialmedia.services.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.socialmedia.entities.User;
import com.cooksys.socialmedia.socialmedia.exceptions.BadRequestException;
import com.cooksys.socialmedia.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.socialmedia.mappers.HashtagMapper;
import com.cooksys.socialmedia.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.socialmedia.repositories.HashtagRepository;
import com.cooksys.socialmedia.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.socialmedia.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

	private final UserMapper userMapper;

	private final TweetRepository tweetRepository;

	private final TweetMapper tweetMapper;
	
	private final HashtagMapper hashtagMapper;

	private final UserRepository userRepository;

	private final HashtagRepository hashtagRepository;

	private final UserService userService;

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
		String username = tweetRequestDto.getCredentials().getUsername();
		Tweet tweetToSave = tweetMapper.dtoToEntity(tweetRequestDto);
		Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);
		if (optionalUser.isPresent() && optionalUser.get().getCredentials().getPassword()
				.equals(tweetRequestDto.getCredentials().getPassword())) {
			User user = optionalUser.get();
			tweetToSave.setAuthor(user);
		} else {
			throw new NotFoundException("Author was not found.");
		}
		String regexPattern = "(#\\w+)";

		Pattern p = Pattern.compile(regexPattern);
		Matcher m = p.matcher(tweetToSave.getContent());
		while (m.find()) {
			Hashtag hashtag = new Hashtag();
			String hashtagString = m.group(1);
			hashtagString = hashtagString.replace("#", "");
			Optional<Hashtag> optionalHashtag = hashtagRepository.findHashtagByLabel(hashtagString);
			if (optionalHashtag.isPresent()) {

				hashtag = optionalHashtag.get();
				hashtag.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));

			} else {
				hashtag.setLabel(hashtagString);
				hashtag.setFirstUsed(Timestamp.valueOf(LocalDateTime.now()));
				hashtag.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));

				hashtagRepository.saveAndFlush(hashtag);
			}
			tweetToSave.getHashtags().add(hashtag);
		}
		String regexPatternMention = "(@\\w+)";
		Pattern pattern = Pattern.compile(regexPatternMention);
		Matcher match = pattern.matcher(tweetToSave.getContent());
		while (match.find()) {

			String userString = match.group(1);
			userString = userString.replace("@", "");
			System.out.println(userString);
			Optional<User> optionalUser2 = userRepository.findByCredentialsUsername(userString);
			if (optionalUser2.isPresent()) {
				User user = optionalUser2.get();
				tweetToSave.getMentionedUsers().add(user);

			}
		}
		return new ResponseEntity<>(tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToSave)),
				HttpStatus.CREATED);
	}

	@Override
	public List<TweetResponseDto> getAllTweets() {
		List<Tweet> tweetList = new ArrayList<Tweet>();
		for (Tweet tweet : tweetRepository.findAll((Sort.by(Sort.Direction.DESC, "posted")))) {
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
    public List<HashtagDto> getTweetTags(Long tweetId) {
        Tweet chosenTweet = getTweet(tweetId);
		List<Hashtag> hashes = chosenTweet.getHashtags();
        return hashtagMapper.entitiesToDtos(hashes);
    }

	@Override
	public List<UserResponseDto> getTweetMentionedUsers(Long tweetId) {
		Tweet chosenTweet = getTweet(tweetId);
		List<User> mentionedUsers = chosenTweet.getMentionedUsers();
		return userMapper.entitiesToDtos(mentionedUsers);
	}

	@Override
	public TweetResponseDto repostTweet(Long tweetId, CredentialsDto credentialsDto) {
		User tweetAuthor = getTweet(tweetId).getAuthor();
		UserResponseDto userCheck = userService.getUserUsername(credentialsDto.getUsername());
		if (!tweetAuthor.equals(userCheck)) {
			throw new BadRequestException("The credentials don't match!");
		}
		Tweet repost = new Tweet();
		Tweet currentTweet = getTweet(tweetId);
		repost.setAuthor(tweetAuthor);
		repost.setRepostOf(currentTweet);
		repost.setMentionedUsers((List<User>) tweetRepository.getTweetsMentions(tweetId));
		repost.setLikes(tweetRepository.getTweetsLikes(tweetId));
		currentTweet.getReposts().add(repost);
		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(repost));
	}
}
