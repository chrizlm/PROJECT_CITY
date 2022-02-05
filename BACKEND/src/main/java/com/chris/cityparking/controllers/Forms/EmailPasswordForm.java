package com.chris.cityparking.controllers.Forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailPasswordForm {
   private String email;
   private String newpassword;
}


/**
 * A general form for changing passwords for users
 */