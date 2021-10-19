package com.cooksys.socialmedia.socialmedia.controllers;

import com.cooksys.socialmedia.socialmedia.dtos.HashtagRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.HashtagResponseDto;
import com.cooksys.socialmedia.socialmedia.services.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hashtag")
@RequiredArgsConstructor
public class HashtagController {

    private final HashtagService hashtagService;


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HashtagResponseDto createHashtag(@RequestBody HashtagRequestDto hashtagRequestDto) {
		return hashtagService.createHashtag(hashtagRequestDto);
	}
	
	@GetMapping
	public List<HashtagResponseDto> getAllHashtags() {
		return hashtagService.getAllHashtags();
	}
}
