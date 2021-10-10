package com.program.mhb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // TODO Varianta_1 --> inMemoryAuthentification()
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // TODO a trebuit sa-i dau CSRF disable ca sa mearga in Postman + @RequestBody on create
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**", "/customers/**", "/accounts/**").permitAll()//.hasRole("ADMIN")//permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and().exceptionHandling()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
