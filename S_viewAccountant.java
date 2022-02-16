
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
public class S_viewAccountant extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");

        PrintWriter pw = res.getWriter();
        pw.println(" <h1 style=\"font-size: 4em; font-family: cursive;text-align: center; color:rgb(251,124,81);\">Accountant Details</h1>");
        pw.println(" <style>\n"
                + "        table {\n"
                + "            font-family: arial, sans-serif;\n"
                + "            border-collapse: collapse;\n"
                + "            width: 100%;\n"
                + "        }\n"
                + "\n"
                + "        td,\n"
                + "        th {\n"
                + "            border: 1px solid #dddddd;\n"
                + "            text-align: left;\n"
                + "            padding: 8px;\n"
                 + "            background-color: rgb(64,180,229);\n"
                + "        }\n"
                + "\n"
                + "    </style>");

        try {
            boolean flag = false;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee1", "root", "root");
//           
            Statement ch = con.createStatement();
//            Statement ch = con.createStatement();
//            ResultSet id1 = ch.executeQuery("select max(id) from accountant;");
//            pw.println(id1);
          
            ResultSet rs = ch.executeQuery("select * from accountant");
            pw.println("<table style=\"border-collapse:collapse\" width=\"100%\">");
            pw.println("<tr>\n"
                    + "    <th>ID</th>\n"
                    + "    <th>NAME</th>\n"
                    + "    <th>EMAIL</th>\n"
                    + "    <th>Qualification</th>\n"
                    + "    <th>MOBILE</th>\n"
                    + "    \n"
                    + "  </tr>");
            while (rs.next()) {
                pw.println("<tr>\n"
                        + "    <td>" + rs.getInt(1) + "</td>\n"
                        + "    <td>" + rs.getString(2) + "</td>\n"
                        + "    <td>" + rs.getString(3) + "</td>\n"
                        + "    <td>" + rs.getString(4) + "</td>\n"
                        + "    <td>" + rs.getString(6) + "</td>\n"
                        + "  </tr>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
