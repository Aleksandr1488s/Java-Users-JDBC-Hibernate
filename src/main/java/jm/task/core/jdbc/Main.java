package jm.task.core.jdbc;




import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        // 1. Создание таблицы
        userService.createUsersTable();

        // 2. Добавление пользователей
        userService.saveUser("Ivan", "Ivanov", (byte) 25);
        System.out.println(
                "User с именем - Ivan добавлен в базу данных"
        );

        userService.saveUser("Petr", "Petrov", (byte) 30);
        System.out.println(
                "User с именем - Petr добавлен в базу данных"
        );

        userService.saveUser("Anna", "Smirnova", (byte) 22);
        System.out.println(
                "User с именем - Anna добавлен в базу данных"
        );

        userService.saveUser("Olga", "Sidorova", (byte) 28);
        System.out.println(
                "User с именем - Olga добавлен в базу данных"
        );

        // 3. Получение и вывод всех пользователей
        List<User> users = userService.getAllUsers();

        for (User user : users) {
            System.out.println(user);
        }

        // 4. Очистка таблицы
        userService.cleanUsersTable();

        // 5. Удаление таблицы
        userService.dropUsersTable();

        // реализуйте алгоритм здесь


    }
}




