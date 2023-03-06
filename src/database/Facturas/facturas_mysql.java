package database.Facturas;

import database.connection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class facturas_mysql {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private PreparedStatement ps2 = null;
    private PreparedStatement ps3 = null;
    private  ResultSet rs3;
    private ResultSet rs2;
    private ResultSet rs;
    String qry = "";

    public void CargarCedulaVendedor(JComboBox cmn){
        conn = connection.getConnection();
        qry = "SELECT ident_Usu from usuario";
        try{
            ps = conn.prepareStatement(qry);

            rs = ps.executeQuery();
            while(rs.next()){
                cmn.addItem( rs.getString("ident_Usu") );
            }

            ps.close();
            rs.close();
            conn.close();
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "combo ced empleado: " + e.toString());
        }
    }

    public void CargarRucEmpresa(JComboBox cmn){
        conn = connection.getConnection();
        qry = "SELECT ruc_Emp from empresa";
        try{
            ps = conn.prepareStatement(qry);

            rs = ps.executeQuery();
            while(rs.next()){
                cmn.addItem( rs.getString("ruc_Emp") );
            }

            ps.close();
            rs.close();
            conn.close();
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "combo ruc empresa: " + e.toString());
        }
    }



   public  void insertar_cabecera(String ruc, String ci_cl, String ci_us, String fecha_fc){
       qry = "insert into CabFactura(FKruc_Emp,FKident_Cli,FKident_Usu,fecha_CF) values(?,?,?,?)";

       try{
           conn = connection.getConnection();
           ps = conn.prepareStatement(qry);
           ps.setString(1,ruc);
           ps.setString(2,ci_cl);
           ps.setString(3,ci_us);
           ps.setString(4,fecha_fc);
           int res = ps.executeUpdate();
           if(res > 0){
               System.out.println("cabecera creada");
           }else{
               System.out.println("Error al crear la cabecera de la factura");
           }
       }catch (HeadlessException | SQLException e){
            e.printStackTrace();
       }

   }

   public void insertar_detalle(String cod_prod, double Iva,double descuento, double total_pagar,double cantidad){
       conn = connection.getConnection();
       int ultimo_cabecera = 0;
       String qry2 = "Select num_CF from CabFactura order by num_CF DESC LIMIT 1";

       try {

           ps = conn.prepareStatement(qry2);
           rs = ps.executeQuery();


           if(rs.next()== true){
               ultimo_cabecera =rs.getInt("num_CF");
               System.out.println(ultimo_cabecera);

           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       qry = "insert into detalle (FKnum_CF,FKcod_pro,Iva,descuento,valto_det,cantPro_Det) values (?,?,?,?,?,?)";
       try {
           ps2 = conn.prepareStatement(qry);
           ps2.setInt(1,ultimo_cabecera);
           ps2.setString(2,cod_prod);
           ps2.setDouble(3,Iva);
           ps2.setDouble(4,descuento);
           ps2.setDouble(5,total_pagar);
           ps2.setDouble(6,cantidad);
           ps2.executeUpdate();
           int res2 = ps2.executeUpdate();
           if (res2 > 0){
               System.out.println("detalle creado");

           }
       }
       catch (SQLException e){
           JOptionPane.showMessageDialog(null,"problema al insertar detalle" + e.toString());
       }

       String qury3 = "update detalle set total_pagar =  (valto_det+(Iva*valto_det)+((descuento*valto_det/100)))";
       try {
           ps3 = conn.prepareStatement(qury3);
           ps3.executeUpdate();

       }
       catch (SQLException e){
           JOptionPane.showMessageDialog(null,"problema al modificar detalle");
       }

   }
   public List exportar_pdf(){
       List<String> Lista_fac = new ArrayList();
       conn = connection.getConnection();
        qry = "Select FKnum_CF,FKcod_pro,Iva,descuento,total_pagar from detalle order by id_De DESC limit 1";
        try{
            ps = conn.prepareStatement(qry);
            rs = ps.executeQuery();
            while (rs.next()){
                String fac = Integer.toString(rs.getInt("FKnum_CF"));
                String cod_prod = rs.getString("FKcod_pro");
                String iva = Double.toString(rs.getDouble("Iva"));
                String descuento_fac = Double.toString(rs.getDouble("descuento"));
                String total_pagar = Double.toString(rs.getDouble("total_pagar"));

                Lista_fac.add(fac);
                Lista_fac.add(cod_prod);
                Lista_fac.add(iva);
                Lista_fac.add(descuento_fac);
                Lista_fac.add(total_pagar);

            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Lista_fac;
   }
}//FIN DE LA CLASE facturas_mysql
