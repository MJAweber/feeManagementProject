
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class S_feePaySearchStudent extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        int id = Integer.parseInt(req.getParameter("sid"));
        String name = req.getParameter("sname");
        int dues;
        int money = Integer.parseInt(req.getParameter("smoney"));
       

        try {
            boolean flag = false;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee1", "root", "root");
            Statement ch = con.createStatement();
            ResultSet rs = ch.executeQuery("select * from student");
            String query1 = "update student set money=";
            pw.println("<style>\n"
                    + "        * {\n"
                    + "            margin: 0;\n"
                    + "            padding: 0;\n"
                    + "        }\n"
                    + "\n"
                    + "        .container {\n"
                    + "            height: 450px;\n"
                    + "            width: 430px;\n"
                    + "            margin: auto;\n"
                       + "            border:2px solid green;\n"
                    + "        }\n"
                    + "\n"
                    + "        table {\n"
                    + "            font-family: arial, sans-serif;\n"
                    + "            border-collapse: collapse;\n"
                    + "            width: 100%;\n"
                    + "            border: 0;\n"
                    + "        }\n"
                    + "\n"
                    + "        td,\n"
                    + "        th {\n"
                    + "            text-align: left;\n"
                    + "            padding: 12px;\n"
                     + "            font-weight:bold;\n"
                    + "            width: 50%;\n"
                    + "        }\n"
                    + "\n"
                    + "        .nav {\n"
                    + "            width: 100%;\n"
                    + "            height: 130px;\n"
                    + "            background-color: green;\n"
                    + "            position: relative;\n"
                    + "        }\n"
                    + "\n"
                    + "        .head1 {\n"
                    + "            font-family: cursive;\n"
                    + "            font-size: 3em;\n"
                    + "            text-align: center;\n"
                    + "            padding: 5px;\n"
                    + "            color: white;\n"
                    + "        }\n"
                    + "\n"
                    + "        .payment {\n"
                    + "            height: 100px;\n"
                    + "            background-color: aliceblue;\n"
                    + "            border-bottom: 4px solid pink;\n"
                    + "        }\n"
                    + "\n"
                    + "        .table {\n"
                    + "            width: 90%;\n"
                    + "            margin: auto;\n"
                    + "        }\n"
                    + "\n"
                    + "        .back {\n"
                    + "            margin: auto;\n"
                    + "        }\n"
                    + "\n"
                    + "        .details {\n"
                    + "            width: 100%;\n"
                    + "            margin-bottom: 2%;\n"
                    + "            border-bottom: 2px solid lightgray;\n"
                    + "        }\n"
                    + "        .pay{\n"
                    + "            text-align: center;\n"
                    + "            padding: 5px;\n"
                    + "            color: green;\n"
                    + "            font-family: cursive;\n"
                    + "        }\n"
                    + "        h3{\n"
                    + "            text-align: center;\n"
                    + "            font-family: cursive;\n"
                    + "        }\n"
                    + "    </style>");
            while (rs.next()) {
                if (rs.getInt(1) == id && rs.getString(2).equals(name)) {
                    int total = rs.getInt(7);
                    Date date = new Date();
       SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss aa");
	//Setting the time zone
	
	System.out.println(dateTimeInGMT.format(new Date()));
                    dues = rs.getInt(6) - (rs.getInt(7) + money);
                    PreparedStatement stmt = con.prepareStatement(query1 + (total + money) + " , dues= " + dues + " where id=" + id);
                    int check = stmt.executeUpdate();
                    if (check > 0) {
                        pw.println("<div class=\"nav\">\n"
                                + "        <h1 class=\"head1\">Ducat India </h1>\n"
                                + "        <div class=\"container\">\n"
                                + "            <div class=\"payment\">\n"
                                + "                <h1 class=\"pay\" style=\"text-align: center;\">Rs. "+money+"</h1>\n"
                                + "                <h3>Payment Successful</h3>\n"
                                + "            </div>\n"
                                + "\n"
                                + "\n"
                                + "            <div class=\"details\">\n"
                                + "                <div class=\"table\">\n"
                                + "                    <table>\n"
                                + "                        <tr>\n"
                                + "                            <td>Paid On : </td>\n"
                                + "                            <td>"+dateTimeInGMT.format(new Date())+"</td>\n"
                                + "                        </tr>\n"
                                + "                        <tr>\n"
                                + "                            <td>Method : </td>\n"
                                + "                            <td> Cash</td>\n"
                                + "                        </tr>\n"
                                + "                    </table>\n"
                                + "                </div>\n"
                                + "            </div>\n"
                                + "            <div class=\"student\">\n"
                                + "                <div class=\"table\">\n"
                                + "                    <table>\n"
                                + "                        <tr>\n"
                                + "                            <td>Student Name : </td>\n"
                                + "                            <td>"+rs.getString(2)+"</td>\n"
                                + "                        </tr>\n"
                                + "                        <tr>\n"
                                + "                            <td>Course : </td>\n"
                                + "                            <td>"+rs.getString(5)+"</td>\n"
                                + "                        </tr>\n"
                                + "                        <tr>\n"
                                + "                            <td>Student Mobile :</td>\n"
                                + "                            <td>"+rs.getString(9)+"</td>\n"
                                + "                        </tr>\n"
                                + "                        <tr>\n"
                                + "                            <td>Total Fee :</td>\n"
                                + "                            <td>"+rs.getInt(6)+"</td>\n"
                                + "                        </tr>\n"
                                + "                        <tr>\n"
                                + "                            <td>Dues Now :</td>\n"
                                + "                            <td>"+dues+"</td>\n"
                                + "                        </tr>\n"
                                + "\n"
                                + "\n"
                                + "                    </table>\n"
                                + "                </div>\n"
                                + "            </div>\n"
                                + "\n"
                                + "        </div>\n"
                                + "        <a href=\"accountantPanel.html\"> Go Back</a>\n"
                                + "    </div>");
                    }

//                    if (check > 0) {
//                        pw.println("<div class=\"position\" style=\"text-align:center;\">\n"
//                                + "    <a style=\"font-size: 2em; color: black;\" href=\"accountantPanel.html\">Back</a>\n"
//                                + "    <img src=\"https://www.farehawker.com/img/success.gif\" width=\"600px\" height=\"450px\" alt=\"\">\n"
//                                + "</div>");
//
//                    } else {
//                        pw.println("payment unsuccessful");
//                    }
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
        int id = Integer.parseInt(req.getParameter("sid"));
        PrintWriter pw = res.getWriter();
        pw.println("<style>\n"
                + "        * {\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "        }\n"
                + "\n"
                + "        body {\n"
                + "            background-color:rgb(64,180,229);\n"
                + "        }\n"
                + "        table {\n"
                + "            font-family: arial, sans-serif;\n"
                + "            border-collapse: collapse;\n"
                + "            width: 100%;\n"
                + "        }\n"
                + "\n"
                + "        td,\n"
                + "        th {\n"
                + "            border: 2px solid white;\n"
                + "            text-align: left;\n"
                + "            padding: 8px;\n"
                + "        }\n"
                + "\n"
                + "        tr:nth-child(even) {\n"
                //                + "            background-color: rgb(64,180,229);\n"
                + "        }\n"
                + "        tr:nth-child(odd) {\n"
                + "            background-color: rgb(251, 124, 81);\n"
                + "        }\n"
                + "\n"
                + "        .content {\n"
                + "            width: 40%;\n"
                + "            margin: auto;\n"
                + "            height: 80vh;\n"
                + "            margin-top: 3%;\n"
                + "        }\n"
                + "\n"
                + "        h1 {\n"
                + "            text-align: center;\n"
                + "            font-family: cursive;\n"
                + "            font-size: 3em;\n"
                + "            color:white;\n"
                + "        }\n"
                + "    </style>");

        try {
            boolean flag = false;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fee1", "root", "root");
//           
            Statement ch = con.createStatement();

            ResultSet rs = ch.executeQuery("select * from student");

            while (rs.next()) {
                if (rs.getInt(1) == id) {
                    pw.println(" <h1>Student Details of -  " + rs.getString(2).toUpperCase() + "&nbsp;&nbsp;<a style=\"font-size: 0.5em; text-align: center; color: black;\" href=\"accountantPanel.html\">Go Back</a> </h1>");
                    pw.println("<div class=\"content\">\n"
                            + "<table style=\"border-collapse:collapse\" >\n"
                            + "<tr>\n"
                            + "    <th>DESCRIPTION</th>\n"
                            + "    <th>RESULT</th>\n"
                            + "  </tr>"
                            + "            <tr>\n"
                            + "                <td>Student ID :</td>\n"
                            + "                <td>" + rs.getInt(1) + "</td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td>Student NAME :</td>\n"
                            + "                <td>" + rs.getString(2) + "</td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td>Student EMAIL :</td>\n"
                            + "                <td>" + rs.getString(3) + "</td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td>Student GENDER :</td>\n"
                            + "                <td>" + rs.getString(4) + "</td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td>Student COURSE:</td>\n"
                            + "                <td>" + rs.getString(5) + "</td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td>Student TOTAL FEE :</td>\n"
                            + "                <td>" + rs.getInt(6) + "</td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td>Student FEE PAID :</td>\n"
                            + "                <td>" + rs.getInt(7) + "</td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td>Student TOTAL DUES :</td>\n"
                            + "                <td>" + rs.getInt(8) + "</td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td>Student CONTACT :</td>\n"
                            + "                <td>" + rs.getString(9) + "</td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td>Student ADDRESS :</td>\n"
                            + "                <td>" + rs.getString(10) + "</td>\n"
                            + "            </tr>"
                            + "\n"
                            + "        </table>\n"
                            + "    </div>"
                    );
//                    pw.println("<a style=\"font-size: 2em; text-align: center; color: white;\" href=\"accountantPanel.html\">Go Back</a>");
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                pw.println("Student Not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
