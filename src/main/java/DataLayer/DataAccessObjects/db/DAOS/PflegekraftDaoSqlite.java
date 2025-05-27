package DataLayer.DataAccessObjects.db.DAOS;

import Models.Pflegekraft;

import java.util.List;
import java.util.Optional;

public class PflegekraftDaoSqlite implements IDao<Pflegekraft> {
    @Override
    public Optional<Pflegekraft> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Pflegekraft> getAll() {
        return List.of();
    }

    @Override
    public void save(Pflegekraft pflegekraft) {

    }

    @Override
    public void update(Pflegekraft pflegekraft, String[] params) {

    }

    @Override
    public void delete(Pflegekraft pflegekraft) {

    }
}
