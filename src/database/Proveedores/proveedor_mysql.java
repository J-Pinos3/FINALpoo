package database.Proveedores;
import database.connection;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class proveedor_mysql {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs;
    String qry = "";

    public void Agregar_Proveedor(String ident, String nombre, String telefono, String email){
        qry = "INSERT INTO proveedor(ident_prov, nom_prov, tel_prov, ema_prov) VALUES(?,?,?,?)";

        try{
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setString(1,ident);
            ps.setString(2,nombre);
            ps.setString(3,telefono);
            ps.setString(4,email);

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Proveedor Registrado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Registrar el Proveedor");
            }
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }finally {
            try{
                conn.close();
            }catch (SQLException e1){
                System.out.println("Proveedores - Error: " + e1.toString());
            }
        }
    }


    public List Buscar_Proveedor(String RUC){
        List<String> Lista_proveedor = new ArrayList();
        conn = connection.getConnection();
        qry = "Select * from proveedor where ident_prov = ?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1,RUC);
            rs = ps.executeQuery();
            if(rs.next() == true){
                String dni = rs.getString("ident_prov");
                String nombre = rs.getString("nom_prov");
                String telefono = rs.getString("tel_prov");
                String correo = rs.getString("ema_prov");


                Lista_proveedor.add(dni);
                Lista_proveedor.add(nombre);
                Lista_proveedor.add(telefono);
                Lista_proveedor.add(correo);


            }
            else {
                JOptionPane.showMessageDialog(null, "Proveedor no encontrado");
            }

            ps.close();
            rs.close();
            conn.close();
        }
        catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,"Proveedor -> " + e.toString());
        }
        return Lista_proveedor;
    }


    public void Eliminar_Proveedor(String dni){
        qry = "DELETE FROM proveedor WHERE ident_prov = ?";
        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setString(1, dni);


            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Proveedor Eliminado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar el Proveedor");
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
    

    public void Actualizar_Proveedor(String ident, String nombre, String telefono, String email){
        qry="UPDATE Usuario SET   nom_prov = ?, tel_prov = ?, ema_prov = ? WHERE ident_prov = ?";

        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);

            ps.setString(1,nombre);
            ps.setString(2,telefono);
            ps.setString(3,email);
            ps.setString(4,ident);


            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Proveedor Actualizado Exitosamente");
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
