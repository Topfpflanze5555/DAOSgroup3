package DataLayer.DataAccessObjects.db.DAOS.services;
import DataLayer.Exceptions.PrivateAccessException;

import java.sql.*;

public abstract class ConnectionManager 
{
	private Connection existingConnection;
	private String connectionString;
	
	public ConnectionManager()
	{
		connectionString = "jdbc:sqlite:patients.db";
	}

	public ConnectionManager(String connectionString) {
		this.connectionString = connectionString;
	}

	public Connection getExistingConnection()
	{
		return existingConnection;
	}
	public String getConnectionString() { return connectionString; }

	// Fun fact, in java there is no way to inherit private variables.
	// So, just don't use this outside of classes that inherit, i guess?
	// It's not like i can change it, the spec says its all in the same package, so...
	protected void _setExistingConnection(Connection conn) {
		this.existingConnection = conn;
	}

	public abstract Connection getNewConnection() throws SQLException;
}
