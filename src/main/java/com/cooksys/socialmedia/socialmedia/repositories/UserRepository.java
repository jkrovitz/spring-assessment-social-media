package com.cooksys.socialmedia.socialmedia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmedia.socialmedia.entities.User;



import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long userId);

    Optional<User> findByCredentialsUsername(String username);

    Optional<User> findByCredentialsUsernameAndDeletedFalse(String string);

    @Query("SELECT i FROM User i WHERE i.deleted=false")
    List<User> findAllNonDeletedUsers(List<User> user);
}
