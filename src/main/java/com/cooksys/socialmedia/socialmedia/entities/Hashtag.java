package com.cooksys.socialmedia.socialmedia.entities;

import java.sql.Timestamp;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Hashtag {

    @Id
    @SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_JUST_FOR_TEST", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
    @Column(name="hashtag_id")
    private Long hashtagId;

    @Column(unique = true)
    private String label;

    private Timestamp firstUsed;

    private Timestamp lastUsed;

    @ManyToMany
    @JoinTable(
            name = "tweet_hashtag",
            joinColumns = @JoinColumn(name = "hashtag_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    private List<Tweet> tweets;
}
