package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

//    private final UserDao us= new UserDaoHibernateImpl();
    private final UserDao us = new UserDaoJDBCImpl();
    public void createUsersTable() throws SQLException {
        us.createUsersTable();
    }

    public void dropUsersTable() throws SQLException{
        us.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        us.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        us.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return us.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        us.cleanUsersTable();
    }
}