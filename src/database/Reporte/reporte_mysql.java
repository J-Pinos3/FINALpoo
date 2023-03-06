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

    public List qryAllInventory(String dia){
        List<String> Lista_usuario = new ArrayList();
        conn = connection.getConnection();
        qry = "Select cab.num_CF, cab.FKruc_Emp, cab.FKident_Cli,cab.FKident_Usu, cab.fecha_CF,dt.FKcod_pro, dt.total_pagar  from CabFactura as cab, detalle as dt where cab.num_CF = dt.FKnum_CF and Month(cab.fecha_CF) =?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1, dia);
            rs = ps.executeQuery();
            while(rs.next()){
                String idfac = Integer.toString(rs.getInt("cab.num_CF"));
                String ruc = rs.getString("cab.FKruc_Emp");
                String ci_cli = rs.getString("cab.FKident_Cli");
                String ci_usu = rs.getString("cab.FKident_Usu");
                String fecha = rs.getString("cab.fecha_CF");
                String cod_prod = rs.getString("dt.FKcod_pro");
                String total = Double.toString(rs.getDouble("total_pagar"));

                Lista_usuario.add(idfac);
                Lista_usuario.add(ruc);
                Lista_usuario.add(ci_cli);
                Lista_usuario.add(ci_usu);
                Lista_usuario.add(fecha);
                Lista_usuario.add(cod_prod);
                Lista_usuario.add(total);


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
/*
    public List<String> qryAllInventory(String dia) {
    List<String> Lista_usuario = new ArrayList<>();
    conn = connection.getConnection();
    qry = "SELECT cab.num_CF, cab.FKruc_Emp, cab.FKident_Cli, cab.fecha_CF, dt.FKcod_pro, dt.total_pagar FROM CabFactura AS cab, detalle AS dt WHERE cab.num_CF = dt.FKnum_CF AND Month(cab.fecha_CF) = ?";
    try {
        ps = conn.prepareStatement(qry);
        ps.setString(1, dia);
        rs = ps.executeQuery();
        while (rs.next()) {
            String idfac = Integer.toString(rs.getInt("num_CF"));
            String ruc = rs.getString("FKruc_Emp");
            String ci_cli = rs.getString("FKident_Cli");
            String fecha = rs.getString("fecha_CF");
            String cod_prod = rs.getString("FKcod_pro");
            String total = Double.toString(rs.getDouble("total_pagar"));

            Lista_usuario.add(idfac);
            Lista_usuario.add(ruc);
            Lista_usuario.add(ci_cli);
            Lista_usuario.add(fecha);
            Lista_usuario.add(cod_prod);
            Lista_usuario.add(total);
        }

        ps.close();
        rs.close();
        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }
    return Lista_usuario;
}

*
*
*/