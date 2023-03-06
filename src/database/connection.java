package database;
import javax.swing.*;
import java.sql.*;

public class connection{

    public static Connection getConnection() {

        String url = "jdbc:mysql://mysql-david2405.alwaysdata.net/david2405_poo";
        String user = "david2405_poo";
        String password = "POO__123@@";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

            System.out.println("Connecion exitosa");

        } catch (Exception e) {
            System.out.println("Connecion fallida");

            e.getMessage();
        }
        return con;
    }

}