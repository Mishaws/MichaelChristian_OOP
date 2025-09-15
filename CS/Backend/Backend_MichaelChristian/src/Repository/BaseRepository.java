package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.HashMap;

public abstract class BaseRepository<T,ID> {
    HashMap<T, ID> Map = new HashMap<>();
    ArrayList<T, ID> List = new ArrayList<>();
    void findById(ID id) {

    }

    void findAll() {

    }

    public abstract void save(T entity) {

    }

    public abstract void getID(T entity) {

    }
}
