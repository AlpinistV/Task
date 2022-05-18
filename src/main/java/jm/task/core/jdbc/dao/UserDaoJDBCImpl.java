package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private final static Connection connection = Util.getConnection();


    @Override
    public void createUsersTable() {
        String table = "CREATE TABLE IF NOT EXISTS users"
                + "(id int primary key auto_increment not null, name VARCHAR(30) not null, " + "lastName VARCHAR(30) not null, age int not null)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(table)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void dropUsersTable() {
        String drop = "drop table if exists users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(drop);
            preparedStatement.executeUpdate();
            System.out.println("Таблица удалена!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Таблица не была удалена");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь: " + name + " " + lastName + " внесён в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Пользователь не внесён в базу данных!");
        }
    }


    @Override
    public void removeUserById(long id) {
        String remove = "delete from users where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(remove)) {
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с " + id + " удалён");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Пользователь с " + id + " не был удалён");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String get = "select * from users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(get)) {
            ResultSet resultSet = preparedStatement.executeQuery(get);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            System.out.println("Пользователи успешно созданы");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка создания пользователей!");
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String clean = "delete from `users`";
        try (PreparedStatement preparedStatement = connection.prepareStatement(clean)) {
            preparedStatement.executeUpdate();
            System.out.println("Удаление прошло успешно");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Удаление не было произведено");
        }

    }
}

