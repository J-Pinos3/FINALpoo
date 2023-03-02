import javax.swing.*;
import java.sql.*;

public class connection{


    public Connection getConnection(){

        Connection conn = null;
        String base = "tiendabuho";
        String url = "jdbc:mysql://localhost:3306/" + base;
        String user = "root";
        String password = "dBase123";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            JOptionPane.showMessageDialog(null,"Conexion exitosa");
        }catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo conectar con la base de datos");
        }
        return conn;
    }





}