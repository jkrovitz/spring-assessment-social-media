package com.cooksys.socialmedia.socialmedia.services.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.socialmedia.dtos.ContextDto;
import com.cooksys.socialmedia.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.socialmedia.entities.User;
import com.cooksys.socialmedia.socialmedia.exceptions.BadRequestException;
import com.cooksys.socialmedia.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.socialmedia.mappers.CredentialsMapper;
import com.cooksys.socialmedia.socialmedia.mappers.HashtagMapper;
import com.cooksys.socialmedia.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.socialmedia.mappers.UserMapper;
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
	
	private final CredentialsMapper credentialsMapper;
	

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
	
	public TweetResponseDto getTweetById(Long tweetId) {
		Tweet tweet = getTweet(tweetId);
		return tweetMapper.entityToDto(tweet);
	}
	
	private Tweet getTweetToLike(Long tweetId) {
		Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);
		if (optionalTweet.isEmpty()) {
			throw new NotFoundException("There was no tweet found with id " + tweetId + ".");
		}
		return optionalTweet.get();
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
	
	
	private Tweet postTweetHelper(TweetRequestDto tweetRequestDto) {
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
		return tweetToSave;
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
	public List<TweetResponseDto> getTweetReposts(Long tweetId) {
		Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);
		List<Tweet> tweetList = new ArrayList<Tweet>();
		if (optionalTweet.isEmpty()) {
			throw new NotFoundException("Tweet not found");
		} else {
			tweetList = tweetRepository.findByRepostId(tweetId);			
		}
		if (tweetList.equals(null)) {
			throw new NotFoundException ("No reposts found");
		}
		else {
				
		return tweetMapper.entitiesToDtos(tweetList);	
			
		}
	 
	}
	
	
	@Override
	public void addTweetLike(Long tweetId, UserRequestDto userRequestDto) {
		Optional<User> optionalUser = userRepository
				.findByCredentialsUsernameAndDeletedFalse(userRequestDto.getCredentials().getUsername());
		if (optionalUser.isPresent()) {
			if (optionalUser.get().getCredentials().getPassword()
					.equals(userRequestDto.getCredentials().getPassword())) {
				User user = optionalUser.get();
				Tweet tweetToLike = getTweetToLike(tweetId);
				tweetToLike = tweetRepository.getById(tweetId);
				tweetToLike.getLikes().add(user);
				tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToLike));
				userMapper.entityToDto(userRepository.saveAndFlush(user));
			}
		} else {
			throw new NotFoundException("User with credentials " + userRequestDto.getCredentials().getUsername()
					+ " along with the password entered were either not found or were deleted.");
		}
	}

	@Override
	public List<UserResponseDto> getTweetLikes(Long tweetId) {
		Tweet tweetToLike = getTweetToLike(tweetId);
		tweetToLike = tweetRepository.getById(tweetId);
		return userMapper.entitiesToDtos(tweetToLike.getLikes());
	}	
	
	@Override
	public List<TweetResponseDto> tweetReplies(Long tweetId) {
		Tweet findRepliesOf = tweetRepository.getById(tweetId);
		List<Tweet> allReplies = new ArrayList<>();
		for (Tweet t : tweetRepository.findAll()) {
			if ((t.getInReplyTo() == findRepliesOf) && (!(t.isDeleted()))) {
				allReplies.add(t);
			}
		}
		return tweetMapper.entitiesToDtos(allReplies);
	}
	
	@Override
	public TweetResponseDto addTweetReply(Long tweetId, TweetRequestDto tweetRequestDto) {
		Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);
		if (optionalTweet.isPresent()) {
			Tweet tweet = optionalTweet.get();
			Tweet newTweet = postTweetHelper(tweetRequestDto);
			newTweet.setInReplyTo(tweet);
			return tweetMapper.entityToDto(tweetRepository.saveAndFlush(newTweet));			
		} else {
			throw new NotFoundException("Tweet not found.");
		}
		
	}

	@Override
	public TweetResponseDto createTweetRepost(Long tweetId, CredentialsDto credentialsDto) {
		Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);
		Optional<User> optionalUser = userRepository.findByCredentialsUsername(credentialsDto.getUsername());
		if (optionalTweet.isPresent() && optionalUser.isPresent()) {
			Tweet tweet = optionalTweet.get();
			User originalAuthor = tweet.getAuthor();
			TweetRequestDto tweetRequestDto = tweetMapper.entityToRequestDto(tweet);
			Credentials originalCredentials = originalAuthor.getCredentials();
			CredentialsDto originalCredentialsDto = credentialsMapper.entityToDto(originalCredentials);
			tweetRequestDto.setCredentials(originalCredentialsDto);			
			TweetResponseDto tweetResponseDto = getTweetById(tweetId);
			Tweet updatedTweet = tweetMapper.responseDtoToEntity(tweetResponseDto);
			TweetRequestDto newTweetRequestDto = tweetMapper.entityToRequestDto(updatedTweet);
			newTweetRequestDto.setCredentials(credentialsDto);
			Tweet newTweet = postTweetHelper(newTweetRequestDto);
			newTweet.setRepostOf(updatedTweet);		
			return tweetMapper.entityToDto(tweetRepository.saveAndFlush(newTweet));			
			
		} else {
			throw new NotFoundException("Tweet not found.");
		}
	}
	
	private Set<Tweet> setBeforeInReplyToList(Tweet tweet, Set<Tweet> inReplyToList) {
		if (tweet != null) {
			if (!inReplyToList.contains(tweet)) {
				if (!tweet.isDeleted()) {
					inReplyToList.add(tweet);
				}
				setBeforeInReplyToList(tweet.getInReplyTo(), inReplyToList);
			}
		}
		return inReplyToList;
	}

	private Set<Tweet> setAfterInReplyToList(Tweet tweet, HashSet<Tweet> inReplyToList) {
		Set<Tweet> list = tweetRepository.findByInReplyToOrderByPostedDesc(tweet);
		for (Tweet t : list) {
			if (t != null) {
				if (!inReplyToList.contains(tweet)) {
					if (!t.isDeleted()) {
						inReplyToList.add(t);
					}
					setAfterInReplyToList(t, inReplyToList);
				}
			}
		}
		return inReplyToList;
	}

	@Override
	public ContextDto context(Long id) {
		Tweet tweet = getTweet(id);
		TweetResponseDto target = tweetMapper.entityToDto(tweet);
		SortedSet<Tweet> beforeList = new TreeSet<>(Comparator.comparing(Tweet::getPosted));
		setBeforeInReplyToList(tweet.getInReplyTo(), beforeList);
		List<TweetResponseDto> before = tweetMapper.setEntitiesToDtos(beforeList);
		HashSet<Tweet> afterListTemp = new HashSet<>();
		setAfterInReplyToList(tweet, afterListTemp);
		SortedSet<Tweet> afterList = new TreeSet<>(Comparator.comparing(Tweet::getPosted));
		afterList.addAll(afterListTemp);
		List<TweetResponseDto> after = tweetMapper.setEntitiesToDtos(afterList);
		return new ContextDto(target, before, after);
	}
	
}
