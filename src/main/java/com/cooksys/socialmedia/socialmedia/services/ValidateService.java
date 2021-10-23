package com.cooksys.socialmedia.socialmedia.services;

public interface ValidateService {

	boolean validateUsernameExists(String username);

	boolean validateUsernameAvailable(String username);
	
	boolean hashtagExists(String label);

}
