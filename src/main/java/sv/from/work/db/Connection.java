package sv.from.work.db;

import java.io.PrintWriter;
import java.sql.*;

public  class Connection {

    final static String URL = "jdbc:mysql://localhost:3306/db-univer";
    final static String USER = "root";
    final static String PASS = "";

    public static ResultSet GetAll(PrintWriter writer){
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
            StringBuilder stringBuilder = new StringBuilder();
            // DML query (insert, update, delete) - executeUpdate(sql)
            // int count = stmt.executeUpdate("INSERT groups (name) values ('ВПД 911')");
            // System.out.println("count = " + count);
            // DQL query (select) - executeQuery(sql) -> ResultSet
            resultSet = stmt.executeQuery("SELECT * FROM `users`");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("username");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                System.out.printf("%3d | %-20s | %-20s | %-4d%n" , id, name, email,age);

                stringBuilder.append("<div style=\""+"display: flex; flex: wrap;  width: 60%; margin: 0 auto; background-color: white; height: 35px; justify-content: space-around;\""+">");
                stringBuilder.append("<div style=\""+"color:green; margin-right: 10px; \""+">"+id+"</div>");
                //stringBuilder.append("<div>"+id+"</div>");

                stringBuilder.append("<div>"+name+"</div>");
                stringBuilder.append("<div>"+email+"</div>");
                stringBuilder.append("<div>"+age+"</div>");

                stringBuilder.append("</div>");

            }

            writer.println(stringBuilder.toString());
            // close connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  resultSet;

    }
}
