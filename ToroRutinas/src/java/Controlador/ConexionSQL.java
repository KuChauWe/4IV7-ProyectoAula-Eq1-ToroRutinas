package Controlador;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class ConexionSQL {
    
    public static Connection getConnection()
    throws ServletException, ClassNotFoundException, SQLException{
        
        String url = "jdbc:mysql://mwacbjvbigo6qre0:qu2y4jeobyskeac6@bmlx3df4ma7r1yh4.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/gf9ditlk93fbnqy2";
        String userName = "mwacbjvbigo6qre0";
        String password = "qu2y4jeobyskeac6";
        
        Connection con = null;
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, userName, password);
        System.out.println("Conexion exitosa con la BD");
        return con;
        
    }
}