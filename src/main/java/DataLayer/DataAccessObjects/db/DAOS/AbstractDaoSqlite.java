package DataLayer.DataAccessObjects.db.DAOS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import DataLayer.DataAccessObjects.IDao;
import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManager;
import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManagerSqlite;

public abstract class AbstractDaoSqlite<T, ID> implements IDao<T,ID> {
	
	private ConnectionManager connectionManager;

    @Override
    public T create(T t)
    {
        ConnectionManager conMan = new ConnectionManagerSqlite();

        try {
            Connection connection = conMan.getNewConnection();
            String stmt = getSqlInsert();
            PreparedStatement pStmt = connection.prepareStatement(stmt);
            setInsertStatement(pStmt, t);

            pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return t;
    }

    

    @Override
    public T read(ID Id) 
    {
        ConnectionManager conMan = new ConnectionManagerSqlite();

        try {
            Connection conn = conMan.getNewConnection();
            String stmt = getSqlReadId();
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setString(1, Id.toString());
            ResultSet rs = pStmt.executeQuery();

            return mapResultSetToObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> read()
    {
        List<T> list = new ArrayList<>();
        ConnectionManager conMan = new ConnectionManagerSqlite();

        try {
            Connection conn = conMan.getNewConnection();
            String stmt = getSqlReadAll();
            ResultSet rs = conn.prepareStatement(stmt).executeQuery();
            while(rs.next()) {
                list.add(mapResultSetToObject(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
	public void update(T objectToUpdate) 
    {
        String stmt = getSqlUpdate();

        try {
            Connection conn = connectionManager.getNewConnection();
            java.sql.PreparedStatement pStmt = conn.prepareStatement(stmt);

            setUpdateStatement(pStmt, objectToUpdate);

            pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
    public void delete(ID id)
	{
		String stmt = getSqlDelete();

        try {
            Connection conn = connectionManager.getNewConnection();
            java.sql.PreparedStatement pStmt = conn.prepareStatement(stmt);

            pStmt.setString(1, id.toString());

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

    protected abstract String getSqlReadId();
    protected abstract String getSqlReadAll();
    protected abstract String getSqlDelete();
	
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
