package com.cooksys.socialmedia.socialmedia.services.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.socialmedia.dtos.HashtagRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.HashtagResponseDto;
import com.cooksys.socialmedia.socialmedia.mappers.HashtagMapper;
import com.cooksys.socialmedia.socialmedia.repositories.HashtagRepository;
import com.cooksys.socialmedia.socialmedia.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;
    private final HashtagMapper hashtagMapper;

    private void validateHashtagRequest(HashtagRequestDto hashtagRequestDtoa) {
    	
    }
    

	@Override
	public HashtagResponseDto createHashtag(HashtagRequestDto hashtagRequestDto) {
		validateHashtagRequest(hashtagRequestDto);
		return hashtagMapper.hashtagEntityToResposneDto(
				hashtagRepository.saveAndFlush(hashtagMapper.hashtagRequestDtoToEntity(hashtagRequestDto)));


}


	@Override
	public List<HashtagResponseDto> getAllHashtags() {
		// TODO Auto-generated method stub
		return hashtagMapper.hashtagEntitiesToResponseDtos(hashtagRepository.findAll());
	}
    
}
