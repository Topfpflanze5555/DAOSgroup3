package DataLayer.DataAccessObjects.db.DAOS.services;
import java.sql.*;

public abstract class ConnectionManager 
{
	private String className;
	private String connectionString;
	private Connection existingConnection;
	
	public ConnectionManager(String className, String connectionString)
	{
		this.className = className;
		this.connectionString = connectionString;
	}
	
	public String getClassName()
	{
		return this.className;
	}
	public String getConnectionString()
	{
		return connectionString;
	}
	public Connection getExistingConnection()
	{
		return existingConnection;
	}
	public abstract Connection getNewConnection() throws SQLException;
}
