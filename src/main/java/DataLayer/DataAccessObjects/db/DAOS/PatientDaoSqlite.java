package DataLayer.DataAccessObjects.db.DAOS;

import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManager;
import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManagerSqlite;
import DataLayer.DataAccessObjects.db.DAOS.services.SQLiteFormatConverter;
import Models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import DataLayer.DataAccessObjects.IDao;

public class PatientDaoSqlite extends AbstractDaoSqlite<Patient, Integer> implements IDao<Patient, Integer> {

	private PreparedStatement insertStatement;

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
	public void update(Patient t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Long Id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTableName() {
		return "";
	}

	@Override
	protected String getPrimaryKeyColumn() {
		return "id";
	}

	@Override
	protected String getSqlCreateTableIfNotExists() {
		return "";
	}

	@Override
	protected void setInsertStatement(PreparedStatement preparedStatement, Patient objectToInsert) {
		String vorname = objectToInsert.getVorname();
		String nachname = objectToInsert.getNachname();
		int pflegegrad = objectToInsert.getPflegegrad();
		String zimmer = objectToInsert.getZimmer();
		int vermoegen = (int)objectToInsert.getVermoegen()*100;

		try {
			preparedStatement.setString(1, vorname);
			preparedStatement.setString(2, nachname);
			preparedStatement.setInt(3, pflegegrad);
			preparedStatement.setString(4, zimmer);
			preparedStatement.setInt(5, vermoegen);

			this.insertStatement = preparedStatement;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void setUpdateStatement(PreparedStatement preparedStatement, Patient objectToUpdate) {

	}

	@Override
	protected void setGeneratedIdToObject(PreparedStatement preparedStatement, Patient objectToInsert) {

	}

	@Override
	protected String getSqlInsert() {
		return this.insertStatement.toString();
	}

	@Override
	protected String getSqlUpdate() {
		return "";
	}

	@Override
	protected Patient mapResultSetToObject(ResultSet resultSet) {
		Patient patient = new Patient();

		try {
			patient.setId(resultSet.getInt(this.getPrimaryKeyColumn()));
			patient.setVorname(resultSet.getString("vorname"));
			patient.setNachname(resultSet.getString("nachname"));
			patient.setPflegegrad(resultSet.getInt("pflegegrad"));
			patient.setZimmer(resultSet.getString("zimmer"));
			patient.setVermoegen(SQLiteFormatConverter.formatIntToDouble(resultSet.getInt("vermoegen")));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return patient;
	}
}
