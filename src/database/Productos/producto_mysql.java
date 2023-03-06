package database.Productos;
import database.connection;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class producto_mysql {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs;
    String qry = "";

    public void CargarProveedor(JComboBox cmb){
        conn = connection.getConnection();
        qry = "SELECT nom_prov from proveedor";
        try{
            ps = conn.prepareStatement(qry);

            rs = ps.executeQuery();
            while(rs.next()){
                cmb.addItem( rs.getString("nom_prov") );
            }

            ps.close();
            rs.close();
            conn.close();
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "combo provee-product: " + e.toString());
        }
    }


    public void Agregar_Producto(String codigo, String detalle, double prec_unitario, double prec_venta, int stock, double descuento, String prove){
        qry="INSERT INTO producto(cod_Pro, det_Pro, preUni_Pro, preVen_Pro, sto_Pro, desc_Pro, FKident_Prov) VALUES(?,?,?,?,?,?,?)";

        try{
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setString(1,codigo);
            ps.setString(2,detalle);
            ps.setDouble(3,prec_unitario);
            ps.setDouble(4,prec_venta);
            ps.setInt(5,stock);
            ps.setDouble(6,descuento);
            ps.setString(7,prove);

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Producto Registrado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Registrar el Producto");
            }
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }finally {
            try{
                conn.close();
            }catch (SQLException e1){
                System.out.println("Productos - Error: " + e1.toString());
            }
        }
    }


    public List Buscar_Producto(String codigo){
        List<Object> Lista_proveedor = new ArrayList();
        //le puse de tipo objeto xq en esta lista hay datos que no son String
        conn = connection.getConnection();
        qry = "Select * from producto where cod_Pro = ?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1,codigo);
            rs = ps.executeQuery();
            if(rs.next() == true){
                String codProd = rs.getString("cod_Pro");
                String detalle = rs.getString("det_Pro");
                double prec_unitario = rs.getDouble("preUni_Pro");
                double prec_venta = rs.getDouble("preVen_Pro");
                int stock = rs.getInt("sto_Pro");
                double descuento = rs.getDouble("desc_Pro");
                String proveedor = rs.getString("FKident_Prov");

                Lista_proveedor.add(codProd);
                Lista_proveedor.add(detalle);
                Lista_proveedor.add(prec_unitario);
                Lista_proveedor.add(prec_venta);
                Lista_proveedor.add(stock);
                Lista_proveedor.add(descuento);
                Lista_proveedor.add(proveedor);


            }
            else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado");
            }

            ps.close();
            rs.close();
            conn.close();
        }
        catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,"Producto -> " + e.toString());
        }
        return Lista_proveedor;
    }


    public void Eliminar_Producto(String codigo){
        qry = "DELETE FROM producto WHERE cod_Pro = ?";
        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);
            ps.setString(1, codigo);


            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Producto Eliminado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar el Producto");
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


    public void Actualizar_Producto(String codigo, String detalle, double prec_unitario, double prec_venta, int stock, double descuento, String prove){
        qry="UPDATE producto SET det_Pro = ?, preUni_Pro = ?, preVen_Pro = ?, sto_Pro = ?, desc_Pro = ?, FKident_Prov = ? WHERE cod_Pro = ?";

        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(qry);


            ps.setString(1,detalle);
            ps.setDouble(2,prec_unitario);
            ps.setDouble(3,prec_venta);
            ps.setInt(4,stock);
            ps.setDouble(5,descuento);
            ps.setString(6,prove);
            ps.setString(7,codigo);

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Producto Actualizado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Actualizar el Producto");
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
    public List Buscar_Productos(){
        List<Object> Lista_proveedor = new ArrayList();
        //le puse de tipo objeto xq en esta lista hay datos que no son String
        conn = connection.getConnection();
        qry = "Select * from producto";
        try {
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            while (rs.next()){
                String codProd = rs.getString("cod_Pro");
                String detalle = rs.getString("det_Pro");
                String prec_unitario = String.valueOf(rs.getDouble("preUni_Pro")) ;
                String prec_venta = String.valueOf(rs.getString("preVen_Pro"));
                String stock = String.valueOf(rs.getString("sto_Pro")) ;
                String descuento = String.valueOf(rs.getFloat("desc_Pro"));


                Lista_proveedor.add(codProd);
                Lista_proveedor.add(detalle);
                Lista_proveedor.add(prec_unitario);
                Lista_proveedor.add(prec_venta);
                Lista_proveedor.add(stock);
                Lista_proveedor.add(descuento);


            }

            ps.close();
            rs.close();
            conn.close();
        }
        catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,"Producto -> " + e.toString());
        }
        return Lista_proveedor;
    }

}
