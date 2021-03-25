//package ru.itis.javalab.repositories.old;
//
//import org.springframework.context.annotation.Profile;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//import ru.itis.javalab.models.User;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@Profile("master")
//@Repository
//public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
//
//    private JdbcTemplate jdbcTemplate;
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    //language=SQL
//    private static final String SQL_SELECT_BY_ID = "select * from person where id = ?";
//
//    //language=SQL
//    private static final String SQL_SELECT_ALL_WITH_PAGES = "select * from person order by id limit :limit offset :offset ;";
//
//    //language=SQL
//    private static final String SQL_SELECT_ALL = "select * from person";
//
//    //language=SQL
//    private static final String SQL_INSERT_USER = "insert into person(firstname, lastname, email, password) values (?, ?, ?, ?)";
//
//    //language=SQL
//    private static final String SQL_SELECT_BY_AGE = "select * from person where age = ?";
//
//    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
//            .id(row.getLong("id"))
//            .firstName(row.getString("firstname"))
//            .lastName(row.getString("lastname"))
//            .age(row.getInt("age"))
//            .build();
//
//    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//    }
//
//    @Override
//    public List<User> findAllByAge(Integer age) {
//        return jdbcTemplate.query(SQL_SELECT_BY_AGE, userRowMapper, age);
//    }
//
//    @Override
//    public Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<User> findAll() {
//        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
//    }
//
//    @Override
//    public List<User> findAll(int page, int size) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("limit", size);
//        params.put("offset", page * size);
//        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, params, userRowMapper);
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        User user;
//        try {
//            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id);
//        } catch (EmptyResultDataAccessException e) {
//            user = null;
//        }
//
//        return Optional.ofNullable(user);
//
//    }
//
//    @Override
//    public void save(User entity) {
//        jdbcTemplate.update(SQL_INSERT_USER, entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPassword());
//    }
//
//
//
//    @Override
//    public void update(User entity) {
//
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//
//    @Override
//    public void delete(User entity) {
//
//    }
//}
