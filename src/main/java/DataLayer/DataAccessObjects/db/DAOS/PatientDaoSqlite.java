package DataLayer.DataAccessObjects.db.DAOS;

import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManager;
import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManagerSqlite;
import DataLayer.DataAccessObjects.db.DAOS.services.SQLiteFormatConverter;
import Models.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DataLayer.DataAccessObjects.IDao;

public class PatientDaoSqlite extends AbstractDaoSqlite<Patient, Integer> implements IDao<Patient, Integer> {

	@Override
	public Patient create(Patient t) {

		ConnectionManager conMan = new ConnectionManagerSqlite();
		String stmt = "INSERT INTO patienten (vorname, nachname, pflegegrad, zimmer, vermoegen) VALUES (?, ?, ?, ?, ?)";

		String vorname = t.getVorname();
		String nachname = t.getNachname();
		int pflegegrad = t.getPflegegrad();
		String zimmer = t.getZimmer();
		int vermoegen = (int)t.getVermoegen()*100;

		try {
			Connection conn = conMan.getNewConnection();
			PreparedStatement pStmt = conn.prepareStatement(stmt);
			pStmt.setString(1, vorname);
			pStmt.setString(2, nachname);
			pStmt.setInt(3, pflegegrad);
			pStmt.setString(4, zimmer);
			pStmt.setInt(5, vermoegen);

			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


		return null;
	}


	public Patient read(Long Id) {
		ConnectionManager conMan = new ConnectionManagerSqlite();
		String stmt = "SELECT * FROM patienten WHERE ? = ?";
		String primaryKeyColumn = this.getPrimaryKeyColumn();

		ConnectionManager ConMan = new ConnectionManagerSqlite();

		try {
			Connection conn = ConMan.getNewConnection();
			PreparedStatement pStmt = conn.prepareStatement(stmt);

			pStmt.setString(1, primaryKeyColumn);
			pStmt.setLong(2, Id);

			ResultSet rs = pStmt.executeQuery();

            return mapResultSetToObject(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Patient> read() {
		ConnectionManager conMan = new ConnectionManagerSqlite();
		String stmt = "SELECT ? FROM patienten";

		List<Patient> patients = new ArrayList<>();

		try {
			Connection conn = conMan.getNewConnection();
			PreparedStatement pStmt = conn.prepareStatement(stmt);
			pStmt.setString(1, this.getPrimaryKeyColumn());

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(this.getPrimaryKeyColumn());
				patients.add(this.read(id));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

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
	protected String getSqlReadId() {
		return "SELECT * FROM " + getTableName() + " WHERE " + getPrimaryKeyColumn() + " = ?";
	}

	@Override
	protected String getSqlReadAll() {
		return "SELECT * FROM " + getTableName();
	}

	@Override
	protected String getSqlDelete() {
		return "DELETE FROM " + getTableName() + " WHERE " + getPrimaryKeyColumn() + " = ?";
	}

	@Override
	protected String getSqlUpdate() {
		return "UPDATE " + getTableName() + "SET (vorname = ?, nachname = ?, geburtsdatum = ?, pflegegrad = ?, zimmer = ?, vermoegen = ?) WHERE " + getPrimaryKeyColumn() + " = ?";
	}

	private void mapPreparedStatementParameters(Patient patient, PreparedStatement preparedStatement) {
		String vorname = patient.getVorname();
		String nachname = patient.getNachname();
		LocalDate geburtsdatum = patient.getGeburtsdatum();
		int pflegegrad = patient.getPflegegrad();
		String zimmer = patient.getZimmer();
		int vermoegen = (int)patient.getVermoegen()*100;

		try {
			preparedStatement.setString(1, vorname);
			preparedStatement.setString(2, nachname);
			preparedStatement.setDate(3, Date.valueOf(geburtsdatum));
			preparedStatement.setInt(3, pflegegrad);
			preparedStatement.setString(4, zimmer);
			preparedStatement.setInt(5, vermoegen);
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
			patient.setGeburtsdatum(resultSet.getDate(4).toLocalDate());
			patient.setPflegegrad(resultSet.getInt("pflegegrad"));
			patient.setZimmer(resultSet.getString("zimmer"));
			patient.setVermoegen(SQLiteFormatConverter.formatIntToDouble(resultSet.getInt("vermoegen")));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return patient;
	}
}
