package com.cooksys.socialmedia.socialmedia.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tweet {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "tweet_ids")
    @Column(name = "tweet_id")
    private long id;

    @ManyToOne
    @JoinColumn
    private User author;

    private String content;

    private boolean deleted;

    @CreationTimestamp
    private Timestamp posted = Timestamp.valueOf(LocalDateTime.now());

    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Tweet inReplyTo;

    @OneToMany(mappedBy = "inReplyTo")
    private List<Tweet> replies;

    @ManyToOne
    @JoinColumn(name = "repost_id")
    private Tweet repostOf;

    @OneToMany(mappedBy = "repostOf")
    private List<Tweet> reposts;

    @ManyToMany
    @JoinTable
    private List<Hashtag> hashtags = new ArrayList<>();

    @ManyToMany
    @JoinTable
    private List<User> likes = new ArrayList<>();

    @ManyToMany
    @JoinTable
    private List<User> mentionedUsers = new ArrayList<>();

}
