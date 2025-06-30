package DataLayer.DataAccessObjects.db.DAOS;

import DataLayer.DataAccessObjects.db.DAOS.services.SQLiteFormatConverter;
import Models.Patient;

import java.sql.*;
import java.time.LocalDate;

import DataLayer.DataAccessObjects.IDao;

public class PatientDaoSqlite extends AbstractDaoSqlite<Patient, Integer> implements IDao<Patient, Integer> {
	@Override
	protected String getTableName() {
		return "patienten";
	}

	@Override
	protected String getPrimaryKeyColumn() {
		return "id";
	}

	@Override
	protected String getSqlCreateTableIfNotExists() {
		return "CREATE TABLE IF NOT EXISTS patienten (\n" +
				"    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
				"    vorname TEXT NOT NULL,\n" +
				"    nachname TEXT NOT NULL,\n" +
				"    geburtsdatum REAL NOT NULL,\n" +
				"    pflegegrad INTEGER,\n" +
				"    zimmer INTEGER,\n" +
				"    vermoegen INTEGER\n" + // SQLite doesnt support decimals. This means we just treat the last two digits of this number as decimals. So, 1.00 would be 100. Not great, but there isn't a better way of doing it.
				")";
	}

	@Override
	protected void setInsertStatement(PreparedStatement preparedStatement, Patient objectToInsert) {
		mapPreparedStatementParameters(objectToInsert, preparedStatement);
	}

	@Override
	protected void setUpdateStatement(PreparedStatement preparedStatement, Patient objectToUpdate) {
		mapPreparedStatementParameters(objectToUpdate, preparedStatement);
	}

	@Override
	protected void setGeneratedIdToObject(PreparedStatement preparedStatement, Patient objectToInsert) {
		try {
			objectToInsert.setId(preparedStatement.getGeneratedKeys().getInt(1));
		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String getSqlInsert() {
		return "INSERT INTO " + getTableName() + " (vorname, nachname, geburtsdatum, pflegegrad, zimmer, vermoegen) VALUES (?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String getSqlUpdate() {
		return "UPDATE " + getTableName() + "SET (vorname = ?, nachname = ?, geburtsdatum = ?, pflegegrad = ?, zimmer = ?, vermoegen = ?) WHERE " + getPrimaryKeyColumn() + " = ?";
	}

	private void mapPreparedStatementParameters(Patient patient, PreparedStatement preparedStatement) {
		String vorname = patient.getVorname();
		String nachname = patient.getNachname();
		String geburtsdatum = patient.getGeburtsdatum();
		int pflegegrad = patient.getPflegegrad();
		String zimmer = patient.getZimmer();
		int vermoegen = (int)patient.getVermoegen()*100;

		try {
			preparedStatement.setString(1, vorname);
			preparedStatement.setString(2, nachname);
			preparedStatement.setDate(3, Date.valueOf(geburtsdatum));
			preparedStatement.setInt(4, pflegegrad);
			preparedStatement.setString(5, zimmer);
			preparedStatement.setInt(6, vermoegen);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected Patient mapResultSetToObject(ResultSet resultSet) {
		Patient patient = new Patient();

		try {
			patient.setId(resultSet.getInt(this.getPrimaryKeyColumn()));
			patient.setVorname(resultSet.getString("vorname"));
			patient.setNachname(resultSet.getString("nachname"));
			patient.setGeburtsdatum(resultSet.getDate(4).toLocalDate().toString());
			patient.setPflegegrad(resultSet.getInt("pflegegrad"));
			patient.setZimmer(resultSet.getString("zimmer"));
			patient.setVermoegen(SQLiteFormatConverter.formatIntToDouble(resultSet.getInt("vermoegen")));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return patient;
	}
}
