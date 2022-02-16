
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class S_addViewStudent extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        int id = Integer.parseInt(req.getParameter("sid"));
        String name = req.getParameter("sname");
        String uid = req.getParameter("suid");
        String add = req.getParameter("sadd");
        String gender = req.getParameter("sgen");
        String course = req.getParameter("scourse");
        int fee = Integer.parseInt(req.getParameter("sfee"));
        int money = Integer.parseInt(req.getParameter("smoney"));
        String mob = req.getParameter("smob");

//        String
        try {
            boolean flag = false;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee1", "root", "root");
//           
            Statement ch = con.createStatement();
            String query = "insert into student values(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = ch.executeQuery("select * from student");
            while (rs.next()) {
                if (rs.getInt(1) == id && rs.getString(2).equals(name)) {
                    pw.println("<p style=\"text-align: center; color: grey; font-size: 2em;\" >Student Already Found<br>");

                    pw.println("<p style=\"text-align: center; font-size: 1em;\"><a href=\"accountantPanel.html\">go to Login page</a></p></p>");
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setString(3, uid);
                stmt.setString(4, gender);
                stmt.setString(5, course);
                stmt.setInt(6, fee);
                stmt.setInt(7, money);
                stmt.setInt(8, (fee - money));
                stmt.setString(9, mob);
                stmt.setString(10, add);

                int check = stmt.executeUpdate();
                if (check > 0) {
                    pw.println("<span style=\"font-size: 3em;\">Student Added Sucessfully</span> ");

                    pw.println("<img src=\"https://userscontent2.emaze.com/images/9cbc5a94-4354-4fa8-975a-81172a85559e/9cd9e183-fdbd-42b0-b797-dc0732d517a8.jpg\" width=\"500px\" height=\"400px\" alt=\"\">");
                    pw.println(" <a href=\"accountantPanel.html\" style=\"font-size: 3em; font-family: cursive;\n"
                            + "    color: green;\">Go To Accountant Panel</a>");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");

        PrintWriter pw = res.getWriter();
        pw.println(" <h1 style=\"font-size: 3em;color: rgb(64,180,229); font-family: cursive;text-align: center;\">Student Data &nbsp;&nbsp;<a style=\"font-size: 0.5em; text-align: right; color: black;\" href=\"accountantPanel.html\">Go Back</a></h1>");
        pw.println(" <style>\n"
                + "        table {\n"
                + "            font-family: arial, sans-serif;\n"
                + "            border-collapse: collapse;\n"
                + "            width: 100%;\n"
                + "        }\n"
                + "\n"
                + "        td,\n"
                + "        th {\n"
                + "            border: 1px solid white;\n"
                + "            text-align: left;\n"
                + "            padding: 8px;\n"
                + "        }\n"
                + "\n"
                + "        tr:nth-child(even) {\n"
                + "            background-color: rgb(251, 124, 81);\n"
                + "        }\n"
                  + "        tr:nth-child(odd) {\n"
                + "            background-color: #dddddd;\n"
                + "        }\n"
                + "\n"
                + "    </style>");

        try {
            boolean flag = false;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee1", "root", "root");
//           
            Statement ch = con.createStatement();
//           

            ResultSet rs = ch.executeQuery("select * from student");
            pw.println("<table style=\"border-collapse:collapse\" width=\"100%\">");
            pw.println("<tr>\n"
                    + "    <th>ID</th>\n"
                    + "    <th>NAME</th>\n"
                    + "    <th>COURSE</th>\n"
                    + "    <th>ADDRESS</th>\n"
                    + "    <th>TOTAL FEE</th>\n"
                    + "    <th>PAID</th>\n"
                    + "    <th>DUES</th>\n"
                    + "    \n"
                    + "  </tr>");
            while (rs.next()) {
                pw.println("<tr>\n"
                        + "    <td>" + rs.getInt(1) + "</td>\n"
                        + "    <td>" + rs.getString(2) + "</td>\n"
                        + "    <td>" + rs.getString(5) + "</td>\n"
                        + "    <td>" + rs.getString(10) + "</td>\n"
                        + "    <td>" + rs.getString(6) + "</td>\n"
                        + "    <td>" + rs.getString(7) + "</td>\n"
                        + "    <td>" + rs.getString(8) + "</td>\n"
                        + "  </tr>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
