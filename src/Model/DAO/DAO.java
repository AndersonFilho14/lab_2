package Model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    void create(T entity) throws SQLException;
    List<T> readAll() throws SQLException;
    void update(T entity) throws SQLException;
    void delete(int id) throws SQLException;
}