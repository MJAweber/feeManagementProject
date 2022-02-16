
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

public class S_addAccountant extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        int id=Integer.parseInt(req.getParameter("aid"));   
        String name = req.getParameter("aname");
        String qua = req.getParameter("aqua");
        String uid = req.getParameter("auid");
        String pass = req.getParameter("apass");
        String mob = req.getParameter("amob");
        try {
            boolean flag = false;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee1", "root", "root");
//           
            Statement ch = con.createStatement();
            String query = "insert into accountant values(?,?,?,?,?,?)";
           
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = ch.executeQuery("select * from accountant");
            while (rs.next()) {
                if (rs.getString(2).equals(name) && rs.getString(4).equals(uid)) {
                    pw.println("<p style=\"text-align: center; color: grey; font-size: 2em;\" >User Already Found<br>");
                    pw.println("Kindly Login by Your Email and Password<br>");
                    pw.println("<p style=\"text-align: center; font-size: 1em;\"><a href=\"adminPanel.html\">go to Login page</a></p></p>");
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setString(3, qua);
                stmt.setString(4, uid);
                stmt.setString(5, pass);
                stmt.setString(6, mob);
                int check = stmt.executeUpdate();
                if (check > 0) {
//                  pw.println("Accountant added successsfully");
                    pw.println("<span style=\"font-size: 4em;\">Accountant Added Sucessfully</span>");

                    pw.println("<img src=\"https://marketingsmokeandmirrors.files.wordpress.com/2018/07/shutterstock_142333726b.jpg\" width=\"500px\" height=\"400px\" alt=\"\">");
                    pw.println(" <a href=\"adminPanel.html\" style=\"font-size: 3em; font-family: cursive;\n"
                            + "    color: green;\">Go To Admin Panel</a>");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
