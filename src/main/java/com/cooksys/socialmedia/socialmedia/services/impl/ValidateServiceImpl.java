package com.cooksys.socialmedia.socialmedia.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.socialmedia.entities.User;
import com.cooksys.socialmedia.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.socialmedia.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

	private final UserRepository userRepository;

	@Override
	public boolean validateUsernameExists(String username) {
		Optional<User> chosenUser = userRepository.findByCredentialsUsername(username);
		if (!chosenUser.isEmpty()) {
			User user = chosenUser.get();
			System.out.println(user.getCredentials().getUsername());
			if ((user.getCredentials().getUsername()).equals(username)) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	@Override
	public boolean validateUsernameAvailable(String username) {
		if (!(validateUsernameExists(username))) {
			return true;
		} else {
			return false;
		}
	}

}
