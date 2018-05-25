/**
 * Created by Ivan.
 */
public interface DatabaseHelper {
    void findByName(String tableName, String name);

    void editLastName(String tableName, String name, String lastName);
}
