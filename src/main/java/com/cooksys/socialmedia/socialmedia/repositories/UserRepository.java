package com.cooksys.socialmedia.socialmedia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.socialmedia.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<UserResponseDto> findAllUsersByDeletedFalse();

    Optional<User> findById(Long userId);

    Optional<User> findByCredentialsUsername(String username);

    Optional<User> findByCredentialsUsernameAndDeletedFalse(String string);
    
    @Query(value = "SELECT * FROM user_table ut"
			+ " WHERE ut.deleted=false", nativeQuery=true )
	List<User> findAllNonDeletedUsers();
}
