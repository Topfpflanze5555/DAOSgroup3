package DataLayer.DataAccessObjects.db.DAOS;

import Models.Pflegekraft;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataLayer.DataAccessObjects.IDao;

public class PflegekraftDaoSqlite extends AbstractDaoSqlite<Pflegekraft, Long> implements IDao<Pflegekraft, Long> {

	@Override
	protected String getTableName() {
		return "pflegekraft";
	}

	@Override
	protected String getPrimaryKeyColumn() {
		return "id";
	}

	@Override
	protected String getSqlCreateTableIfNotExists() {
		return "CREATE TABLE IF NOT EXISTS pflegekraft (\n" +
				"    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
				"    vorname TEXT NOT NULL,\n" +
				"    nachname TEXT NOT NULL,\n" +
				"    telefon TEXT,\n" +
				")";
	}

	@Override
	protected String getSqlInsert() {
		return "INSERT INTO " + getTableName() + " (vorname, nachname, telefon) VALUES(?,?,?)";
	}

	@Override
	protected String getSqlUpdate() {
		return "UPDATE " + getTableName() + " SET vorname = ?, nachname = ?, telefon = ? WHERE id = ?";
	}

	@Override
	protected void setUpdateStatement(PreparedStatement preparedStatement, Pflegekraft objectToUpdate) {
		mapPreparedStatementParameters(objectToUpdate, preparedStatement);
	}

	@Override
	protected void setInsertStatement(PreparedStatement preparedStatement, Pflegekraft objectToInsert) {
		mapPreparedStatementParameters(objectToInsert, preparedStatement);
	}

	private void mapPreparedStatementParameters(Pflegekraft pflegekraft, PreparedStatement preparedStatement) {
		String vorname = pflegekraft.getVorname();
		String nachname = pflegekraft.getNachname();
		String telefon = pflegekraft.getTelefon();

		try {
			preparedStatement.setString(1, vorname);
			preparedStatement.setString(2, nachname);
			preparedStatement.setString(3, telefon);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected Pflegekraft mapResultSetToObject(ResultSet resultSet) {
		Pflegekraft pflegekraft = new Pflegekraft();

		try {
			String vorname = resultSet.getString("vorname");
			String nachname = resultSet.getString("nachname");
			String telefon = resultSet.getString("telefon");

			pflegekraft.setId(resultSet.getInt(this.getPrimaryKeyColumn()));
			pflegekraft.setVorname(vorname);
			pflegekraft.setNachname(nachname);
			pflegekraft.setTelefon(telefon);

			return pflegekraft;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void setGeneratedIdToObject(PreparedStatement preparedStatement, Pflegekraft objectToInsert) {
		try {
			objectToInsert.setId(preparedStatement.getGeneratedKeys().getInt(1));
		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
