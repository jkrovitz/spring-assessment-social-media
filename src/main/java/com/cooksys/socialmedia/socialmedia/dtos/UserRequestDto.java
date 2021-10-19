package com.cooksys.socialmedia.socialmedia.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class UserRequestDto {

    private long userId;
    private String username;
    private String password;
    private long timestamp;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    // NEEDS LIST
}
