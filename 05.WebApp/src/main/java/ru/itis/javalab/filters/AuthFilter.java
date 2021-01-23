package ru.itis.javalab.filters;

import org.springframework.context.ApplicationContext;
import ru.itis.javalab.services.UsersService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    private UsersService usersService;
    @Override
    public void init(FilterConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");

        usersService = applicationContext.getBean(UsersService.class);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;


        Cookie[] cookies = request.getCookies();
        boolean cookieFound = false;
        if (cookies != null){
            for (Cookie cookie: cookies){
                if("AuthCookie".equals(cookie.getName()))
                    if(usersService.checkAuthCookie((String) cookie.getValue())){
                        cookieFound = true;
                    }
            }
        }
        if (!cookieFound){
            response.sendRedirect("/login");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
