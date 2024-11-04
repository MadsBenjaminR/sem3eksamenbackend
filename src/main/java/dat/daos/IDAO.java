package dat.daos;

import java.util.List;

public interface IDAO<T, ID> {

    T create(T e);

    List<T> getAll();

    T getById(ID id);

    T update(T e);

    void delete(ID id);
}
