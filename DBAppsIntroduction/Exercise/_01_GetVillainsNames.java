package DBAppsIntroduction.Exercise;

import java.sql.*;
import java.util.Properties;

public class _01_GetVillainsNames {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "7521");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT `name`, COUNT(DISTINCT mv.minion_id) AS minion_count FROM villains AS v" +
                        " JOIN minions_villains AS mv ON v.id = mv.villain_id " +
                        " GROUP BY mv.villain_id " +
                        " HAVING minion_count > 15 " +
                        " ORDER BY minion_count DESC ");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String minions_count = resultSet.getString("minion_count");
            System.out.println(name + " " + minions_count);

        }
    }
}
