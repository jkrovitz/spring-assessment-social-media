package com.cooksys.socialmedia.socialmedia.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    private Profile profile;
    @Embedded
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
