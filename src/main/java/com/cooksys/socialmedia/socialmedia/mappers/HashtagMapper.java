package com.cooksys.socialmedia.socialmedia.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.socialmedia.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.socialmedia.entities.Hashtag;

@Mapper(componentModel = "spring", uses = { HashtagMapper.class })
public interface HashtagMapper {

    HashtagDto entityToDto(Hashtag hashtag);

    List<HashtagDto> entitiesToDtos(List<Hashtag> hashtags);
    
    Hashtag dtoToEntity(HashtagDto dto);

}
