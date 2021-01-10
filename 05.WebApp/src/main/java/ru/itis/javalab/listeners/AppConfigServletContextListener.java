package ru.itis.javalab.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class AppConfigServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
//        old version--------------------------------------------
//        Properties properties = new Properties();
//        try {
//            properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/db.properties"));
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(properties.getProperty("db.jdbc.url"));
//        hikariConfig.setDriverClassName(properties.getProperty("db.jdbc.driver-class-name"));
//        hikariConfig.setUsername(properties.getProperty("db.jdbc.username"));
//        hikariConfig.setPassword(properties.getProperty("db.jdbc.password"));
//        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.jdbc.hikari.max-pool-size")));
//
//        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//
//        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
//        UsersService usersService = new UserServicesImpl(usersRepository);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        servletContext.setAttribute("dataSource", dataSource);
//        servletContext.setAttribute("usersService", usersService);
//
//        servletContext.setAttribute("objectMapper", objectMapper);
//  --------------------------------------------------------------------------
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        servletContext.setAttribute("applicationContext", context);
        //------------------------------------------------------------------------------------------


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
