import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost/Sklad?useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String USER = "Max";
    private static final String PASSWORD = "123456";

    private ResultSet getResultSet(String sql) throws ClassNotFoundException, SQLException {
        return getStatement().executeQuery(sql);
    }


    private int updateQuery(String sql) throws SQLException, ClassNotFoundException {
        return getStatement().executeUpdate(sql);
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


    ArrayList<String> getUchetTypesCode(String requestedColumn) {
        try {
            ResultSet resultSet = getResultSet("SELECT * FROM uchet_type;");
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(Integer.toString(resultSet.getInt(requestedColumn)));
            }
            return list;
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    ArrayList<String> getUchetTypesType(String requestedColumn) {
        try {
            ResultSet resultSet = getResultSet("SELECT * FROM uchet_type;");
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getString(requestedColumn));
            }
            return list;
        }
        catch (ClassNotFoundException | SQLException e) {
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
        List<String> addList = Arrays.asList(add.split(",[ ]*"));
        String separatedType = "";
        String types = addList.get(addList.size() - 1);
        for(int i = 0; i < addList.size() - 1; i++) {
            separatedType = separatedType.concat(addList.get(i) + ", ");
        }
        separatedType = separatedType.substring(0, separatedType.length() - 2);
        try {
            updateQuery("INSERT INTO uchet(product , price, date_of_delivery, date_of_sale, amount) VALUES( " + separatedType + ");");
            ResultSet resultSet = getResultSet("SELECT MAX(product_code) FROM uchet;");
            String id = "";
            while(resultSet.next()) {
                id = Integer.toString(resultSet.getInt("MAX(product_code)"));
            }
            System.out.println("INSERT INTO uchet_type(product_code, product_type) VALUES( " + id + ", " + types + ");");
            updateQuery("INSERT INTO uchet_type(product_code, product_type) VALUES(" + id + ", '" + types + "');");
        }
        catch (ClassNotFoundException|SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}