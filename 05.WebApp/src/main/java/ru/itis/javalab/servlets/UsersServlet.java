package ru.itis.javalab.servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.SimpleJdbcTemplate;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersServlet extends HttpServlet {

    private UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/WebApplication");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("B1gather");
        hikariConfig.setMaximumPoolSize(20);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        //SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersRepository.findAll();
        System.out.println(users);
    }
}
