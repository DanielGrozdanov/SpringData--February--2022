package DBAppsIntroduction.Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _04_ChangeTownNameCasing {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "7521");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/minions_db", properties);

        String townToChange = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement("UPDATE towns SET name = UPPER(name) " +
                " WHERE country = ?");
        statement.setString(1, townToChange);
        int count = statement.executeUpdate();

        if (count == 0) {
            System.out.println("No town names were affected.");
        } else {
            List<String> townsAffected = new ArrayList<>();
            PreparedStatement affected = connection.prepareStatement("SELECT `name` FROM towns " +
                    " WHERE country = ?");

            affected.setString(1, townToChange);
            ResultSet resultSet = affected.executeQuery();
            while (resultSet.next()) {
                townsAffected.add(resultSet.getString("name"));
            }
            System.out.println(count + " town names were affected.");
            System.out.println(townsAffected);
        }
    }
}
