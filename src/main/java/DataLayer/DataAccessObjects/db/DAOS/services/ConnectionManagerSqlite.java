package DataLayer.DataAccessObjects.db.DAOS.services;

import java.sql.*;
import DataLayer.Exceptions.DAOException;
public class ConnectionManagerSqlite extends ConnectionManager {
    public ConnectionManagerSqlite() {
        super();
    }

    @Override
    public Connection getNewConnection() throws SQLException {

        if(this.getExistingConnection() == null){
            Connection conn = DriverManager.getConnection(getConnectionString());
            _setExistingConnection(conn);
        }

        return null;
    }
}
