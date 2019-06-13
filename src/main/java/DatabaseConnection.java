import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost/Sklad?useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String USER = "Max";
    private static final String PASSWORD = "123456";

    private ResultSet getResultSet(String sql) throws ClassNotFoundException, SQLException {
        return getStatement().executeQuery(sql);
    }


    private int updateQuery(String sql) throws SQLException, ClassNotFoundException {
        int result = getStatement().executeUpdate(sql);
        return result;
    }


    private Statement getStatement() throws SQLException, ClassNotFoundException {
        Connection connection;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        return connection.createStatement();
    }



    ArrayList<String> getProducts() {
        try {
            ResultSet resultSet = getResultSet("SELECT * FROM uchet;");
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add((resultSet.getString("product")));
            }
            return list;
        } catch (ClassNotFoundException|SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



    ArrayList<String> getDatesOfDelivery() {
        try {
            ResultSet resultSet = getResultSet("SELECT * FROM uchet;");
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getDate(("date_of_delivery")).toString());
            }
            return list;
        } catch (ClassNotFoundException|SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    ArrayList<String> getDatesOfSale() {
        try {
            ResultSet resultSet = getResultSet("SELECT * FROM uchet;");
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getDate("date_of_sale").toString());
            }
            return list;
        } catch (ClassNotFoundException|SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    ArrayList<String> getIntegerData(String requestedColumn) {
        try {
            ResultSet resultSet = getResultSet("SELECT * FROM uchet;");
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(Integer.toString(resultSet.getInt(requestedColumn)));
            }
            return list;
        } catch (ClassNotFoundException|SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    void edit(String id, String columnName, String value) {
        try {
            if (columnName.equals("product")||(columnName.equals("date_of_delivery")) || (columnName.equals("date_of_sale")))
                updateQuery("UPDATE uchet SET " + columnName + "=" + "\'" + value + "\'" + " WHERE product_code = " + id + ";");
            else
                updateQuery("UPDATE uchet SET " + columnName + "=" + value + " WHERE product_code = " + id + ";");
        }
        catch (ClassNotFoundException|SQLException e){
            System.out.println(e.getMessage());
        }
    }


    void delete(String id) {
        try {
            updateQuery("DELETE FROM uchet WHERE product_code = " + id + ";");
        }
        catch (ClassNotFoundException|SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    void add(String add) {
        try {
            updateQuery("INSERT INTO uchet(product , price, date_of_delivery, date_of_sale, amount) VALUES( " + add + ");");
        }
        catch (ClassNotFoundException|SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
