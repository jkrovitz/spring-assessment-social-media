package com.cooksys.socialmedia.socialmedia.dtos;

import java.sql.Timestamp;
import java.util.List;

import com.cooksys.socialmedia.socialmedia.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDto {

    private Long id;

    private UserResponseDto author;

    private Timestamp posted;

    private String content;

    private TweetResponseDto inReplyTo;

    private TweetRequestDto repostOf;
    
    private List<User> following;

}
