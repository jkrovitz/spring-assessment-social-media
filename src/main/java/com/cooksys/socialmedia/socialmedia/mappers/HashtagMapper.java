package com.cooksys.socialmedia.socialmedia.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.http.HttpStatus;

import com.cooksys.socialmedia.socialmedia.dtos.HashtagRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.HashtagResponseDto;
import com.cooksys.socialmedia.socialmedia.entities.Hashtag;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

	Hashtag hashtagRequestDtoToEntity(HashtagRequestDto hashtagRequestDto);

	HashtagResponseDto hashtagEntityToResposneDto(Hashtag entity);

	List<HashtagResponseDto> hashtagEntitiesToResponseDtos(List<Hashtag> entities);

	Hashtag hashtagDtoToEntity(HashtagRequestDto hashtagRequestDto);

}
