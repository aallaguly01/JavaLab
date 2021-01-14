package ru.itis.javalab;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityManager {
    private JdbcTemplate jdbcTemplate;

    public EntityManager(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // createTable("account", User.class);
    public <T> void createTable(String tableName, Class<T> entityClass) {
        // сгенерировать CREATE TABLE на основе класса
        // create table account ( id integer, firstName varchar(255), ...))
        Field fields[] = entityClass.getDeclaredFields();
        StringBuilder createTableBuilder = new StringBuilder();
        createTableBuilder.append("create table " + tableName + " (");

        for (Field field : fields) {
            createTableBuilder.append(field.getName()).append(" ");

            switch (field.getType().getSimpleName()){
                case ("String"):
                    createTableBuilder.append("varchar(255)");
                    break;
                case ("Long"):
                    createTableBuilder.append("bigint");
                    break;
                default: createTableBuilder.append(field.getType().getSimpleName());
            }

            if (field != fields[fields.length-1])
                createTableBuilder.append(", ");
        }

        createTableBuilder.append(")");
        jdbcTemplate.execute(createTableBuilder.toString());
    }

    //    INSERT INTO films (code)
//      VALUES ('T_601');
    public void save(String tableName, Object entity) {
        // сканируем его поля
        // сканируем значения этих полей
        // генерируем insert into
        Class<?> classOfEntity = entity.getClass();

        StringBuilder insertIntoBuilder = new StringBuilder();
        insertIntoBuilder.append("insert into " + tableName + " (");

        StringBuilder valueBuilder = new StringBuilder();
        valueBuilder.append("values (");
        Field[] fields = classOfEntity.getDeclaredFields();

        for(Field field : fields){
            insertIntoBuilder.append(field.getName());
            try {
                field.setAccessible(true);
                if(field.getType().getSimpleName().equals("String"))
                    valueBuilder.append("'").append(field.get(entity)).append("'");
                else
                    valueBuilder.append(field.get(entity));
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }

            if (field != fields[fields.length-1]) {
                insertIntoBuilder.append(", ");
                valueBuilder.append(", ");
            }
        }

        insertIntoBuilder.append(") ");
        valueBuilder.append(")");
        insertIntoBuilder.append(valueBuilder);
        jdbcTemplate.update(insertIntoBuilder.toString());

    }

    //select * from user where id = ?
    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {

        // сгенеририровать select
        StringBuilder findByIdBuilder = new StringBuilder();
        findByIdBuilder.append("select * from " + tableName + " where id = " + idValue );

        return jdbcTemplate.queryForObject(findByIdBuilder.toString(), resultType);

    }
}

