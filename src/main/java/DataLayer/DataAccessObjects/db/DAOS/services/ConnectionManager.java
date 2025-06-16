package DataLayer.DataAccessObjects.db.DAOS.services;
import java.sql.*;

public abstract class ConnectionManager 
{
	private Connection existingConnection;
	
	public ConnectionManager()
	{

	}

	public Connection getExistingConnection()
	{
		return existingConnection;
	}
	public abstract Connection getNewConnection() throws SQLException;
}
