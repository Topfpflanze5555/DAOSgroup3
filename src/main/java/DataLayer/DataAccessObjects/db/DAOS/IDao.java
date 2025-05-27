package DataLayer.DataAccessObjects.db.DAOS;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    T create();
    T create(T t);
    T read(int Id);
    List<T> read();
    void update(T t);
    void delete(int Id);
}
