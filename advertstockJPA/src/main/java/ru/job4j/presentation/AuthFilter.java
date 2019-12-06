package ru.job4j.presentation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURI().contains("/signin")) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession();
            if (session.getAttribute("userId") == null) {
                ((HttpServletResponse) response).sendRedirect(String.format("%s/signin", req.getContextPath()));
                return;
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
