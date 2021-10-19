package com.cooksys.socialmedia.socialmedia.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private Timestamp joined;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    //DOUBLE CHECK RELATIONSHIPS
    @OneToMany(mappedBy = "user")
    private List<Tweet> tweets;

    @ManyToMany
    @JoinTable(
            name = "user_tweets",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Tweet> likedTweets;
    private List<User> followers; //NEEDS TO FIX RELATIONSHIP
    private List<User> following; //NEEDS TO FIX RELATIONSHIP
    private List<Tweet> mentions;
}
