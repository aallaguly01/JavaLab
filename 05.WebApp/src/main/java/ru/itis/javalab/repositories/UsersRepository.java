package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findByAge(Integer age);
    Optional<User> findFirstByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findOneByEmail(String email);
    void updateAuthCookie(UUID uuid, String email);
    boolean checkAuthCookie(String uuid);
}
