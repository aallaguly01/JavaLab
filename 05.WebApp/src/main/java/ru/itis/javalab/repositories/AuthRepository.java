package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Auth;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<Auth> {
    Optional<Auth> findByLoginPassword(String username, String password);
}
