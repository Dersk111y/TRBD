import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost/Sklad?useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String USER = "Max";
    private static final String PASSWORD = "123456";

    private ResultSet getResultSet() throws ClassNotFoundException, SQLException{
        Connection connection;
        Statement statement;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        statement = connection.createStatement();
        String sql;
        sql = "SELECT * FROM uchet";
        return statement.executeQuery(sql);
    }



    ArrayList<String> getProducts() {
        try {
            ResultSet resultSet = getResultSet();
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add((resultSet.getString("product")));
            }
            return list;
        } catch (ClassNotFoundException|SQLException e) {
            e.getMessage();
        }
        return null;
    }



    ArrayList<String> getDatesOfDelivery() {
        try {
            ResultSet resultSet = getResultSet();
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getDate(("date_of_delivery")).toString());
            }
            return list;
        } catch (ClassNotFoundException|SQLException e) {
            e.getMessage();
        }
        return null;
    }

    ArrayList<String> getDatesOfSale() {
        try {
            ResultSet resultSet = getResultSet();
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getDate("date_of_sale").toString());
            }
            return list;
        } catch (ClassNotFoundException|SQLException e) {
            e.getMessage();
        }
        return null;
    }


    ArrayList<String> getIntegerData(String requestedColumn) {
        try {
            ResultSet resultSet = getResultSet();
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(Integer.toString(resultSet.getInt(requestedColumn)));
            }
            return list;
        } catch (ClassNotFoundException|SQLException e) {
            e.getMessage();
        }
        return null;
    }
}
