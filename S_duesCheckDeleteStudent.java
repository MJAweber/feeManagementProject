
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

public class S_duesCheckDeleteStudent extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        int id = Integer.parseInt(req.getParameter("sid"));
        String name = req.getParameter("sname");
        
        pw.println("<style> body{\n"
//                + "            background-color: rgb(18,192,106);\n"
                + "        } </style>");

        try {
            boolean flag = false;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee1", "root", "root");
            Statement ch = con.createStatement();
            ResultSet rs = ch.executeQuery("select * from student");
            String query1 = "delete from student where id=";
         

            while (rs.next()) {
                if (rs.getInt(1) == id && rs.getString(2).equals(name)) {
                   
                    PreparedStatement stmt = con.prepareStatement(query1 +id);
                    int check = stmt.executeUpdate();

                    if (check > 0) {
                        pw.println("<span style=\"font-size: 4em;\">Student Deleted Sucessfully</span>");

                    pw.println("<img src=\"https://static.vecteezy.com/system/resources/previews/004/968/615/original/file-deleted-successfully-throw-it-away-trash-concept-illustration-flat-design-eps10-simple-modern-graphic-element-for-landing-page-empty-state-ui-infographic-icon-vector.jpg\" width=\"500px\" height=\"400px\" alt=\"\">");
                    pw.println(" <a href=\"accountantPanel.html\" style=\"font-size: 3em; font-family: cursive;\n"
                            + "    color: green;\">Go To Accountant Panel</a>");

                    } else {
                        pw.println("Student not deleted yet");
                    }
                    break;
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
        int range = Integer.parseInt(req.getParameter("range"));
        PrintWriter pw = res.getWriter();
         pw.println(" <h1 style=\"font-size: 3em;color: rgb(64,180,229); font-family: cursive;text-align: center;\">Dues Available &nbsp;&nbsp;<a style=\"font-size: 0.5em; text-align: right; color: black;\" href=\"accountantPanel.html\">Go Back</a></h1>");
        pw.println(" <style>\n"
                + "        table {\n"
                + "            font-family: arial, sans-serif;\n"
                + "            border-collapse: collapse;\n"
                + "            width: 100%;\n"
                + "        }\n"
                 + "        td {\n"
                + "            font-family: arial, sans-serif;\n"
//                + "            border-collapse: collapse;\n"
                + "            color: black;\n"
                + "        }\n"
                + "\n"
                + "        td,\n"
                + "        th {\n"
                + "            border: 1px solid white;\n"
                + "            text-align: left;\n"
                + "            padding: 8px;\n"
                 + "            background-color: rgb(251,124,81);\n"
                
                + "        }\n"
                + "\n"
                + "        tr:nth-child(even) {\n"
                + "            background-color: rgb(64,180,229);\n"
                + "        }\n"
                  + "        tr:nth-child(odd) {\n"
                + "            background-color: #dddddd;\n"
                + "        }\n"
                + "\n"
                + "    </style>");
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

        try {
            boolean flag = false;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee1", "root", "root");
//           
            Statement ch = con.createStatement();

            ResultSet rs = ch.executeQuery("select * from student");
          
            while (rs.next()) {
                if (rs.getInt(8)>=range) {
                    pw.println("<tr>\n"
                            + "    <td>" + rs.getInt(1) + "</td>\n"
                            + "    <td>" + rs.getString(2) + "</td>\n"
                            + "    <td>" + rs.getString(5) + "</td>\n"
                            + "    <td>" + rs.getString(10) + "</td>\n"
                            + "    <td>" + rs.getString(6) + "</td>\n"
                            + "    <td>" + rs.getString(7) + "</td>\n"
                            + "    <td>" + rs.getString(8) + "</td>\n"
                            + "  </tr>");
                    flag = true;
                   
                }
            }
            if(flag==false)
                pw.println("<h1> no dues greater than"+ range);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
