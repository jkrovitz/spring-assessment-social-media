package com.cooksys.socialmedia.socialmedia.controllers;

import java.util.List;

import com.cooksys.socialmedia.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.socialmedia.services.HashtagService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class HashtagController {

    private final HashtagService hashtagService;

    @GetMapping
    public List<HashtagDto> getAllHashtags() {
        return hashtagService.getAllHashtags();
    }
}
