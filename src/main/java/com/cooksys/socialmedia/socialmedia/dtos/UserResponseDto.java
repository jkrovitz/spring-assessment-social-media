package com.cooksys.socialmedia.socialmedia.dtos;

import java.sql.Timestamp;
import java.util.List;

import com.cooksys.socialmedia.socialmedia.entities.Tweet;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

    private String username;

    private ProfileDto profile;

    private Timestamp joined;

}
