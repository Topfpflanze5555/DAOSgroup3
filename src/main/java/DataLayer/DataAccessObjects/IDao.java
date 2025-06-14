package DataLayer.DataAccessObjects;

import java.util.List;
import java.util.Optional;

public interface IDao<T, ID> {
    T create();
    T create(T t);
    T read(ID Id);
    List<T> read();
    void update(T t);
    void delete(ID Id);
}
