package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

// import org.springframework.http.HttpMethod;

// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;

// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// import javax.sql.DataSource;

@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().and()
                .cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/product/**").permitAll()
                .antMatchers("/productImg/**").permitAll()
                .antMatchers("/customer/login").permitAll()
                .antMatchers("/customer/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
        // .loginPage("/login").permitAll()
        // .and()
        // .logout().invalidateHttpSession(true)
        // .clearAuthentication(true)
        // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        // .logoutSuccessUrl("/logout-success").permitAll();

    }

}