package com.chris.cityparking;

import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.modules.AppUser;
import com.chris.cityparking.modules.Role;
import com.chris.cityparking.services.AdminDetailService;
import com.chris.cityparking.services.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class CityParkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityParkingApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /*
    @Bean
    CommandLineRunner run(AppUserService appUserService, AdminDetailService adminDetailService){
        return args -> {
            appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
            appUserService.saveRole(new Role(null, "ROLE_ATTENDANT"));
            appUserService.saveRole(new Role(null, "ROLE_MOTORIST"));


            adminDetailService.createAdminDetail(new AdminDetails(null, "Admin","trial1", "admin1trial1@gmail.com", "male", "1234"));


            appUserService.addRoleToUser("admin1trial1@gmail.com", "ROLE_ADMIN");
        };
    }
    */

}
