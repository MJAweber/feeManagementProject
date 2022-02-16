
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class servAdmin1 extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
         Boolean flag = false;
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String email = req.getParameter("AdminUid");
        String pass = req.getParameter("AdminPass");
       
        try{
            Class.forName("com.mysql.jdbc.Driver");
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee1", "root", "root");
//           
            Statement ch = con.createStatement();
            ResultSet rs = ch.executeQuery("select * from admin");
//            Boolean flag = false;
            while (rs.next()) {
                if (rs.getString(1).equals(email) && rs.getString(2).equals(pass)) {
                   RequestDispatcher rd=req.getRequestDispatcher("adminPanel.html");
                   rd.forward(req, res);
                }
                else
                {
                    RequestDispatcher rd=req.getRequestDispatcher("adminNotFound.html");
                   rd.include(req, res);
                }
            }
            con.close();
        }catch(Exception e){
            
            e.printStackTrace();
        }
        
    }

}