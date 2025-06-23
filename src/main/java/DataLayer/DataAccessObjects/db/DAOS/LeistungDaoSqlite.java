package DataLayer.DataAccessObjects.db.DAOS;

import Models.Leistung;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeistungDaoSqlite extends AbstractDaoSqlite<Leistung,String>  {



	@Override
	protected String getTableName() {
		return "leistung";
	}

	@Override
	protected String getPrimaryKeyColumn() {
		return "lkNr";
	}

	@Override
	protected String getSqlCreateTableIfNotExists() {
		return """
                CREATE TABLE IF NOT EXISTS leistung (
                    lkNr TEXT PRIMARY KEY,
                    bezeichnung TEXT,
                    beschreibung TEXT
                )""";


	}

	@Override
	protected String getSqlInsert() {
		return "INSERT INTO " + getTableName() + " ("+getPrimaryKeyColumn()+", bezeichnung, beschreibung) VALUES (?, ?)";
	}

	@Override
	protected String getSqlUpdate() {
		return "SET "+getTableName()+" = ?, bezeichnung = ?, beschreibung = ? WHERE " + getPrimaryKeyColumn() + " = ?";
	}

	@Override
	protected String getSqlReadId() {
		return "";
	}

	@Override
	protected String getSqlReadAll() {
		return "";
	}

	@Override
	protected String getSqlDelete() {
		return "";
	}

	@Override
	protected Leistung mapResultSetToObject(ResultSet resultSet) {

		Leistung leistung = new Leistung();
		try {
			leistung.setBeschreibung(resultSet.getString("beschreibung"));
			leistung.setBezeichnung(resultSet.getString("bezeichnung"));
			leistung.setLkNr(resultSet.getString(getPrimaryKeyColumn()));

		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return leistung;
    }
	private void mapPreparedStatementParameters(Leistung leistung, PreparedStatement preparedStatement) {
		String bezeichnung = leistung.getBezeichnung();
		String beschreibung = leistung.getBeschreibung();


		try {
			preparedStatement.setString(1, bezeichnung);
			preparedStatement.setString(2, beschreibung);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void setInsertStatement(PreparedStatement preparedStatement, Leistung objectToInsert) {
		mapPreparedStatementParameters(objectToInsert, preparedStatement);

	}

	@Override
	protected void setGeneratedIdToObject(PreparedStatement preparedStatement, Leistung objectToInsert) {

		try {
			objectToInsert.setLkNr(preparedStatement.getGeneratedKeys().getString(1));
		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void setUpdateStatement(PreparedStatement preparedStatement, Leistung objectToUpdate) {
		mapPreparedStatementParameters(objectToUpdate, preparedStatement);
	}
}
