package DBAppsIntroduction.Lab.Dablo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "");
        properties.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", properties);

        PreparedStatement statement = connection.prepareStatement("SELECT user_name,first_name,last_name, COUNT(users_games.id) as games_played FROM users " +
                "JOIN users_games ON users_games.user_id = users.id " +
                "WHERE user_name = ?"
                + " GROUP BY users_games.user_id");

        Scanner scanner = new Scanner(System.in);

        String username = scanner.nextLine();

        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String user_name = resultSet.getString("user_name");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            Integer gamesCount = resultSet.getInt("games_played");

            System.out.printf("User: %s%n" + "%s %s has played %d games%n", user_name, first_name, last_name, gamesCount);
        } else {
            System.out.println("No such user exists");
        }
    }
}
