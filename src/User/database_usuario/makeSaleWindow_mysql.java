package User.database_usuario;
import database.connection;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class makeSaleWindow_mysql {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs;
    static String qry = "";

    public static List Buscar_Venta(String DNI){
        List<String> Lista_venta = new ArrayList();
        conn = connection.getConnection();
        qry = "Select * from CabFactura where cident_Cli = ?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1,DNI);
            rs = ps.executeQuery();
            if(rs.next() == true){
                String dni = rs.getString("cident_Cli");
                String nombre = rs.getString("nomCli_CF");
                String apellido = rs.getString("apeCli_CF");
                String direccion = rs.getString("dirCli_CF");
                String telefono = rs.getString("telCli_CF");
                String subtotal = rs.getString("subT_CF");
                String iva = rs.getString("iva_CF");
                String total = rs.getString("valT_CF");

                Lista_venta.add(dni);
                Lista_venta.add(nombre);
                Lista_venta.add(apellido);
                Lista_venta.add(direccion);
                Lista_venta.add(telefono);
                Lista_venta.add(subtotal);
                Lista_venta.add(iva);
                Lista_venta.add(total);
            }
            else {
                JOptionPane.showMessageDialog(null, "Venta no encontrada");
            }
            ps.close();
            rs.close();
            conn.close();
        }
        catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,"Venta -> " + e.toString());
        }
        return Lista_venta;
    }
}
