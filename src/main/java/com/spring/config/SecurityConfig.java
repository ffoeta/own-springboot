package com.spring.config;

import com.spring.security.jwt.JwtConfigurer;
import com.spring.security.jwt.JwtTokenProvider;
import com.spring.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;



    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(Constants.USER_ENDPOINT_V1).hasRole("USER")
                .antMatchers(Constants.LOGIN_ENDPOINT_V1).permitAll()
                .antMatchers(Constants.ADMIN_ENDPOINT_V1).hasRole("ADMIN")
                .antMatchers(Constants.REST_ENDPOINT_V1).permitAll()
                .antMatchers(Constants.USER_ENDPOINT_V2).hasRole("USER")
                .antMatchers(Constants.AUTH_ENDPOINT_V2).permitAll()
                .antMatchers(Constants.ADMIN_ENDPOINT_V2).hasRole("ADMIN")
                .antMatchers(Constants.ANON_ENDPOINT_V2).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
