package com.cooksys.socialmedia.socialmedia.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class Profile {

    private String firstName; // OPTIONAL
    private String lastName; // OPTIONAL
    private String phone; // OPTIONAL
    @Column(nullable = false)
    private String email; // MANDATORY

}
