package database.Login_mysql;
import database.connection;


import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login_mysql {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    String qry = "";

    public void MostrarRol(JComboBox cmn){
        conn = connection.getConnection();
        String sql = "SELECT desc_rol from rol";
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                cmn.addItem( rs.getString("desc_rol") );
            }

            ps.close();
            rs.close();
            conn.close();
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }


    public void IniciarSesion(String usuario, String clave, String rol){
        conn = connection.getConnection();
        String sql = "SELECT usuN_Usu, pass_Usu, FKtipo_rol from Usuario WHERE FKtipo_rol = ? AND usuN_Usu = ? AND pass_Usu = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,rol);
            ps.setString(2,usuario);
            ps.setString(3,clave);

            rs = ps.executeQuery();
            if(rs.next() == false){
                JOptionPane.showMessageDialog(null,"El usuario ingresado no existe");
            }




            ps.close();

            conn.close();
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "error en iniciar sesion: " + e.toString());
        }
    }

    //******************************************
}
