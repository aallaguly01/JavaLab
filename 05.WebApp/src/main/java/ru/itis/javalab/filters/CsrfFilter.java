package ru.itis.javalab.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class CsrfFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getMethod().equals("POST")) {
            String requestCsrf = request.getParameter("_csrf_token");
            String sessionCsrf = (String) request.getSession(false).getAttribute("_csrf_token");
            if (sessionCsrf.equals(requestCsrf)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                response.sendRedirect("/login");
                return;
            }
        }
        if (request.getMethod().equals("GET")) {
            String csrf = UUID.randomUUID().toString();
            request.setAttribute("_csrf_token", csrf);
            request.getSession().setAttribute("_csrf_token", csrf);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
