package com.cooksys.socialmedia.socialmedia.dtos;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HashtagResponseDto {
	
	private Long hashtagId;
	private String label;
	private Timestamp firstUsed;
	private Timestamp lastUsed;
//	private List<TweetResponseDto> tweets;
}
