package database.Clientes;
import database.connection;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class cliente_mysql {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    String qry = "";


    public List Buscar_Cliente(String DNI){
        List<String> Lista_usuario = new ArrayList();
        conn = connection.getConnection();
        qry = "Select * FROM cliente where ident_Cli = ?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1,DNI);
            rs = ps.executeQuery();
            if(rs.next() == true){
                String dni = rs.getString("ident_Cli");
                String nombre = rs.getString("nom_Cli");
                String apellido = rs.getString("ape_Cli");
                String telefono = rs.getString("tel_Cli");
                String correo = rs.getString("ema_Cli");
                String direccion = rs.getString("dir_Cli");


                Lista_usuario.add(dni);
                Lista_usuario.add(nombre);
                Lista_usuario.add(apellido);
                Lista_usuario.add(telefono);
                Lista_usuario.add(correo);
                Lista_usuario.add(direccion);


            }
            else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado");
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


    public void Agregar_Cliente(String dni, String nombre, String apellido, String telefono, String correo, String direccion){

        qry = "INSERT INTO cliente (ident_Cli, nom_Cli, ape_Cli, tel_Cli,ema_Cli, dir_Cli) VALUES (?,?,?,?,?,?)";

        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setString(1, dni);
            ps.setString(2,nombre);
            ps.setString(3,apellido);
            ps.setString(4,telefono);
            ps.setString(5,correo);
            ps.setString(6,direccion);

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Cliente Registrado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Registrar el Cliente");
            }


        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,"client -> "+ e.toString());
            e.printStackTrace();

        }finally {
            try{
                conn.close();
            }catch (SQLException e1){
                System.out.println("Cliente - Error: " + e1.toString());
            }
        }

    }


    public void Eliminar_Cliente(String dni){
        qry = "DELETE FROM cliente WHERE ident_Cli = ?";
        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setString(1, dni);


            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Cliente Eliminado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar el Cliente");
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


    public void Actualizar_Cliente(String dni, String nombre, String apellido, String telefono, String correo, String direccion){

        qry = "UPDATE cliente SET nom_Cli = ? , ape_Cli = ? , tel_Cli = ? ,ema_Cli = ? , dir_Cli = ?  WHERE ident_Cli = ?";

        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);

            ps.setString(1,nombre);
            ps.setString(2,apellido);
            ps.setString(3,telefono);
            ps.setString(4,correo);
            ps.setString(5,direccion);
            ps.setString(6, dni);

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Cliente Actualizado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Actualizar el Cliente");
            }


        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,"client -> "+ e.toString());
            e.printStackTrace();

        }finally {
            try{
                conn.close();
            }catch (SQLException e1){
                System.out.println("Cliente - Error: " + e1.toString());
            }
        }

    }

}
