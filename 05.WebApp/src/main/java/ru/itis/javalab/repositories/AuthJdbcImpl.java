package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Auth;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class AuthJdbcImpl implements AuthRepository {



    private DataSource dataSource;

    public AuthJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Auth> findAll() {
        return null;
    }

    @Override
    public List<Auth> findAll(int page, int size) {
        return null;
    }

    @Override
    public Optional<Auth> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Auth entity) {

    }

    @Override
    public void update(Auth entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Auth entity) {

    }

    @Override
    public Optional<Auth> findByLoginPassword(String username, String password) {
        return Optional.empty();
    }
}
