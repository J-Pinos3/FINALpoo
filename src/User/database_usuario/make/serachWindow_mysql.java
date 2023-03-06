package User.database_usuario.make;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class serachWindow_mysql {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs;

    //Metodo para solo colocar la consulta SQL dentro de los metodos
    public ResultSet consulta(String sql){
        ResultSet res = null;
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            res = ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error consulta"+e);
        }
        return res;
    }

    //Combo box
    public DefaultComboBoxModel optener_productoCliente(){
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        listaModelo.addElement("Seleccionar");
        ResultSet res = this.consulta("Select * from producto");
        try{
            while(res.next()){
                listaModelo.addElement(res.getString("producto"));
            }
            res.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return listaModelo;
    }

}
