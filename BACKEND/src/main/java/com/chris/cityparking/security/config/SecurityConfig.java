package com.chris.cityparking.security.config;

import com.chris.cityparking.security.filters.CustomAuthenticationFilter;
import com.chris.cityparking.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    //for authenticating the user during login
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }



    //restrain paths according the role of user
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        //customAuthenticationFilter.setFilterProcessesUrl("/apiv1/appUser/login");
        http.cors();
        http.csrf().disable();
        //http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/apiv1/appUser/login","/apiv1/motorist/save","/apiv1/admin/save", "/apiv1/appUser/role/save", "/ws").permitAll();
        http.authorizeRequests().antMatchers(GET, "/apiv1/attendant/getall","/apiv1/motorist/getall","/apiv1/blacklist/{numberPlate}","/apiv1/blacklist/all").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,  "/apiv1/attendant/save","/apiv1/parkingLot/save").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/apiv1/parkingLot/update","/apiv1/attendant/update").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/apiv1/attendant/{attendantID}","/apiv1/parkingdetails/all","/apiv1/parkingLot/all","/apiv1/parkingLot/{parkingRegNo}","/apiv1/blacklist/{numberPlate}").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/apiv1/blacklist/save").hasAnyAuthority("ROLE_ATTENDANT","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/apiv1/attendant/getDetails/{attendantID}","/apiv1/attendant/get/{email}","/apiv1/parkingdetails/all").hasAnyAuthority("ROLE_ATTENDANT","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/apiv1/attendant/updatePassword").hasAnyAuthority("ROLE_ATTENDANT","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/apiv1/motorist/getDetails/{motoristID}","/apiv1/motorist/get/{email}","/apiv1/parkingdetails/get/{numberPlate}").hasAnyAuthority("ROLE_MOTORIST","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/apiv1/parkingdetails/save/{email}").hasAnyAuthority("ROLE_MOTORIST","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/apiv1/motorist/update","/apiv1/motorist/updatePassword").hasAnyAuthority("ROLE_MOTORIST","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/apiv1/motorist/{motoristID}").hasAnyAuthority("ROLE_MOTORIST","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/apiv1/parkingdetails/get/{numberPlate}").hasAnyAuthority("ROLE_MOTORIST","ROLE_MOTORIST","ROLE_ADMIN");

        /*

        http.authorizeRequests().antMatchers("/apiv1/appUser/login/**","/apiv1/appUser/token/refresh/**", "/apiv1/booking/**", "/apiv1/parkingLot/get/**","/apiv1/parkingLot/all/**", "/apiv1/admin/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/apiv1/appUser/**", "/apiv1/attendant/getall/**", "/apiv1/motorist/getall/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/apiv1/appUser/**", "/apiv1/attendant/**","/apiv1/attendant/save","/apiv1/parkingLot/save/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/apiv1/attendant/**","/apiv1/parkingdetails/all/**","/apiv1/parkingLot/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/apiv1/parkingLot/update/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/apiv1/attendant/getDetails/**","/apiv1/parkingdetails/all/**").hasAnyAuthority("ROLE_ATTENDANT","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/apiv1/attendant/update/**").hasAnyAuthority("ROLE_ATTENDANT","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/apiv1/motorist/save/**","/apiv1/parkingdetails/save/**").hasAnyAuthority("ROLE_MOTORIST","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/apiv1/motorist/getDetails/**","/apiv1/parkingdetails/get/**").hasAnyAuthority("ROLE_MOTORIST","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/apiv1/motorist/update/**","/apiv1/parkingdetails/update/**").hasAnyAuthority("ROLE_MOTORIST","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/apiv1/motorist/**","/apiv1/parkingdetails/**").hasAnyAuthority("ROLE_MOTORIST","ROLE_ADMIN");

        */

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}

/**
 * a configuration class to handle the security of the application through
 * authentication of user
 * restricting the access to paths according to roles
 */




    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationTokenFilter will ignore the below paths
        web
                .ignoring()
                .antMatchers(
                        HttpMethod.POST,
                "/apiv1/appUser/login"
                );
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/apiv1/appUser/login");
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(OPTIONS,"/apiv1/appUser/login","/apiv1/motorist/**","/apiv1/appUser/token/refresh/**", "/apiv1/appUser/role/save").permitAll();
        http.authorizeRequests().antMatchers(GET, "/apiv1/appUser/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(POST, "/apiv1/appUser/save/**").hasAnyAuthority("ROLE_MOTORIST");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    */


