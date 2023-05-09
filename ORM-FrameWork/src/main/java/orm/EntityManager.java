package orm;

import annotations.Column;
import annotations.Entity;
import annotations.Id;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManager<E> implements DBContext<E> {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void doCreate(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        String fieldsWithTypes = getSQLFieldsWithTypes(entityClass);
        String query = String.format("CREATE TABLE %s (" + "id INT PRIMARY KEY AUTO_INCREMENT, %s)", tableName, fieldsWithTypes);


        PreparedStatement statement = connection.prepareStatement(query);

        statement.execute();
    }

    @Override
    public void doAlter(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        String addColumnStatements = getAddColumnStatements(entityClass);

        String query = String.format("ALTER TABLE %s %s", tableName, addColumnStatements);

        PreparedStatement statement = connection.prepareStatement(query);

        statement.execute();
    }

    @Override
    public boolean delete(E user) throws IllegalAccessException, SQLException {
        String tableName = getTableName(user.getClass());
        Field idColumn = getColumnId(user.getClass());
        String idColumnName = getSQLColunName(idColumn.getAnnotationsByType(Column.class));

        Object idColumnValue = getIdValue(idColumn, user);

        String query = String.format("DELETE FROM %s WHERE %s = %s", tableName, idColumnName, idColumnValue);
        PreparedStatement statement = connection.prepareStatement(query);

        return statement.execute();
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field columnId = getColumnId(entity.getClass());
        Object getFieldValue = getIdValue(columnId, entity);

        if (getFieldValue == null || (long) getFieldValue <= 0) {
            return doInsert(entity);
        }
        return doUpdate(entity, (long) getFieldValue);

    }

    //
//    UPDATE Customers - ready
//    SET ContactName = 'Alfred Schmidt', City = 'Frankfurt'
//    WHERE CustomerID = 1;
    private boolean doUpdate(E entity, long value) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity.getClass());
        List<String> tableFields = getColumnsWithoutId(entity.getClass());
        List<String> tableValues = getColumnsValuesWithoutId(entity);

        List<String> setStatements = new ArrayList<>();

        for (int i = 0; i < tableFields.size(); i++) {
            String statements = tableFields.get(i) + " = " + tableValues.get(i);
            setStatements.add(statements);
        }
        String query = String.format("UPDATE %s SET %s WHERE id = %d", tableName, String.join(",", setStatements), value);
        PreparedStatement statement = connection.prepareStatement(query);

        return statement.execute();
    }


    private boolean doInsert(E entity) throws SQLException, IllegalAccessException {
        String tableName = getTableName(entity.getClass());
        List<String> tableFields = getColumnsWithoutId(entity.getClass());
        List<String> tableValues = getColumnsValuesWithoutId(entity);
        String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, String.join(",", tableFields),
                String.join(",", tableValues));

        return connection.prepareStatement(insertQuery).execute();
    }


    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        return find(table, null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return baseFind(table, where, null);
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(table, null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException,
            NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        List<E> result = baseFind(table, where, "LIMIT 1");
        return result.get(0);
    }

    private void fillEntity(Class<E> table, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Field[] declaredFields = table.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            fillField(declaredField, resultSet, entity);
        }
    }

    private void fillField(Field field, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        field.setAccessible(true);
        String fieldName = getSQLColunName(field.getAnnotationsByType(Column.class));
        if (field.getType() == int.class || field.getType() == Integer.class) {
            field.set(entity, resultSet.getInt(fieldName));
        } else if (field.getType() == long.class || field.getType() == Long.class) {
            field.set(entity, resultSet.getLong(fieldName));
        } else if (field.getType() == LocalDate.class) {
            LocalDate localDate = LocalDate.parse(resultSet.getString(fieldName));
            field.set(entity, localDate);
        } else if (field.getType() == String.class) {
            field.set(entity, resultSet.getString(fieldName));
        }
    }


    private Field getColumnId(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new);
    }

    private List<E> baseFind(Class<E> table, String where, String limit) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String tableName = getTableName(table);

        String query = String.format("SELECT * FROM %s %s %s", tableName, where != null ? " WHERE " + where : "", limit != null ? limit : "");

        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();


        List<E> result = new ArrayList<>();
        while (resultSet.next()) {
            E entity = table.getDeclaredConstructor().newInstance();
            fillEntity(table, resultSet, entity);
            result.add(entity);
        }


        return result;
    }

    private List<String> getColumnsWithoutId(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotationsByType(Column.class))
                .map(this::getSQLColunName).collect(Collectors.toList());
    }

    private String getTableName(Class<?> aClass) {
        Entity[] annotationsByType = aClass.getAnnotationsByType(Entity.class);

        if (annotationsByType.length == 0) {
            throw new UnsupportedOperationException();
        }

        return annotationsByType[0].name();
    }

    private List<String> getColumnsValuesWithoutId(E entity) throws IllegalAccessException {
        Class<?> aClass = entity.getClass();
        List<Field> fields = getEntityColumnFieldsWithoutId(aClass.getDeclaredFields());

        List<String> values = new ArrayList<>();

        for (Field field : fields) {
            Object o = getIdValue(field, entity);

            if (o instanceof String || o instanceof LocalDate) {
                values.add("'" + o + "'");
            } else {
                values.add(o.toString());
            }


        }

        return values;
    }

    private List<Field> getEntityColumnFieldsWithoutId(Field[] aClass) {
        return Arrays.stream(aClass)
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class)).toList();
    }

    private Object getIdValue(Field columnId, E entity) throws IllegalAccessException {
        columnId.setAccessible(true);
        return columnId.get(entity);
    }

    private String getSQLColunName(Column[] idColumn) {
        return idColumn[0].name();
    }

    private String getAddColumnStatements(Class<E> entityClass) throws SQLException {
        Set<String> sqlColumns = getSqlColumnNamesForEntry();
        List<Field> fields = getEntityColumnFieldsWithoutId(entityClass.getDeclaredFields());

        List<String> allAddStatements = new ArrayList<>();
        for (Field field : fields) {
            String fieldName = getSQLColunName(field.getAnnotationsByType(Column.class));

            if (sqlColumns.contains(fieldName)) {
                continue;
            }

            String sqlType = getSqlType(field.getType());
            String formatted = String.format("ADD COLUMN %s %s", fieldName, sqlType);
            allAddStatements.add(formatted);
        }
        return String.join(",", allAddStatements);
    }

    private Set<String> getSqlColumnNamesForEntry() throws SQLException {
        String schemaQuery = "SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`. `COLUMNS` c " + " WHERE `c.TABLE_SCHEMA` = 'custom-orm' "
                + " AND COLUMN_NAME != 'id' " + " AND TABLE_NAME = 'users'; ";
        PreparedStatement statement = connection.prepareStatement(schemaQuery);
        ResultSet resultSet = statement.executeQuery();

        Set<String> result = new HashSet<>();
        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");
            result.add(columnName);
        }
        return result;
    }

    private String getSQLFieldsWithTypes(Class<E> entityClass) {
        return Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> {
                    String fieldName = getSQLColunName(f.getAnnotationsByType(Column.class));

                    String sqlType = getSqlType(f.getType());

                    return fieldName + " " + sqlType;
                }).collect(Collectors.joining(","));
    }

    private String getSqlType(Class<?> type) {
        String sqlType = "";
        if (type == Integer.class || type == int.class) {
            sqlType = "INT";
        } else if (type == String.class) {
            sqlType = "VARCHAR(200)";
        } else if (type == LocalDate.class) {
            sqlType = "DATE";
        }
        return sqlType;
    }

}
