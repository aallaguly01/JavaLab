package ru.itis.javalab.filters;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.itis.javalab.services.UsersService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//commented in web.xml working AuthFilter
public class SessionFilter implements Filter {

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

        HttpSession session = request.getSession();
        String uuid = (String) session.getAttribute("AuthSession");

        if(!usersService.checkAuthCookie(uuid)){
            response.sendRedirect("/login");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
