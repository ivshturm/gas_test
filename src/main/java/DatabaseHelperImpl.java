import dao.DaoDatabase;
import dao.DaoDatabaseImpl;

import java.sql.SQLException;

/**
 * Created by Ivan.
 */
public class DatabaseHelperImpl implements DatabaseHelper {
    DaoDatabase daoDatabase;

    public DatabaseHelperImpl() {
        this.daoDatabase = new DaoDatabaseImpl();
    }

    public void findByName(String tableName, String name) {
        try {
            daoDatabase.findByName(tableName, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editLastName(String tableName, String name, String lastName) {
        try {
            daoDatabase.editLastName(tableName, name, lastName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
