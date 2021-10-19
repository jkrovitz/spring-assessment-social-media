package com.cooksys.socialmedia.socialmedia.mappers;

import java.util.List;

import com.cooksys.socialmedia.socialmedia.dtos.HashtagDto;
import com.cooksys.socialmedia.socialmedia.entities.Hashtag;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

    HashtagDto entityToDto(Hashtag entity);

    List<HashtagDto> entitiesToDtos(List<Hashtag> entities);

}
