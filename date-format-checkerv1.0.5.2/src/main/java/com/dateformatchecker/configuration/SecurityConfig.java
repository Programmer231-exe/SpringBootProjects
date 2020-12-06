package com.dateformatchecker.configuration;

import com.dateformatchecker.webfilters.PreAuthenticatedTokenFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Value("${security.http.auth.authHeaderName}")
    private String authHeaderName;
    @Value("${security.http.auth.authHeaderValue}")
    private String authHeaderValue;

    private static Logger logger = LogManager.getLogger();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       PreAuthenticatedTokenFilter filter = new PreAuthenticatedTokenFilter(authHeaderName);
       filter.setAuthenticationManager(
               new AuthenticationManager() {
                   @Override
                   public Authentication authenticate(Authentication authentication){
                        String principal = (String) authentication.getPrincipal();

                        if(!authHeaderValue.equals(principal)){
                            logger.info("This is working");
                            throw new BadCredentialsException("The API key was not found " +
                                    "or not the expected key");
                        }
                        authentication.setAuthenticated(true);
                        return authentication;
                 }
               }
       );

       http.antMatcher("/**")
               .csrf().disable()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .addFilter(filter)
               .authorizeRequests()
               .anyRequest()
               .authenticated();
    }


}