package DBAppsIntroduction.Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _03_AddMinion {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "7521");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/minions_db", properties);

        String[] input = scanner.nextLine().split(" ");
        String minionName = input[1];
        int minionAge = Integer.parseInt(input[2]);
        String townName = input[3];

        String villainName = scanner.nextLine().split(" ")[1];

        int townId = addOrGetTown(connection, townName);
        int villainId = addOrGetVillain(connection, villainName);


        PreparedStatement minionInsert = connection.prepareStatement(" INSERT INTO minions(name,age,town_id) VALUES (?, ?, ?) ");
        minionInsert.setString(1, minionName);
        minionInsert.setInt(2, minionAge);
        minionInsert.setInt(3, townId);
        minionInsert.executeUpdate();

        PreparedStatement lastMinion = connection.prepareStatement("SELECT id FROM minions WHERE name  = ?");
        lastMinion.setString(1, minionName);

        ResultSet resultSet = lastMinion.executeQuery();
        resultSet.next();

        int minionId = resultSet.getInt("id");

        PreparedStatement insertMinionVil = connection.prepareStatement("INSERT INTO minions_villains(minion_id,villain_id) VALUES (?, ? ); ");
        insertMinionVil.setInt(1, minionId);
        insertMinionVil.setInt(2, villainId);
        insertMinionVil.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.", minionName, villainName);
        connection.close();

    }

    private static int addOrGetTown(Connection connection, String townName) throws SQLException {
        int townId;
        PreparedStatement towns = connection.prepareStatement("SELECT `id` FROM towns WHERE name = ?");
        towns.setString(1, townName);
        ResultSet townResult = towns.executeQuery();


        if (!townResult.next()) {
            PreparedStatement insertTown = connection.prepareStatement("INSERT INTO towns(name) VALUES (?); ");
            insertTown.setString(1, townName);
            insertTown.executeUpdate();

            ResultSet newTownsInfo = towns.executeQuery();
            townId = newTownsInfo.getInt("id");
            System.out.printf("Town %s was added to the database.%n", townName);

        } else {
            townId = townResult.getInt("id");
        }

        return townId;
    }

    private static int addOrGetVillain(Connection connection, String villainName) throws SQLException {
        int villainId;
        PreparedStatement villain = connection.prepareStatement("SELECT `id` FROM villains WHERE name = ?");
        villain.setString(1, villainName);
        ResultSet villainResult = villain.executeQuery();

        if (!villainResult.next()) {
            PreparedStatement insertVillain = connection.prepareStatement("INSERT INTO villains(name, evilness_factor) VALUE (?, ?) ");
            insertVillain.setString(1, villainName);
            insertVillain.setString(2, "evil");

            insertVillain.executeUpdate();

            ResultSet newVillainSet = villain.executeQuery();
            newVillainSet.next();
            villainId = newVillainSet.getInt("id");
            System.out.printf("Villain %s was added to the database.%n", villainName);
        } else {
            villainId = villainResult.getInt("id");
        }
        return villainId;
    }
}

