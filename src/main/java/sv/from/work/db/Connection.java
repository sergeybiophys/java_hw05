package sv.from.work.db;

import java.sql.*;

public  class Connection {

    final static String URL = "jdbc:mysql://localhost:3306/db-univer";
    final static String USER = "root";
    final static String PASS = "";



    public static ResultSet getAll() throws SQLException {
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        java.sql.Connection conn = DriverManager.getConnection(URL, USER, PASS) ;
            // Statement (PreparedStatement, CallableStatement)

            System.out.println("connected");
            Statement stmt = conn.createStatement();
            StringBuilder stringBuilder = new StringBuilder();
            // DML query (insert, update, delete) - executeUpdate(sql)
            // int count = stmt.executeUpdate("INSERT groups (name) values ('ВПД 911')");
            // System.out.println("count = " + count);
            // DQL query (select) - executeQuery(sql) -> ResultSet

             resultSet = stmt.executeQuery("SELECT * FROM `users`");

        return  resultSet;
    }

    public static void createUser(String username, String email, Integer age){

        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (java.sql.Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            Statement stmt = conn.createStatement();

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
            // close connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
