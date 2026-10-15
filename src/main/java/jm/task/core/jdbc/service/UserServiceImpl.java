package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public  class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {  //создать таблицу пользователей
        userDao.createUsersTable();
    }

    public void dropUsersTable() { //удаление таблицы пользователей
        userDao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) { //сохранить пользователя
        userDao.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) { //удалить идентификатор пользователя по умолчанию
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() { //получить доступ ко всем пользователям
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() { //очистить таблицу пользователей
    userDao.cleanUsersTable();
    }

    }








