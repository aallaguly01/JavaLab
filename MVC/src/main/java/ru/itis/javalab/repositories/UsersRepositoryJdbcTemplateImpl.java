package ru.itis.javalab.repositories;

import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.*;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    //language=SQL
    private static final String SQL_SELECT_ALL_WITH_PAGES = "select * from person order by id limit :limit offset :offset ;";
    //language=SQL
    private static final String SQL_FIND_ONE_BY_EMAIL = "select * from person where email = ? limit 1";
    //language=SQL
    private static final String SQL_UPDATE_AuthCookie = "update person set cookieauth = ? where email = ?";
    //language=SQl
    private static final String SQL_FIND_BY_AUTHCOOKIE = "select * from person where cookieauth = ? limit 1";

    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("firstName"))
            .lastName(row.getString("lastName"))
            .age(row.getInt("age"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .build();

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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
        Map<String, Object> params = new HashMap<>();
        params.put("limit", size);
        params.put("offset", page * size);
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, params, userRowMapper);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_ONE_BY_EMAIL, userRowMapper, email));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void updateAuthCookie(UUID uuid, String email) {
        jdbcTemplate.update(SQL_UPDATE_AuthCookie, uuid, email);
    }

    @Override
    public boolean checkAuthCookie(String uuid) {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_AUTHCOOKIE, userRowMapper, uuid) != null;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
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
        System.out.println("asdasdasd");
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
