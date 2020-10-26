//package ru.itis.javalab.repositories.old;
//
//import ru.itis.javalab.models.User;
//import ru.itis.javalab.repositories.UsersRepository;
//
//import javax.sql.DataSource;
//import java.util.List;
//import java.util.Optional;
//
//public class UsersRepositoryJdbcImpl implements UsersRepository {
//
//    //language=SQL
//    private static final String SQL_SELECT_BY_AGE = "select * from person where age  = ?";
//    //language=SQL
//    private static final String SQL_SELECT = "select  * from person";
//    //language=SQL
//    private static final String SQL_SELECT_BY_ID = "select * from person where id = ?";
//
//    private DataSource dataSource;
//    SimpleJdbcTemplate template;
//
//    public UsersRepositoryJdbcImpl(DataSource dataSource) {
//        this.dataSource = dataSource;
//        template =  new SimpleJdbcTemplate(dataSource);
//    }
//
//    private RowMapper<User> userRowMapper = row -> User.builder()
//            .id(row.getLong("id"))
//            .firstName(row.getString("firstName"))
//            .lastName(row.getString("lastName"))
//            .age(row.getInt("age"))
//            .build();
//
//    @Override
//    public List<User> findByAge(Integer age) {
//      TODO: return template.query(SQL_SELECT_BY_AGE, usersRowMapper, age);
//        return template.query(SQL_SELECT_BY_AGE, userRowMapper, age);
////        Connection connection = null;
////        PreparedStatement statement = null;
////        ResultSet resultSet = null;
////
////        try {
////            connection = dataSource.getConnection();
////            statement = connection.prepareStatement(SQL_SELECT_BY_AGE);
////            statement.setInt(1, age);
////            resultSet = statement.executeQuery();
////
////            List<User> users = new ArrayList<>();
////
////            while (resultSet.next()) {
////                User user = userRowMapper.mapRow(resultSet);
////                users.add(user);
////            }
////
////            return users;
////
////        } catch (SQLException e) {
////            throw new IllegalStateException(e);
////        } finally {
////            if (resultSet != null) {
////                try {
////                    resultSet.close();
////                } catch (SQLException ignore) {}
////            }
////            if (statement != null) {
////                try {
////                    statement.close();
////                } catch (SQLException ignore) {}
////            }
////            if (connection != null) {
////                try {
////                    connection.close();
////                } catch (SQLException ignore) {}
////            }
////        }
//    }
//
//    @Override
//    public Optional<User> findFirstByFirstNameAndLastName(String firstName, String lastName) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<User> findAll() {
//        // TODO: return template.query(SQL_SELECT, usersRowMapper);
//        return template.query(SQL_SELECT, userRowMapper);
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        //return template.query(SQL_SELECT_BY_ID, userRowMapper, id);
//        return Optional.empty();
//    }
//
//    @Override
//    public void save(User entity) {
//
//    }
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
