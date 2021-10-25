package com.cooksys.socialmedia.socialmedia.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "replies", "reposts"})
    private long id;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private User author;

    private String content;

    private boolean deleted;

    @CreationTimestamp
    @JsonBackReference
    private Timestamp posted = Timestamp.valueOf(LocalDateTime.now());

    @JsonIgnoreProperties("replies")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    @JsonBackReference
    private Tweet inReplyTo;

    @JsonIgnoreProperties("inReplyTo")
    @OneToMany(mappedBy = "inReplyTo", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Tweet> replies;

    @JsonIgnoreProperties("reposts")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repost_id")
    @JsonBackReference
    private Tweet repostOf;

    @JsonIgnoreProperties("repostOf")
    @OneToMany(mappedBy = "repostOf", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Tweet> reposts;

    @JsonIgnoreProperties("tweets")
    @ManyToMany
    @JoinTable
    private List<Hashtag> hashtags = new ArrayList<>();

    @JsonIgnoreProperties("likedTweets")
    @ManyToMany
    @JoinTable
    private List<User> likes;

    @JsonIgnoreProperties("mentions")
    @ManyToMany
    @JoinTable
    private List<User> mentionedUsers = new ArrayList<>();

}
