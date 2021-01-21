package ru.itis.javalab.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.itis.javalab.services.UserServicesImpl;
import ru.itis.javalab.services.UsersService;

import javax.sql.DataSource;

@Configuration

//@PropertySource("classpath:db.properties")
//@ComponentScan(basePackages = "ru.itis.javalab")

public class ApplicationConfig {

    //first version is get from context.xml with @Autowired->
    @Autowired //puts all properties of hikariConfig( from context.xml) into "hikariConfig"
    private HikariConfig hikariConfig;

    //second version is create all in hikari method HikariConfig with Environment
//    @Autowired
//    private Environment environment;
//
//    @Bean
//    public HikariConfig hikariConfig(){
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
//        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
//        hikariConfig.setUsername(environment.getProperty("db.username"));
//        hikariConfig.setPassword(environment.getProperty("db.password"));
//        hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
//        return hikariConfig;
//
//    }

    @Bean
    public DataSource dataSource(){
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public UsersRepository usersRepository(){
        return new UsersRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public UsersService usersService(){
        return new UserServicesImpl(usersRepository());
    }


    @Bean //created Bean with id = ObjectMapper
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
