package DBAppsIntroduction.Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _02_GetMinionsNames {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);


        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "7521");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/minions_db", properties);

        int villainID = Integer.parseInt(scanner.nextLine());


        PreparedStatement villainStatement = connection.prepareStatement("SELECT name FROM villains WHERE id = ?");
        villainStatement.setInt(1, villainID);

        ResultSet villainSet = villainStatement.executeQuery();

        if (!villainSet.next()) {
            System.out.printf("No villain with ID %d exists in the database.", villainID);
            return;
        }
        String villainName = villainSet.getString("name");
        System.out.println("Villain: " + villainName);

        PreparedStatement minionsStatement = connection.prepareStatement("SELECT m.name,m.age FROM minions AS m " +
                " JOIN minions_villains AS mv ON m.id = mv.minion_id " +
                " JOIN villains AS v ON mv.villain_id = v.id " +
                " WHERE mv.villain_id = ?; ");
        minionsStatement.setInt(1,villainID);

        ResultSet minionSet = minionsStatement.executeQuery();

        for (int i = 1; minionSet.next(); i++) {

            String name = minionSet.getString("name");
            int age = minionSet.getInt("age");

            System.out.printf("%d. %s %d", i, name, age);
            System.out.println();
        }
        connection.close();
    }
}
