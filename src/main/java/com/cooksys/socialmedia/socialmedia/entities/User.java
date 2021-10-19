package com.cooksys.socialmedia.socialmedia.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private long timestamp;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
