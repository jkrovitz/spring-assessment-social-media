package com.cooksys.socialmedia.socialmedia.services.impl;

import java.util.List;

import com.cooksys.socialmedia.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.socialmedia.mappers.HashtagMapper;
import com.cooksys.socialmedia.socialmedia.repositories.HashtagRepository;
import com.cooksys.socialmedia.socialmedia.services.HashtagService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;
    private final HashtagMapper hashtagMapper;

    @Override
    public List<HashtagDto> getAllHashtags() {
//        return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
    	return null;
    }

}
