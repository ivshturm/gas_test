import dao.DaoDatabase;
import dao.DaoDatabaseImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by Ivan.
 */
public class DatabaseHelperImplTest {
    private String tableName = "USER_DATABASE";

    private String trueName = "James";
    private String falseName = "Robert";

    private String lastName = "Ryan";
    private String updateLastName = "Kovalski";

    DaoDatabase daoDatabase = new DaoDatabaseImpl();
    DatabaseHelper databaseHelper = new DatabaseHelperImpl();

    protected final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUp() throws SQLException {
        System.setOut(new PrintStream(output));
        daoDatabase.createTable(tableName);
        daoDatabase.insertIntoTable(tableName, trueName, lastName);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void findByNameSuccessTest() throws SQLException {
        databaseHelper.findByName(tableName, trueName);

        String expected = "name: " + trueName + "\r\n" + "lastname: " + lastName;
        String actual = output.toString().trim();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = AssertionError.class)
    public void findByNameFailTest() throws SQLException {
        databaseHelper.findByName(tableName, trueName);

        String expected = "name: " + trueName + "\r\n" + "lastname: " + updateLastName;
        String actual = output.toString().trim();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void editLastNameSuccessTest() throws SQLException {
        databaseHelper.editLastName(tableName, trueName, updateLastName);
        databaseHelper.findByName(tableName, trueName);

        String expected = "name: " + trueName + "\r\n" + "lastname: " + updateLastName;
        String actual = output.toString().trim();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = AssertionError.class)
    public void editLastNameFailTest() throws SQLException {
        databaseHelper.editLastName(tableName, falseName, updateLastName);
        databaseHelper.findByName(tableName, falseName);

        String expected = "name: " + trueName + "\r\n" + "lastname: " + updateLastName;
        String actual = output.toString().trim();

        Assert.assertEquals(expected, actual);
    }
}