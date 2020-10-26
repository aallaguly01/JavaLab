package ru.itis.javalab.services;

import ru.itis.javalab.models.Auth;
import ru.itis.javalab.repositories.AuthJdbcImpl;

import java.util.Optional;


public class AuthServicesImpl implements AuthService{
    private AuthJdbcImpl authJdbc;

    public AuthServicesImpl(AuthJdbcImpl authJdbc) {
        this.authJdbc = authJdbc;
    }

    @Override
    public void save(Auth entity) {
        authJdbc.save(entity);
    }

    @Override
    public Optional<Auth> findByLoginPassword(String login, String password) {
        return authJdbc.findByLoginPassword(login, password);
    }
}
