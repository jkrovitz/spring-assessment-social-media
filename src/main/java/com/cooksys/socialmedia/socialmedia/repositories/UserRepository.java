package com.cooksys.socialmedia.socialmedia.repositories;

<<<<<<< Updated upstream
public interface UserRepository {
=======
import com.cooksys.socialmedia.socialmedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long userId);
>>>>>>> Stashed changes
}
