package com.cooksys.socialmedia.socialmedia.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user_table")
@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @Column(name = "user_id")
    @SequenceGenerator(name = "user_ids")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "joined")
    private java.sql.Timestamp joined = Timestamp.valueOf(LocalDateTime.now());

    private boolean deleted;

    private Boolean isActive = true;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "firstName")),
            @AttributeOverride(name = "lastName", column = @Column(name = "lastName")),
            @AttributeOverride(name = "phone", column = @Column(name = "phone")),
            @AttributeOverride(name = "email", column = @Column(name = "email"))
    })
    private Profile profile;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "username", column = @Column(name = "username")),
            @AttributeOverride(name = "password", column = @Column(name = "password")),
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

    public boolean isDeleted() {
        return deleted;
    }

    public void userFollow(User userToAdd) {

        following.add(userToAdd);
    }

    public void userFollowing(User userToAdd) {

        followers.add(userToAdd);
    }

    public void userUnfollow(User userToRemove) {

        following.remove(userToRemove);
    }

    public void userUnfollowing(User userToRemove) {

        followers.remove(userToRemove);
    }

    public List<User> getUserFollowing() {
        return following;
    }

    public Boolean getIsActive() {
        return isActive;
    }

}
