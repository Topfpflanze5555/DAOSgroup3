package DataLayer.DataAccessObjects.db.DAOS;

import java.util.List;
import java.util.Optional;

public class AbstractDaoSqlite implements IDao {
    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return List.of();
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }
}
