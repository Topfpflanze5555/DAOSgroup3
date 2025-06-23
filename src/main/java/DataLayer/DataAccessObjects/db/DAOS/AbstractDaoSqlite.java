package DataLayer.DataAccessObjects.db.DAOS;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import DataLayer.DataAccessObjects.IDao;
import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManager;

public abstract class AbstractDaoSqlite<T, ID> implements IDao<T,ID> {
	
	private ConnectionManager connectionManager;
	
    @Override
    public T create(T objectToInsert) 
    {
        return null;
    }

    

    @Override
    public T read(ID Id) 
    {
        return null;
    }

    @Override
    public List read() 
    {
        return List.of();
    }

    @Override
	public void update(T objectToUpdate) 
    {
        String stmt = "UPDATE FROM ? WHERE ?=?";
        Connection conn = null;

        try {
            conn = connectionManager.getNewConnection();
            java.sql.PreparedStatement pStmt = conn.prepareStatement(stmt);

            setUpdateStatement(pStmt, objectToUpdate);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
    public void delete(ID id)
	{
		String stmt = "DELETE FROM ? WHERE ?=?";
        Connection conn = null;

        try {
            conn = connectionManager.getNewConnection();
            java.sql.PreparedStatement pStmt = conn.prepareStatement(stmt);

            int paramIndex = 1;
            pStmt.setString(paramIndex, getTableName());
            paramIndex++;//paramIndex = 2

            pStmt.setString(paramIndex, getPrimaryKeyColumn());
            paramIndex++;//paramIndex = 3

            pStmt.setString(paramIndex, id.toString());

            pStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	protected abstract String getTableName();
	
	protected abstract String getPrimaryKeyColumn();
	
	protected abstract String getSqlCreateTableIfNotExists();

    /**
     *
     * @return prebuild SQL String for inserting an object see setSqlInsert
     */
	protected abstract String getSqlInsert();

    /**
     *
     * @return prebuild SQL String for updating an object see setSqlUpdate
     */
	protected abstract String getSqlUpdate();
	
	protected abstract T mapResultSetToObject(ResultSet resultSet);

    /**
     * defines specific InsertStatement with an object and its type
     * @param preparedStatement parameterized SQL statement with '?' as parameters
     *
     */
	protected abstract void setInsertStatement(PreparedStatement preparedStatement, T objectToInsert);


	protected abstract void setGeneratedIdToObject(PreparedStatement preparedStatement, T objectToInsert);

    /**
     * defines specific UpdateStatement with an object and its type
     * @param preparedStatement parameterized SQL statement with '?' as parameters
     *
     */
	protected abstract void setUpdateStatement(PreparedStatement preparedStatement, T objectToUpdate);
	
	private void createTableIfNotExists()
	{

	}
	
	
	
	
	
}
