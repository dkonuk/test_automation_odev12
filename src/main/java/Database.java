import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "mysecretpassword";
        String tablename = "users_new2";
        String user1name = "John";
        String user1lastname = "Doe";
        int user1age = 25;
        String user2name = "Jane";
        String user2lastname = "Doe";
        int user2age = 30;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement createTableStatement = connection.createStatement();
            String createtablequerry = "CREATE TABLE IF NOT EXISTS " + tablename + "(NAME TEXT, LASTNAME TEXT, AGE INT);";
            createTableStatement.executeUpdate(createtablequerry);

            String insertquerry = "INSERT INTO " + tablename + " VALUES ('" + user1name + "', '" + user1lastname + "', " + user1age + ");";
            int result = createTableStatement.executeUpdate(insertquerry);


            String insertquerry2 = "INSERT INTO " + tablename + " VALUES ('" + user2name + "', '" + user2lastname + "', " + user2age + ");";
            int result2 = createTableStatement.executeUpdate(insertquerry2);


            String selectquerry = "SELECT * FROM " + tablename + ";";
            ResultSet result3 = createTableStatement.executeQuery(selectquerry);
            while (result3.next()) {
                System.out.println(result3.getString("NAME") + " " + result3.getString("LASTNAME") + " " + result3.getInt("AGE"));

            }


            String deletequerry = "DELETE FROM " + tablename + " WHERE name = '" + user2name + "';";
            int result4 = createTableStatement.executeUpdate(deletequerry);

            ResultSet resultSetfinal = createTableStatement.executeQuery(selectquerry);
            while (resultSetfinal.next()) {
                System.out.println(resultSetfinal.getString("NAME") + " " + resultSetfinal.getString("LASTNAME") + " " + resultSetfinal.getInt("AGE"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
