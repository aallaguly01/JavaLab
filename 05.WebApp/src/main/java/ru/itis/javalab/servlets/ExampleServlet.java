package ru.itis.javalab.servlets;


import org.springframework.context.ApplicationContext;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ExampleServlet", urlPatterns = {"/freemarkerPage"})
public class ExampleServlet extends HttpServlet {
    private UsersService usersServices;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        usersServices = applicationContext.getBean(UsersService.class);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        List<User> users = usersServices.getAllUsers();
        System.err.println(users.size());

        request.setAttribute("users", users);

        request.getRequestDispatcher("ftl/users.ftl").forward(request, response);
    }



}
