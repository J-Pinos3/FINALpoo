package database.Usurios;
import database.connection;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class usuario_mysql {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    String qry = "";

    public void CargarRol(JComboBox cmn){
        conn = connection.getConnection();
        qry = "SELECT tipo_rol from rol";
        try{
            ps = conn.prepareStatement(qry);

            rs = ps.executeQuery();
            while(rs.next()){
                cmn.addItem( rs.getString("tipo_rol") );
            }

            ps.close();
            rs.close();
            conn.close();
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "combo rol: " + e.toString());
        }
    }
    
    public List Buscar_Usuario(String DNI){
        List<String> Lista_usuario = new ArrayList();
        conn = connection.getConnection();
        qry = "Select * from usuario where ident_Usu = ?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1,DNI);
            rs = ps.executeQuery();
            if(rs.next() == true){
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
            else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
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

    public void Agregar_Usuario(String dni, String nombre, String apellido, String fechaINgreso, String telefono, String correo, String user, String password, String rol){

        qry = "INSERT INTO usuario (ident_Usu, nom_Usu, ape_Usu, ing_Usu,tel_Usu, ema_Usu, FKtipo_rol, usuN_Usu, pass_Usu) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setString(1, dni);
            ps.setString(2,nombre);
            ps.setString(3,apellido);
            ps.setString(4,fechaINgreso);
            ps.setString(5,telefono);
            ps.setString(6,correo);
            ps.setString(7,rol);
            ps.setString(8,user);
            ps.setString(9,password);

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Usuario Registrado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Registrar el Usuario");
            }


        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,"user -> "+ e.toString());
            e.printStackTrace();

        }finally {
            try{
                conn.close();
            }catch (SQLException e1){
                System.out.println("Cliente - Error: " + e1.toString());
            }
        }

    }


    public void Eliminar_Usuario(String dni){
        qry = "DELETE FROM usuario WHERE ident_Usu = ?";
        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setString(1, dni);


            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Usuario Eliminado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar el Usuario");
            }


        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());

        }finally {
            try{
                conn.close();
            }catch (SQLException e1){
                System.out.println("Error: " + e1.toString());
            }
        }
    }


    public void Actualizar_Usuario(String dni, String nombre, String apellido, String fechaINgreso, String telefono, String correo, String user, String password, String rol){
        qry="UPDATE usuario SET   nom_Usu = ?, ape_Usu = ?, ing_Usu = ?, tel_Usu = ?, ema_Usu = ?, FKtipo_rol = ?, usuN_Usu = ?, pass_Usu = ? WHERE ident_Usu = ?";

        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);

            ps.setString(1,nombre);
            ps.setString(2,apellido);
            ps.setString(3,fechaINgreso);
            ps.setString(4,telefono);
            ps.setString(5,correo);
            ps.setString(6,rol);
            ps.setString(7,user);
            ps.setString(8,password);
            ps.setString(9, dni);

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Usuario Actualizado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Actualizar el Usuario");
            }


        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());

        }finally {
            try{
                conn.close();
            }catch (SQLException e1){
                System.out.println("Error: " + e1.toString());
            }
        }

    }
}
