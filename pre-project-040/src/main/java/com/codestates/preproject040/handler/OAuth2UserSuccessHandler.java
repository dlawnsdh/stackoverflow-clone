package com.codestates.preproject040.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class OAuth2UserSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("------------------------------------");
        log.info("**log** : Authentication : {}", authentication.getPrincipal());
        log.info("**log** : SecurityContext : {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        log.info("**log** : Session : {}", request.getSession(false));
        log.info("------------------------------------");

        HttpSession session = request.getSession(false);
        session.setMaxInactiveInterval(10000);
        response.sendRedirect("/");
    }

}
