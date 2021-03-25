package ru.itis.javalab.repositories.old;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

@Profile("dev")
@Repository
public class UsersRepositoryFakeImpl implements UsersRepository {
    @Override
    public List<User> findAllByAge(Integer age) {
        return null;
    }

    @Override
    public Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAll(int page, int size) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(User.builder()
                .firstName("FAKE")
                .lastName("FAKEOFF")
                .build());
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User entity) {

    }
}
