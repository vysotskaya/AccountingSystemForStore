package dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 25.03.2015.
 */
public interface BaseDAO <T extends Serializable> {
    public boolean create(T t);
    public List read();
    public boolean update(T t);
    public boolean deleteById(int id);
    public T getById(int id);
}
