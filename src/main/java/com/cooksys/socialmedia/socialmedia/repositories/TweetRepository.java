package com.cooksys.socialmedia.socialmedia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmedia.socialmedia.entities.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
	
	@Query("SELECT t FROM Tweet t WHERE t.deleted=false")
	List<Tweet> findAllNonDeletedTweets(List<Tweet> tweets);

}
