package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Vasia", "Vasin", (byte) 15);
        user.saveUser("Petr", "Petin", (byte) 18);
        user.saveUser("Sergey", "Sergeyv", (byte) 35);
        user.saveUser("Masha", "Mashina", (byte) 23);
        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
