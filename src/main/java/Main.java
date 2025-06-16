import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManagerSqlite;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ConnectionManagerSqlite ConMan = new ConnectionManagerSqlite();

        try {
            ConMan.getNewConnection();
        } catch (SQLException e) {
            // Catch me if you can
        }
    }
}
