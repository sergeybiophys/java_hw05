package sv.from.work.servlet;

import sv.from.work.db.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.sql.*;

public class HomeServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //super.init(config);
        System.out.println("Init HomeServlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        System.out.println("Handle request");
        PrintWriter writer = resp.getWriter();
         writer.println("Current time2:" + LocalTime.now());
        //Connection.GetAll();
        final String URL = "jdbc:mariadb://localhost:3306/db-univer";
        final String USER = "root";
        final String PASS = "";


            try (java.sql.Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                // Statement (PreparedStatement, CallableStatement)
                Statement stmt = conn.createStatement();
                // DML query (insert, update, delete) - executeUpdate(sql)
                // int count = stmt.executeUpdate("INSERT groups (name) values ('ВПД 911')");
                // System.out.println("count = " + count);
                // DQL query (select) - executeQuery(sql) -> ResultSet
                ResultSet resultSet = stmt.executeQuery("SELECT * FROM `users`");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("username");
                    System.out.printf("%3d | %-20s%n", id, name);
                }
                // close connection
            } catch (SQLException e) {
                e.printStackTrace();
            }


    }

    @Override
    public void destroy() {
        //super.destroy();
        System.out.println("Destroy HomeServlet");

    }
}
