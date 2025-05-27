package DataLayer.DataAccessObjects.db.DAOS;

import Models.Pflegekraft;

import java.util.List;
import java.util.Optional;

public class PflegekraftDaoSqlite implements IDao<Pflegekraft> {
    @Override
    public Pflegekraft create(Pflegekraft pflegekraft) {
        return null;
    }

    @Override
    public Pflegekraft create() {
        return null;
    }

    @Override
    public Pflegekraft read(int Id) {
        return null;
    }

    @Override
    public List<Pflegekraft> read() {
        return List.of();
    }

    @Override
    public void delete(int Id) {

    }

    @Override
    public void update(Pflegekraft pflegekraft) {

    }
}
