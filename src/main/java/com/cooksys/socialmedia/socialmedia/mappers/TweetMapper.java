package com.cooksys.socialmedia.socialmedia.mappers;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.cooksys.socialmedia.socialmedia.dtos.TweetRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.entities.Tweet;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TweetMapper {

    TweetResponseDto entityToDto(Tweet tweet);

    List<TweetResponseDto> entitiesToDtos(List<Tweet> tweet);
    
    Tweet dtoToEntity(TweetRequestDto dto);
    
    TweetRequestDto entityToRequestDto(Tweet tweet);
    
    Tweet responseDtoToEntity(TweetResponseDto tweetResponseDto);

    List<TweetResponseDto> setEntitiesToDtos(Set<Tweet> entities);
}
