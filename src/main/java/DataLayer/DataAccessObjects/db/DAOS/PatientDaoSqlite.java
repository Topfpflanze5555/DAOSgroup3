package DataLayer.DataAccessObjects.db.DAOS;

import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManager;
import DataLayer.DataAccessObjects.db.DAOS.services.ConnectionManagerSqlite;
import Models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import DataLayer.DataAccessObjects.IDao;

public class PatientDaoSqlite implements IDao<Patient, Long> {

	@Override
	public Patient create(Patient t) {

		ConnectionManager conMan = new ConnectionManagerSqlite();
		String stmt = "INSERT INTO patienten (vorname, nachname, pflegegrad, zimmer, vermoegen) VALUES (?, ?, ?, ?, ?)";

		String vorname = t.getVorname();
		String nachname = t.getNachname();
		Integer pflegegrad = t.getPflegegrad();
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


	@Override
	public Patient read(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Patient t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long Id) {
		// TODO Auto-generated method stub
		
	}

}
