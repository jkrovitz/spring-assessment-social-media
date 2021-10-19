package com.cooksys.socialmedia.socialmedia.entities;

<<<<<<< Updated upstream
public class User {
=======
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name="user_table")
@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue
    private Long userId;

    private String username;
    private String password;

    @Column(name = "joined")
    private Timestamp joined;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
>>>>>>> Stashed changes
}
