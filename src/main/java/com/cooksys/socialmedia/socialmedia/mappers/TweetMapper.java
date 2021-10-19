package com.cooksys.socialmedia.socialmedia.mappers;

import java.util.List;

import com.cooksys.socialmedia.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.socialmedia.entities.Tweet;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TweetMapper {

    TweetResponseDto entityToDto(Tweet tweet);

    List<TweetResponseDto> entitiesToDtos(List<Tweet> tweets);

}
