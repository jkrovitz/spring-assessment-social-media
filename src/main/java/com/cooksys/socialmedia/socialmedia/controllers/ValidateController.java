package com.cooksys.socialmedia.socialmedia.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.socialmedia.services.ValidateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {
	
	private final ValidateService validateService;
	
	@GetMapping("/username/exists@{username}")
    public boolean validateUsernameExists(@PathVariable String username) {
        return validateService.validateUsernameExists(username);
    }
	
	@GetMapping("/username/available@{username}")
    public boolean validateUsernameAvailable(@PathVariable String username) {
        return validateService.validateUsernameAvailable(username);
    }
}
