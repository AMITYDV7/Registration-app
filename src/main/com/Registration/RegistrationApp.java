import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/Register")
public class RegistrationApp extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String passwrd = req.getParameter("password");
        PrintWriter pw = resp.getWriter();

       // jdbc:mysql://localhost:3306/?user=root
        String url;
        url= "jdbc:mysql://localhost:3306/clients";

        String user = "root";
        String password = "Amit@2003";
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,passwrd);

         int rowAffected = ps.executeUpdate();

         if(rowAffected >0){
             pw.println("<h1>Successful store your info :)</h1>");
         }
         else{
             pw.println("<h1>Try again</h1>");
         }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }
}
