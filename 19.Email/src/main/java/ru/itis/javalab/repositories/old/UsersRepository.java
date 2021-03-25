package ru.itis.javalab.repositories.old;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);
    Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName);
}
