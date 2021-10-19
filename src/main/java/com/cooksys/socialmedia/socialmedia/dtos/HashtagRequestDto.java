package com.cooksys.socialmedia.socialmedia.dtos;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HashtagRequestDto {
	
	private String label;
	private Timestamp firstUsed;
	private Timestamp lastUsed;
//	private List<TweetRequestDto> tweets;
}
