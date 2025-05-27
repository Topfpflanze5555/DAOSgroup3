package DataLayer.DataAccessObjects.db.DAOS;

import Models.Patient;

import java.util.List;
import java.util.Optional;

public class PatientDaoSqlite implements IDao<Patient> {
    @Override
    public Patient create(Patient patient) {
        return null;
    }

    @Override
    public Patient create() {
        return null;
    }

    @Override
    public Patient read(int Id) {
        return null;
    }

    @Override
    public List<Patient> read() {
        return List.of();
    }

    @Override
    public void update(Patient patient) {

    }

    @Override
    public void delete(int Id) {

    }
}
