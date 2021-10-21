package com.cooksys.socialmedia.socialmedia.repositories;

import com.cooksys.socialmedia.socialmedia.dtos.ProfileDto;
import com.cooksys.socialmedia.socialmedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long userId);
    Optional<User> findByCredentialsUsername(String username);
    Optional<User> findByProfile(ProfileDto profileDto);
    User getUserByUsername(String string);

    Optional<User> findByCredentialsUsernameAndDeletedFalse(String username);
}
