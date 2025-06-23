package DataLayer.DataAccessObjects.db.DAOS;

import Models.Pflegekraft;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DataLayer.DataAccessObjects.IDao;


public class PflegekraftDaoSqlite extends AbstractDaoSqlite<Pflegekraft, Long> implements IDao<Pflegekraft, Long>
{

	@Override
	protected String getTableName()
	{
		return "Pflegekraft";
	}

	@Override
	protected String getPrimaryKeyColumn()
	{
		return "id";
	}

	@Override
	protected String getSqlCreateTableIfNotExists()
	{
      return "CREATE TABLE IF NOT EXISTS pflegekraft (\n" +
          "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
          "    vorname TEXT NOT NULL,\n" +
          "    nachname TEXT NOT NULL,\n" +
          "    geburtsdatum REAL NOT NULL,\n" +
          "    pflegegrad INTEGER,\n" +
          "    zimmer INTEGER,\n" +
          "    vermoegen INTEGER\n" +
          ")";
	}

	@Override
	protected String getSqlInsert()
	{
		return "INSERT INTO"
			+ getTableName()
			+
			"(id, nachname, vorname, telefon)" +
			" VALUES(?,?,?,?)";
	}

	@Override
	protected String getSqlUpdate()
	{
		return "UPDATE " + getTableName() +
			" SET nachname = ?, vorname = ?, telefon = ?" +
			" WHERE id = ?";
	}

	@Override
	protected Pflegekraft mapResultSetToObject(ResultSet resultSet)
	{
		Pflegekraft pflegekraft = new Pflegekraft();
		try {
			pflegekraft.setId(resultSet.getLong(getPrimaryKeyColumn()));
			pflegekraft.setNachname(resultSet.getString("nachname"));
			pflegekraft.setVorname(resultSet.getString("vorname"));
			pflegekraft.setTelefon(resultSet.getString("telefon"));
		}
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
		return pflegekraft;

    }

	@Override
	protected void setInsertStatement(final PreparedStatement preparedStatement,
									  final Pflegekraft objectToInsert)
	{


	}

	@Override
	protected void setGeneratedIdToObject(final PreparedStatement preparedStatement,
										  final Pflegekraft objectToInsert)
	{

	}

	@Override
	protected void setUpdateStatement(final PreparedStatement preparedStatement,
									  final Pflegekraft objectToUpdate)
	{

	}
}
