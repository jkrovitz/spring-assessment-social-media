package com.cooksys.socialmedia.socialmedia.mappers;

import java.util.List;

import com.cooksys.socialmedia.socialmedia.dtos.ProfileDto;
import com.cooksys.socialmedia.socialmedia.entities.Profile;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile dtoToEntity(ProfileDto profileDto);

    ProfileDto entityToDto(Profile profile);

}
