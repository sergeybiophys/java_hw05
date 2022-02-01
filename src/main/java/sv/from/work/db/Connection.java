package sv.from.work.db;

import javax.servlet.ServletException;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public  class Connection {

    final static String URL = "jdbc:mysql://localhost:3306/db-univer";
    final static String USER = "root";
    final static String PASS = "";

    public static ResultSet GetAll() {
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

            while (resultSet.next())
            {
                System.out.println(resultSet.getString("username"));
            }
          /*  while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("username");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                System.out.printf("%3d | %-20s | %-20s | %-4d%n" , id, name, email,age);

                stringBuilder.append("<div style=\""+"display: flex; flex: wrap; width: 60%; margin: 0 auto; margin-top: 20px;  background-color: white; height: 35px; justify-content: space-around; border: 1px solid lightblue;\""+">");
                stringBuilder.append("<div style=\""+"color:red; margin-right: 10px; \""+">"+id+"</div>");
                //stringBuilder.append("<div>"+id+"</div>");

                stringBuilder.append("<div>"+name+"</div>");
                stringBuilder.append("<div>"+email+"</div>");
                stringBuilder.append("<div>"+age+"</div>");

                stringBuilder.append("</div>");

            }*/

            //writer.println(stringBuilder.toString());
            // close connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  resultSet;
    }

    public static void CreateUser(String username,String email, Integer age){

        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (java.sql.Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            Statement stmt = conn.createStatement();
            //stmt.executeUpdate("INSERT users (age,email,username) values(20,unknown@mail.ru,Unknown)");
            //stmt.executeUpdate("INSERT users_groups(group_id,user_id) values('1','2')");
            //------------------------------------------------------------------------------

            PreparedStatement pstmt = conn.prepareStatement("INSERT users (age,email,username) values(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,age);
            pstmt.setString(2,email);
            pstmt.setString(3,username);
            pstmt.executeUpdate();

            int lastSet=-1;
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                lastSet=rs.getInt(1);
            }

            PreparedStatement pstmt2 = conn.prepareStatement("INSERT users_groups(group_id,user_id) values(?,?)");
            pstmt2.setInt(1,1);
            pstmt2.setInt(2,lastSet);
            pstmt2.executeUpdate();

            //-----------------------------------------------------------------------------
          /*  ResultSet resultSet = stmt.executeQuery("SELECT username, name FROM users JOIN users_groups ON users.id = users_groups.user_id JOIN groups ON groups.id = users_groups.group_id");
            while (resultSet.next()) {
                String userName = resultSet.getString("username");
                String groupName = resultSet.getString("name");
                System.out.printf("|%-20s | %-20s%n", userName, groupName);

            }*/
            // close connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
