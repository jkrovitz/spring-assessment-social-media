package com.cooksys.socialmedia.socialmedia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.socialmedia.socialmedia.entities.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Long>{
	
	Optional<Hashtag> findById(Long hashtagId);


}
