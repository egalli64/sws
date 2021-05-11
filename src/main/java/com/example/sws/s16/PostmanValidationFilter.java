package com.example.sws.s16;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PostmanValidationFilter implements Filter {
    private static final Logger log = LogManager.getLogger(PostmanValidationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String agent = ((HttpServletRequest) request).getHeader("User-Agent");
        if (agent == null || !agent.contains("Postman")) {
            log.warn("Only Postman agent allowed *** " + agent);
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        log.debug(agent);
        chain.doFilter(request, response);
    }

}
