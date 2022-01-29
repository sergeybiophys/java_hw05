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
import java.util.Enumeration;
import java.util.HashSet;

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
        //PrintWriter writer = resp.getWriter();
         //writer.println("Current time2:" + LocalTime.now());


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        //resp.addHeader("Id", "Username");
       StringBuilder stringBuilder = new StringBuilder();
       String idHeader = "ID ";
       String userNameHeader = "User Name";
       String emailHeader = "E-mail";
       String ageHeader = "Age";

       stringBuilder.append("<div style=\""+"display: flex; flex: wrap;  width: 60%; margin: 0 auto; background-color: grey; height: 35px; justify-content: space-around;\""+">");
            stringBuilder.append("<div style=\""+"color:black; margin-right: 10px; \""+">"+idHeader+"</div>");
        //stringBuilder.append("<div>"+id+"</div>");

            stringBuilder.append("<div>"+userNameHeader+"</div>");
            stringBuilder.append("<div>"+emailHeader+"</div>");
            stringBuilder.append("<div>"+ageHeader+"</div>");

        stringBuilder.append("</div>");
        //--------------------------------------------------------------------------------------------------------------

        PrintWriter writer = resp.getWriter();


        writer.println(stringBuilder.toString());
        ResultSet resultSet = Connection.GetAll(writer);


    }

    @Override
    public void destroy() {
        //super.destroy();
        System.out.println("Destroy HomeServlet");

    }
}
