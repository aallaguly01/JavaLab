package ru.itis.javalab;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Main {
    public static void main(String[] args) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/ComputerFarm");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("qwerty");
        hikariConfig.setMaximumPoolSize(20);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        EntityManager entityManager = new EntityManager(dataSource);

        User user = new User(1L, "Aaa", "Allaguly", true);
        entityManager.createTable("reflection", User.class);
        entityManager.save("reflection", user);
        //User user1 = entityManager.findById("reflection", User.class, Long.class, 1L);
    }
}

