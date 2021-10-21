package com.cooksys.socialmedia.socialmedia;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksys.socialmedia.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.socialmedia.entities.Profile;
import com.cooksys.socialmedia.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.socialmedia.entities.User;
import com.cooksys.socialmedia.socialmedia.repositories.HashtagRepository;
import com.cooksys.socialmedia.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.socialmedia.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner{
	
	private final UserRepository userRepository;
	private final HashtagRepository hashtagRepository;
	private final TweetRepository tweetRepository;
	
	@Override
	public void run(String... args) throws Exception {
//		Credentials credential1 = new Credentials();
//		credential1.setUsername("testuser1");
//		credential1.setPassword("password1");
//
//		Credentials credential2 = new Credentials();
//		credential2.setUsername("testuser2");
//		credential2.setPassword("password2");
//
//		Profile profile1 = new Profile();
//		profile1.setEmail("test1@test.com");
//		profile1.setFirstName("Joe");
//		profile1.setLastName("Shmoe");
//		profile1.setPhone("1111111111");
//
//		Profile profile2 = new Profile();
//		profile2.setEmail("test2@test.com");
//		profile2.setFirstName("Jon");
//		profile2.setLastName("Smith");
//		profile2.setPhone("2222222222");
//
//		User user1 = new User();
//		user1.setCredentials(credential1);
//		user1.setDeleted(false);
//		user1.setProfile(profile1);
//
//		User user2 = new User();
//		user2.setCredentials(credential2);
//		user2.setDeleted(false);
//		user2.setProfile(profile2);
//
//		userRepository.saveAll(Arrays.asList(new User[] {user1, user2}));
//
//		Hashtag hashtag1 = new Hashtag();
//		hashtag1.setLabel("#testhashtag1");
//
//
//		Hashtag hashtag2 = new Hashtag();
//		hashtag2.setLabel("#testhashtag2");
//
//		hashtagRepository.saveAll(Arrays.asList(new Hashtag[] {hashtag1, hashtag2}));
//
//		Tweet tweet1 = new Tweet();
//		tweet1.setContent("tweet 1");
//		tweet1.setDeleted(false);
//		Timestamp timestamp1 = Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2018, 10, 7), LocalTime.of(8, 45, 0)));
//		String dateTime = "2020-12-12 01:24:23";
//
//        Timestamp timestamp2 = Timestamp.valueOf(dateTime);
//
//
//		tweet1.setPosted("2021-10-20T04:43:17.854+00:00");
//
//		tweet1.setAuthor(user1);
//
//		Tweet tweet2 = new Tweet();
//		tweet2.setContent("tweet 2");
//		tweet2.setDeleted(false);
//		tweet2.setPosted(new Timestamp(System.currentTimeMillis()));
//		tweet2.setAuthor(user2);
//
//		tweetRepository.saveAll(Arrays.asList(new Tweet[] {tweet1, tweet2}));
	}
	


}
