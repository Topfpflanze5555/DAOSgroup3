package DataLayer.DataAccessObjects.db.DAOS;

import Models.Leistung;

import java.util.List;
import java.util.Optional;

public class LeistungDaoSqlite implements IDao<Leistung> {
    @Override
    public Leistung create(Leistung leistung) {
        return null;
    }

    @Override
    public Leistung create() {
        return null;
    }

    @Override
    public Leistung read(int Id) {
        return null;
    }

    @Override
    public List<Leistung> read() {
        return List.of();
    }

    @Override
    public void update(Leistung leistung) {

    }

    @Override
    public void delete(int Id) {

    }
}
