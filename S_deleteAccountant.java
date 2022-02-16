
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
public class S_deleteAccountant extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        int id1 = Integer.parseInt(req.getParameter("Accid"));
        String name1 = req.getParameter("AccName");
        PrintWriter pw = res.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee1", "root", "root");
//           
            String del = "delete from accountant where id=";
            Statement stmt = con.createStatement();
            boolean check = stmt.execute(del + id1);
            if (!check) {
                pw.println("<span style=\"font-size: 4em;\">Accountant DeletedSucessfully</span>");
//                pw.println("<img src=\"https://thumbs.dreamstime.com/b/d-word-done-icon-white-background-illustration-38932125.jpg\" width=\"700px\" height=\"400px\"  alt=\"\">");
                pw.println("<div class=\"image\">\n"
                        + "    <img src=\"https://thumbs.dreamstime.com/b/d-word-done-icon-white-background-illustration-38932125.jpg\" width=\"400px\" height=\"400px\" margin-left=\"10%\" alt=\"\">\n"
                        + "</div>");
                pw.println(" <a href=\"adminPanel.html\" style=\"font-size: 3em; font-family: cursive;\n"
                        + "    color: green;\">Go To Admin Panel</a>");
            } else {
                pw.println("not deleted");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
