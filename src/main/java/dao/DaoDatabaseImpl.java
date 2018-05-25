package dao;

/**
 * Created by Ivan.
 */

import config.DatabaseConfig;

import java.sql.*;

public class DaoDatabaseImpl implements DaoDatabase {

    public Connection getDatabaseConnection() {
        Connection connection = null;

        try {
            Class.forName(DatabaseConfig.DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DatabaseConfig.DATABASE_URL, DatabaseConfig.USER_NAME,
                    DatabaseConfig.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createTable(String tableName) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        String commandSQL = String.format("DROP TABLE if exists %s;" +
                        "CREATE TABLE %s (name VARCHAR(20) NOT NULL UNIQUE, lastname VARCHAR(20) NOT NULL)",
                tableName, tableName);

        try {
            connection = getDatabaseConnection();
            statement = connection.createStatement();

            statement.execute(commandSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    public void insertIntoTable(String tableName, String userName, String userLastName) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        String commandSQL = String.format("INSERT INTO %s (name, lastname) VALUES ('%s', '%s')",
                tableName, userName, userLastName);

        try {
            connection = getDatabaseConnection();
            statement = connection.createStatement();

            statement.execute(commandSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    public void findByName(String tableName, String name) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        String commandSQL = String.format("SELECT * FROM %s WHERE name = '%s'", tableName, name);

        try {
            connection = getDatabaseConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(commandSQL);

            while (resultSet.next()) {
                String userName = resultSet.getString("name");
                String userLastName = resultSet.getString("lastname");

                System.out.println("name: " + userName);
                System.out.println("lastname: " + userLastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    public void editLastName(String tableName, String name, String lastName) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        String commandSQL = String.format("UPDATE %s SET lastname = '%s' WHERE name = '%s'",
                tableName, lastName, name);

        try {
            connection = getDatabaseConnection();
            statement = connection.createStatement();

            statement.execute(commandSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }
}
