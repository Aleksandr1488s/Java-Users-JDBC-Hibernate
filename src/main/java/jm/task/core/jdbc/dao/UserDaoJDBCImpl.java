package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {//создать таблицу пользователей
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age SMALLINT" +
                ")";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка создания таблицы пользователей", e);
        }
    }
        


    public void dropUsersTable() {//удаление таблицы пользователей
        String sql = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления таблицы пользователей", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {//сохранить пользователя
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении пользователя", e);
        }
    }

    public void removeUserById(long id) {//удалить идентификатор пользователя по умолчанию
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении пользователя по ID", e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM users ORDER BY id";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));

                short age = resultSet.getShort("age");
                if (resultSet.wasNull()) {
                    user.setAge(null);
                } else {
                    user.setAge((byte) age);
                }

                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении списка пользователей", e);
        }

        return users;
    }//получить доступ ко всем пользователям

    public void cleanUsersTable() {//очистить таблицу пользователей
        String sql = "TRUNCATE TABLE users RESTART IDENTITY";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при очистке таблицы пользователей", e);
        }
    }
}
