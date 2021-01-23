package ru.itis.javalab.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;


public class LoginServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");

        usersService = applicationContext.getBean(UsersService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.randomUUID();

        if (usersService.signIn(req.getParameter("email") , req.getParameter("password"), uuid)){
            Cookie cookie = new Cookie("AuthCookie", uuid.toString());
            cookie.setMaxAge(60 * 60 * 24);
            resp.addCookie(cookie);
//            HttpSession session = req.getSession();
//            session.setAttribute("AuthSession", uuid.toString());
            resp.sendRedirect("/users");
        }
        else{
            resp.sendRedirect("/login");
        }
    }
}
