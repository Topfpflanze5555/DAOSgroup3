package DataLayer.DataAccessObjects.db.DAOS;

import Models.Leistung;

import java.util.List;
import java.util.Optional;

public class LeistungDaoSqlite implements IDao<Leistung> {
    @Override
    public Optional<Leistung> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Leistung> getAll() {
        return List.of();
    }

    @Override
    public void save(Leistung leistung) {

    }

    @Override
    public void update(Leistung leistung, String[] params) {

    }

    @Override
    public void delete(Leistung leistung) {

    }
}
