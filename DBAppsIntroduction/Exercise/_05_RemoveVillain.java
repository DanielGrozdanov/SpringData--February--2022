package DBAppsIntroduction.Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _05_RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "7521");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/minions_db", properties);

        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement statement = connection.prepareStatement("SELECT name FROM villains " +
                " WHERE id = ?");
        statement.setInt(1, villainId);

        ResultSet resultSet = statement.executeQuery();


        if (!resultSet.next()) {
            System.out.println("No such villain was found");
            return;
        }

        String villainName = resultSet.getString("name");

        PreparedStatement selectAllVillainMinions = connection.prepareStatement("SELECT COUNT(DISTINCT minion_id) as m_count" +
                " FROM minions_villains WHERE villain_id = ?");
        selectAllVillainMinions.setInt(1, villainId);

        ResultSet resultSet1 = selectAllVillainMinions.executeQuery();
        resultSet1.next();

        int m_count = resultSet1.getInt("m_count");

        connection.setAutoCommit(false);

        try {
            PreparedStatement deleteMinionsVillains = connection.prepareStatement("DELETE FROM minions_villains WHERE villain_id = ?");
            deleteMinionsVillains.setInt(1, villainId);
            deleteMinionsVillains.executeUpdate();

            PreparedStatement deleteVillain = connection.prepareStatement(" DELETE FROM villains WHERE id = ?");
            deleteVillain.setInt(1, villainId);
            deleteVillain.executeUpdate();

            connection.commit();

            System.out.println(villainName + " was deleted");
            System.out.println(m_count + " minions released");


        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
