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

        Connection.GetAll();


    }

    @Override
    public void destroy() {
        //super.destroy();
        System.out.println("Destroy HomeServlet");

    }
}
