package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;
                             //НЕ ТРОГАТЬ
public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

                                 }
                                 @Override
                                 public void createUsersTable() {//создать таблицу пользователей
                                     try (Session session = Util.getSessionFactory().openSession()) {
                                         Transaction transaction = session.beginTransaction();
                                         try {
                                             session.createSQLQuery(
                                                     "CREATE TABLE IF NOT EXISTS users (" +
                                                             "id BIGSERIAL PRIMARY KEY, " +
                                                             "name VARCHAR(50), " +
                                                             "lastName VARCHAR(50), " +
                                                             "age SMALLINT)"
                                             ).executeUpdate();

                                             transaction.commit();
                                             System.out.println("Таблица создалась");
                                         } catch (RuntimeException e) {
                                             throw new RuntimeException("Таблица не создалась по причине: "+ e);

                                         }
                                     }
                                 }

                                 @Override
                                 public void dropUsersTable() { //удаление таблицы пользователей
                                     try (Session session = Util.getSessionFactory().openSession()) {
                                         Transaction transaction = session.beginTransaction();

                                         try {
                                             session.createSQLQuery(
                                                     "DROP TABLE IF EXISTS users CASCADE"
                                             ).executeUpdate();

                                             transaction.commit();
                                         } catch (RuntimeException e) {
                                             throw new RuntimeException ("Ошибка при удалении таблицы пользователей" +e);

                                         }
                                     }
                                 }

                                 @Override
                                 public void saveUser(String name, String lastName, byte age) { //сохранить пользователя
                                     try (Session session = Util.getSessionFactory().openSession()) {
                                         Transaction transaction = session.beginTransaction();

                                         try {
                                             User user = new User();
                                             user.setName(name);
                                             user.setLastName(lastName);
                                             user.setAge(age);

                                             session.save(user);
                                             transaction.commit();
                                             System.out.println("Пользователь добавлен");
                                         } catch (RuntimeException e) {
                                             transaction.rollback();
                                             throw e;
                                         }
                                     }
                                 }

                                 @Override
                                 public void removeUserById(long id) { //удалить идентификатор пользователя по умолчанию
                                     try (Session session = Util.getSessionFactory().openSession()) {
                                         Transaction transaction = session.beginTransaction();

                                         try {
                                             User user = session.get(User.class, id);

                                             if (user != null) {
                                                 session.delete(user);
                                             }

                                             transaction.commit();
                                         } catch (RuntimeException e) {
                                             transaction.rollback();
                                             throw e;
                                         }
                                     }
                                 }

                                 @Override
                                 public List<User> getAllUsers() {
                                     try (Session session = Util.getSessionFactory().openSession()) {
                                         return session.createQuery("FROM User").list();
                                     }
                                 } //получить доступ ко всем пользователям

                                 @Override
                                 public void cleanUsersTable() { //очистить таблицу пользователей
                                     try (Session session = Util.getSessionFactory().openSession()) {
                                         Transaction transaction = session.beginTransaction();

                                         try {
                                             session.createQuery("DELETE FROM User").executeUpdate();
                                             transaction.commit();
                                         } catch (RuntimeException e) {
                                             throw new RuntimeException("Не удалось очистить таблицу пользователей", e);
                                         }
                                     }
                                     }
                                 }

