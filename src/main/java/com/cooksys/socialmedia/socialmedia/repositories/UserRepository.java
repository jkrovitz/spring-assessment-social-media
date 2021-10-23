package com.cooksys.socialmedia.socialmedia.repositories;

import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.socialmedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<UserResponseDto> findAllUsersByDeletedFalse();

    Optional<User> findById(Long userId);

    Optional<User> findByCredentialsUsername(String username);

    Optional<User> findByCredentialsUsernameAndDeletedFalse(String string);
    
//
//    @Query("SELECT i FROM user_table i WHERE i.deleted=false")
//    List<User> findAllNonDeletedUsers(List<User> user);
    
}
