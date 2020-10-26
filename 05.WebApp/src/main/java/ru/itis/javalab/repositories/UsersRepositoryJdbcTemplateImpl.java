package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_BY_AGE = "select * from person where age  = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "select  * from person";
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from person where id = ?";
    //language=SQL
    private static final String SQL_INSERT = "INSERT into person(firstname, lastname, age) values (?,?,?)";
    //language=SQL
    private static final String SQL_UPDATE = "update person firstname = ?, lastname = ?, age = ? where id = ?";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM person WHERE id =?";

    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("firstName"))
            .lastName(row.getString("lastName"))
            .age(row.getInt("age"))
            .build();

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<User> findByAge(Integer age) {
        return jdbcTemplate.query(SQL_SELECT_BY_AGE, userRowMapper, age);
    }

    @Override
    public Optional<User> findFirstByFirstNameAndLastName(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public List<User> findAll(int page, int size) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try{
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id);
        }catch (EmptyResultDataAccessException e){
            user = null;
        }

        return Optional.ofNullable(user);
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getFirstName(), entity.getLastName(), entity.getAge());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void delete(User entity) {

    }
}
