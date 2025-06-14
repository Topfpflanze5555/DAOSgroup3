package DataLayer.DataAccessObjects.db.DAOS;

import java.util.List;
import java.util.Optional;
import DataLayer.DataAccessObjects.IDao;

public class AbstractDaoSqlite<T, ID> implements IDao<T,ID> {
    @Override
    public T create(Object o) {
        return null;
    }

    @Override
    public T create() {
        return null;
    }

    @Override
    public T read(ID Id) {
        return null;
    }

    @Override
    public List read() {
        return List.of();
    }

    @Override
    public void delete(ID Id) {

    }

    @Override
    public void update(Object o) {

    }
}
