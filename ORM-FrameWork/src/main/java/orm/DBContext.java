package orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DBContext<E> {
    boolean persist(E entity) throws IllegalAccessException, SQLException;

    Iterable<E> find(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;

    Iterable<E> find(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    E findFirst(Class<E> table) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException;

    E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    void doCreate(Class<E> entityClass) throws SQLException;

    void doAlter(Class<E> entityClass) throws SQLException;

    boolean delete(E user) throws IllegalAccessException, SQLException;
}
