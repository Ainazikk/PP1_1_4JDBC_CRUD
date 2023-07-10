package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static Connection connection;

    private static final String username = "root";
    private static final String password = "Akylbekovaai.1998@";
    private static final String url="jdbc:mysql://localhost:3306/mydb";

    private static final String driver = "com.mysql.cj.jdbc.Driver";


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null) {
            return sessionFactory;
        }
        Properties properties = new Properties();

        properties.put(Environment.DRIVER, driver);
        properties.put(Environment.URL, url);
        properties.put(Environment.USER, username);
        properties.put(Environment.PASS, password);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "");

        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        return sessionFactory;
    }

    public static void closeSF() {
        try {
            sessionFactory.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    public static Connection get() {
        try {
            Class.forName(driver);
            return connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}