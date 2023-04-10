package com.example.coresecurity.security.common;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FormWebAuthenticationDetails extends WebAuthenticationDetails {

    private  String secretKey;
    private String something;

    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        secretKey = request.getParameter("secret_key");
        something = request.getParameter("something");
    }

    public String getSecretKey() {

        return secretKey;
    }
}