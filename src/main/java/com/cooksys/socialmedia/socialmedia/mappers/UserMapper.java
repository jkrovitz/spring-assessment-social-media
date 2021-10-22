package com.cooksys.socialmedia.socialmedia.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.socialmedia.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.socialmedia.entities.User;

@Mapper(componentModel = "spring", uses = { CredentialsMapper.class, ProfileMapper.class })
public interface UserMapper {

    User dtoToEntity(UserRequestDto userRequestDto);

    @Mapping(target = "username", source = "credentials.username")
    UserResponseDto entityToDto(User entity);

    List<UserResponseDto> entitiesToDtos(List<User> entities);

    User requestDtoToEntity(UserRequestDto userRequestDto);
}
