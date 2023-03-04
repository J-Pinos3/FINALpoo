package database.Company;

import database.connection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class company_mysql {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs;
    String qry = "";



    public void Agregar_Empresa(String Ruc, String nombre, String telefono, String email, String Direccion){
        qry = "INSERT INTO empresa(ruc_Emp,nom_Emp,tel_Emp ,ema_Emp,dir_Emp) VALUES(?,?,?,?,?)";

        try{
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setString(1,Ruc);
            ps.setString(2,nombre);
            ps.setString(3,telefono);
            ps.setString(4,email);
            ps.setString(5,Direccion);

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Empresa Registrada Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Registrar La Empresa");
            }
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }finally {
            try{
                conn.close();
            }catch (SQLException e1){
                System.out.println("Empresa - Error: " + e1.toString());
            }
        }
    }


    public java.util.List Buscar_Empresa(String RUC){
        List<String> Lista_Empresa = new ArrayList();
        conn = connection.getConnection();
        qry = "Select * from empresa where ruc_Emp = ?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1,RUC);
            rs = ps.executeQuery();
            if(rs.next() == true){
                String Ruc = rs.getString("ruc_Emp");
                String nombre = rs.getString("nom_Emp");
                String telefono = rs.getString("tel_Emp");
                String correo = rs.getString("ema_Emp");
                String Direccion = rs.getString("dir_Emp");


                Lista_Empresa.add(RUC);
                Lista_Empresa.add(nombre);
                Lista_Empresa.add(telefono);
                Lista_Empresa.add(correo);
                Lista_Empresa.add(Direccion);


            }
            else {
                JOptionPane.showMessageDialog(null, "Empresa no encontrada");
            }

            ps.close();
            rs.close();
            conn.close();
        }
        catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,"Empresa -> " + e.toString());
        }
        return Lista_Empresa;
    }


    public void Eliminar_Empresa(String dni){
        qry = "DELETE FROM empresa WHERE ruc_Emp = ?";
        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setString(1, dni);


            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Empresa Eliminada Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar la Empresa");
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


    public void Actualizar_Empresa(String Ruc, String nombre, String telefono, String email,String direccion){
        qry="UPDATE empresa SET    nom_Emp = ?,  tel_Emp = ?, ema_Emp = ?, dir_Emp = ? WHERE ruc_Emp = ?";

        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);


            ps.setString(1,nombre);
            ps.setString(2,telefono);
            ps.setString(3,email);
            ps.setString(4,direccion);
            ps.setString(5,Ruc);


            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Empresa Actualizada Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Actualizar la Empresa");
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
