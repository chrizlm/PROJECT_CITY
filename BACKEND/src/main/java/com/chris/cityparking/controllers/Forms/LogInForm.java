package com.chris.cityparking.controllers.Forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInForm{
    private String username;
    private String password;
}
/**
 * login details required in the request body of login
 */