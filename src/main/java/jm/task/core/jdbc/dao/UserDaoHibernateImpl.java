package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private final SessionFactory sessionFactory = Util.getSessionFactory();


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {

            String sqlCreate = "CREATE TABLE if not exists users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastName VARCHAR(40), age TINYINT)";
            session.beginTransaction();
            session.createSQLQuery(sqlCreate).executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {

            String sqlDrop = "DROP TABLE if exists users;";
            session.beginTransaction();
            session.createSQLQuery(sqlDrop).executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = null;

        try (Session session = sessionFactory.getCurrentSession()) {

            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.printf("User с именем – %s добавлен в базу данных %n", name);

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {

            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {

        List<User> list = null;
        Transaction transaction = null;

        try (Session session = sessionFactory.getCurrentSession()) {

            transaction = session.beginTransaction();
            list = session.createQuery("from User").getResultList();
            transaction.commit();
            list.stream().forEach(System.out::println);

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {

        Transaction transaction = null;

        try (Session session = sessionFactory.getCurrentSession()) {

            transaction = session.beginTransaction();
            session.createQuery("delete User ").executeUpdate();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}