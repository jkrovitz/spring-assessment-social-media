package com.cooksys.socialmedia.socialmedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotAuthorizedException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3633895901059670640L;
	private String message;
}
