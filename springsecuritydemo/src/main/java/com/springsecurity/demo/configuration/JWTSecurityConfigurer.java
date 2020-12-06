package com.springsecurity.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class JWTSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity security) throws Exception{
        security.authorizeRequests(auth -> auth.antMatchers(HttpMethod.GET,"/protectedresources/**").hasAuthority("SCOPE_read")
                .antMatchers(HttpMethod.POST,"/protectedresources").hasAuthority("SCOPE_write")
                .anyRequest().authenticated()).oauth2ResourceServer(oauth2 -> oauth2.jwt());

    }
}
