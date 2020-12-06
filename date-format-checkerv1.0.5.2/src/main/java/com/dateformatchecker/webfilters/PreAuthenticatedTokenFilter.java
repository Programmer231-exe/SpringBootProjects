package com.dateformatchecker.webfilters;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
@WebFilter
public class PreAuthenticatedTokenFilter extends AbstractPreAuthenticatedProcessingFilter {

    private String authHeaderName;

    public PreAuthenticatedTokenFilter(String authHeaderName){
        this.authHeaderName = authHeaderName;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request)
    {
        return request.getHeader(authHeaderName);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request){
        return "N/A";
    }
}
