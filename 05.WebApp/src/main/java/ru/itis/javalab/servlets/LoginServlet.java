package ru.itis.javalab.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Map map = new HashMap<String, String>();
//        map.put(req.getParameter("uname"), req.getParameter("psw"));
        String psw = req.getParameter("psw");
        String uname = req.getParameter("uname");
        Cookie cookiepsw = new Cookie("password", psw);
        Cookie cookieuname = new Cookie("username", uname);

        //Cookie cookie = new Cookie("Auth", map);
        cookiepsw.setMaxAge(60 * 60 * 24 * 365);
        cookieuname.setMaxAge(60 * 60 * 24 * 365);

        resp.addCookie(cookiepsw);
        resp.addCookie(cookieuname);
        resp.sendRedirect("/login");


    }


}
