
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class S_loginAccountant extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        String uid = req.getParameter("AccUid");
        String pass = req.getParameter("AccPass");

        try {
            boolean flag = false;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee1", "root", "root");
//           
            Statement ch = con.createStatement();
            ResultSet rs = ch.executeQuery("select * from accountant");
            while (rs.next()) {
                if (rs.getString(4).equals(uid) && rs.getString(5).equals(pass)) {
                    RequestDispatcher rd = req.getRequestDispatcher("accountantPanel.html");
                    rd.forward(req, res);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                RequestDispatcher rd = req.getRequestDispatcher("accountantNotFound.html");
                rd.forward(req, res);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
