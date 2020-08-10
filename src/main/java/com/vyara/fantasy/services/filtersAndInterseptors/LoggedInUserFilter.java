package com.vyara.fantasy.services.filtersAndInterseptors;


import com.vyara.fantasy.services.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoggedInUserFilter implements Filter {
    private final AuthenticatedUserService authenticatedUserService;

    @Autowired
    public LoggedInUserFilter(AuthenticatedUserService authenticatedUserService) {
        this.authenticatedUserService = authenticatedUserService;
    }


    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        String username = authenticatedUserService.getUsername();
        if(username.equals("anonymousUser")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        session.setAttribute("username", username);
        String email = authenticatedUserService.getCurrentUser().getEmail();
        session.setAttribute("email", email);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}