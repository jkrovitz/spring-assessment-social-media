package com.cooksys.socialmedia.socialmedia.services;

import com.cooksys.socialmedia.socialmedia.dtos.HashtagRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.HashtagResponseDto;

import java.util.List;


public interface HashtagService {

    HashtagResponseDto createHashtag(HashtagRequestDto hashtagRequestDto);

    List<HashtagResponseDto> getAllHashtags();
}
