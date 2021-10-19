package com.cooksys.socialmedia.socialmedia.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Profile {

    private String firstName; //OPTIONAL
    private String lastName; //OPTIONAL
    private String email; //MANDATORY
    private String phone; //OPTIONAL

}
