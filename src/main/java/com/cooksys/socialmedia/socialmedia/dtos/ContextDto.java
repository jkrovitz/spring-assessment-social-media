package com.cooksys.socialmedia.socialmedia.dtos;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ContextDto {

    private TweetResponseDto target;

    private List<TweetResponseDto> before;

    private List<TweetResponseDto> after;
    
    public ContextDto(TweetResponseDto target, List<TweetResponseDto> before, List<TweetResponseDto> after) {
        this.target = target;
        this.before = before;
        this.after = after;
    }
}
