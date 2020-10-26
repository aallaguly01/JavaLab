package ru.itis.javalab.services;

import ru.itis.javalab.models.Auth;

import java.util.Optional;

public interface AuthService {

    void save(Auth entity);
    Optional<Auth> findByLoginPassword(String login, String password);
}
