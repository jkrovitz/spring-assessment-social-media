package com.cooksys.socialmedia.socialmedia.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.socialmedia.entities.User;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

	@Query("SELECT t FROM Tweet t WHERE t.deleted=false")
	List<Tweet> findAllNonDeletedTweets(List<Tweet> tweets);

	Optional<Tweet> findById(Long tweetId);

	List<TweetResponseDto> findByDeletedFalseAndAuthorOrderByPostedDesc(String username);

	@Query(value = "SELECT * FROM Tweet t WHERE t.repost_id = ?1", nativeQuery = true)
	List<Tweet> findByRepostId(Long tweetId);

	List<Tweet> findByAuthorAndDeletedFalse(User author);

	@Query(value = "SELECT * FROM user_table ut"
			+ " LEFT JOIN user_table_followers utf ON (ut.user_id = utf.followers_user_id)"
			+ " JOIN tweet tw ON (ut.user_id = tw.author_user_id)"
			+ " WHERE ut.username = :username", nativeQuery = true)
	List<Tweet> queryBy(String username);

	Set<Tweet> findByInReplyToOrderByPostedDesc(Tweet inReplyTo);

}
