package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    private final static UserService user = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {

        // реализуйте алгоритм здесь
        user.createUsersTable();

        user.saveUser("Maksatai", "Karimova", (byte)23);
        System.out.println("User c именем - Maksatai добавлен в базу данных");

        user.saveUser("Ain", "Akylbek", (byte)24);
        System.out.println("User c именем - Ain добавлен в базу данных");

        user.saveUser("Anton", "Ilienko", (byte)20);
        System.out.println("User c именем - Anton добавлен в базу данных");

        user.saveUser("Vadim", "Gerasko", (byte)23);
        System.out.println("User c именем - Vadim добавлен в базу данных");

        user.getAllUsers();

        user.cleanUsersTable();

        user.dropUsersTable();
    }
}
