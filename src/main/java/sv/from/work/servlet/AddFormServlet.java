package sv.from.work.servlet;

import sv.from.work.db.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class AddFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println("<div style=\""+"display: flex; width: 30%; margin: 0 auto; \""+">" +
                "<form method='post' action='/'>" +
                "<label>UserName: <input name='username'/></label><br/>" +
                "<label>E-mail: <input name='email' type='email'/></label><br/>" +
                "<label>Age: <input name='age' type='number'/></label><br/>" +
                "<input type='submit'/>" +
                "</form>" +
                "</div>");



    }
}
