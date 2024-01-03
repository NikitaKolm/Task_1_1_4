package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.connect();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE users " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255) NULL, " +
                    " lastName VARCHAR(255) NULL, " +
                    " age INTEGER NULL, " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql);
            System.out.println("Database users has been created");
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "DROP TABLE users ";
            statement.executeUpdate(sql);
            System.out.println("Database users has been deleted");
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("INSERT INTO users(name, lastName, age) VALUES ('%s', '%s', %d)", name, lastName, age);
            statement.execute(sql);
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("DELETE FROM users WHERE Id = %d", id);
            statement.execute(sql);
            System.out.printf("User с id=%d удален из базы данных\n", id);
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> list = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
            return list;
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "TRUNCATE TABLE users ";
            statement.executeUpdate(sql);
            System.out.println("Database users has been cleaned");
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
