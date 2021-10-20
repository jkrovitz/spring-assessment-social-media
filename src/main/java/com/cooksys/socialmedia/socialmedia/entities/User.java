package com.cooksys.socialmedia.socialmedia.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Table(name = "user_table")
@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @Column(name = "user_id")
    @SequenceGenerator(name = "user_ids")
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    @Column(name = "joined")
    private java.sql.Timestamp joined = Timestamp.valueOf(LocalDateTime.now());

    private boolean deleted;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "firstName", column = @Column(name = "firstName")),
            @AttributeOverride( name = "lastName", column = @Column(name = "lastName")),
            @AttributeOverride( name = "phone", column = @Column(name = "phone")),
            @AttributeOverride( name = "email", column = @Column(name = "email"))
    })
    private Profile profile;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "username", column = @Column(name = "username")),
            @AttributeOverride( name = "password", column = @Column(name = "password")),
    })
    private Credentials credentials;

    @OneToMany(mappedBy = "author")
    private List<Tweet> tweets;

    @ManyToMany
    @JoinTable
    private List<User> followers;

    @ManyToMany(mappedBy = "followers")
    private List<User> following;

    @ManyToMany(mappedBy = "likes")
    private List<Tweet> likedTweets;

    @ManyToMany(mappedBy = "mentionedUsers")
    private List<Tweet> mentions;

}
