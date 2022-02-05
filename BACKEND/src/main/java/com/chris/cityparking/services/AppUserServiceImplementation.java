package com.chris.cityparking.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AppUser;
import com.chris.cityparking.controllers.Forms.LogInForm;
import com.chris.cityparking.modules.Role;
import com.chris.cityparking.repositories.AppUserRepo;
import com.chris.cityparking.repositories.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class AppUserServiceImplementation implements AppUserService, UserDetailsService {
    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    //login authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.getByEmail(username);
        if(appUser == null){
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        }else{
            log.info("User found in database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(appUser.getEmail(), appUser.getPassword(), authorities);
    }



    //save app user
    @Override
    public AppUser saveAppUser(AppUser appUser) {
        log.info("saving new user {} to the database", appUser.getFirstName());
        //appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepo.save(appUser);
    }

    //save role
    @Override
    public Role saveRole(Role role) {
        log.info("saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    //changing user password
    @Override
    public void changeUserPassword(EmailPasswordForm emailPasswordForm) {
        log.info("changing password for the user");
        AppUser user = appUserRepo.getByEmail(emailPasswordForm.getEmail());
        user.setPassword(passwordEncoder.encode(emailPasswordForm.getNewpassword()));
        appUserRepo.save(user);
    }

    //add role to user
    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        AppUser appUser = appUserRepo.getByEmail(username);
        Role role = roleRepo.findByName(roleName);

        appUser.getRoles().add(role);
    }

    //getting app user
    @Override
    public AppUser getAppUser(String userName) {
        log.info("fetching user {} ", userName);
        return appUserRepo.getByEmail(userName);
    }

    //getting all app users
    @Override
    public List<AppUser> getAppUsers() {
        log.info("fetching all users");
        return appUserRepo.findAll();
    }

    //token generation for logged in user
    @Override
    public String userLogin(LogInForm logInForm) {
        log.info("generating token for user");
        String username = logInForm.getUsername();
        String password = logInForm.getPassword();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        //return authenticationManager.authenticate(authenticationToken);

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60*60*1000))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                .sign(algorithm);


       String tokens = access_token;
        return tokens;
    }


}

/**
 * required services for app user include
 * login
 * token generation
 * assign role to user
 * save user
 * get user
 */