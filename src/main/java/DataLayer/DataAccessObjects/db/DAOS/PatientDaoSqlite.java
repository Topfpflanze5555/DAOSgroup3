package DataLayer.DataAccessObjects.db.DAOS;

import Models.Patient;

import java.util.List;
import java.util.Optional;

public class PatientDaoSqlite implements IDao<Patient> {
    @Override
    public Optional<Patient> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Patient> getAll() {
        return List.of();
    }

    @Override
    public void save(Patient patient) {

    }

    @Override
    public void update(Patient patient, String[] params) {

    }

    @Override
    public void delete(Patient patient) {

    }
}
