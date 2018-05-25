package dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Ivan.
 */
public interface DaoDatabase {
    Connection getDatabaseConnection();

    void findByName(String tableName, String name) throws SQLException;

    void editLastName(String tableName, String name, String lastName) throws SQLException;

    void createTable(String tableName) throws SQLException;

    void insertIntoTable(String tableName, String userName, String userLastName) throws SQLException;
}
