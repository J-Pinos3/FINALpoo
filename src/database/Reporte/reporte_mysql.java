package database.Reporte;

import database.connection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class reporte_mysql {
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection conn = null;
    private String qry ="";

    public List qryAllInventory(){
        List<String> Lista_usuario = new ArrayList();
        conn = connection.getConnection();
        qry = "Select * from usuario";
        try {
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            while(rs.next()){
                String dni = rs.getString("ident_Usu");
                String nombre = rs.getString("nom_Usu");
                String apellido = rs.getString("ape_Usu");
                String ingreso = rs.getString("ing_Usu");
                String telefono = rs.getString("tel_Usu");
                String correo = rs.getString("ema_Usu");
                String rol = rs.getString("FKtipo_rol");
                String usuario = rs.getString("usuN_Usu");
                String contrasenia = rs.getString("pass_Usu");

                Lista_usuario.add(dni);
                Lista_usuario.add(nombre);
                Lista_usuario.add(apellido);
                Lista_usuario.add(ingreso);
                Lista_usuario.add(telefono);
                Lista_usuario.add(correo);
                Lista_usuario.add(rol);
                Lista_usuario.add(usuario);
                Lista_usuario.add(contrasenia);

            }

            ps.close();
            rs.close();
            conn.close();
        }
        catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return Lista_usuario;
    }

}
