package ru.itis.javalab.repositories.old;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplate{

    private DataSource dataSource;


    public SimpleJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List <T> query(String sql, RowMapper<T> rowMapper, Object ... args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            if (args.equals(null))
                statement.setObject(1, args);

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            List<T> users = new ArrayList<>();
            while (resultSet.next()){
                T t =  rowMapper.mapRow(resultSet);
                users.add(t);
            }

            return users;

        }catch (SQLException e) {
            throw new IllegalStateException(e);
        }finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) {}
            }
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException ignore) {}
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ignore) {}
            }
        }
    }

}
