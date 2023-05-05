package DBAppsIntroduction.Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class _06_PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "7521");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/minions_db", properties);

        PreparedStatement names = connection.prepareStatement("SELECT name FROM minions ");
        ResultSet resultSet = names.executeQuery();

        List<String> minionNames = new ArrayList<>();
        while (resultSet.next()) {
            minionNames.add(resultSet.getString("name"));
        }

        for (int first = 0, last = minionNames.size() - 1; first <= last; first++, last--) {
            System.out.println(minionNames.get(first));
            if (first != last) {
                System.out.println(minionNames.get(last));
            }
        }
    }
}
