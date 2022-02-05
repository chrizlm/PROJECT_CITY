package com.chris.cityparking.modules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class AdminDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    //national ID( use later)
    private Long adminID;

    private String firstName;

    private String lastName;

    //use as natural id
    private String email;

    private String gender;

    private String password;

}

/**
 * basic needed admin details
 */