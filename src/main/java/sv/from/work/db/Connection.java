package sv.from.work.db;

import java.sql.*;

public  class Connection {

    final static String URL = "jdbc:mysql://localhost:3306/db-univer";
    final static String USER = "root";
    final static String PASS = "";

    public static ResultSet GetAll(){
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try (java.sql.Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // Statement (PreparedStatement, CallableStatement)

            System.out.println("connected");
            Statement stmt = conn.createStatement();
            // DML query (insert, update, delete) - executeUpdate(sql)
            // int count = stmt.executeUpdate("INSERT groups (name) values ('ВПД 911')");
            // System.out.println("count = " + count);
            // DQL query (select) - executeQuery(sql) -> ResultSet
            resultSet = stmt.executeQuery("SELECT * FROM `users`");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("username");
                System.out.printf("%3d | %-20s%n", id, name);
            }
            // close connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  resultSet;

    }
}
