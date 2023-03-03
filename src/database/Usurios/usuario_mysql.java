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

    public void MostrarRol(JComboBox cmn){
        conn = connection.getConnection();
        qry = "SELECT desc_rol from rol";
        try{
            ps = conn.prepareStatement(qry);

            rs = ps.executeQuery();
            while(rs.next()){
                cmn.addItem( rs.getString("desc_rol") );
            }

            ps.close();
            rs.close();
            conn.close();
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    public List Buscar_Usuario(String DNI){
        List<String> Lista_usuario = new ArrayList();
        conn = connection.getConnection();
        qry = "Select * from Usuario where ident_Usu = ?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1,DNI);
            rs = ps.executeQuery();
            while(rs.next()){
                String dni = rs.getString("ident_Usu");
                String nombre = rs.getString("nom_Usu");
                String apellido = rs.getString("ape_Usu");
                Date ingreso = rs.getDate("ing_Usu");
                String telefono = rs.getString("tel_Usu");
                String rol = rs.getString("FKtipo_rol");
                String usuario = rs.getString("usuN_Usu");
                String contrasenia = rs.getString("pass_Usu");

                Lista_usuario.add(0,dni);
                Lista_usuario.add(1,nombre);
                Lista_usuario.add(2,apellido);
                Lista_usuario.add(3,ingreso.toString());
                Lista_usuario.add(4,telefono);
                Lista_usuario.add(5,rol);
                Lista_usuario.add(6,usuario);
                Lista_usuario.add(7,contrasenia);

            }

            ps.close();
            rs.close();
            conn.close();

            return Lista_usuario;
        }
        catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
/*
   public List ListarCliente(){

        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while( rs.next() ){
                Cliente cl = new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setDni(rs.getInt("dni"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon(rs.getString("razon"));
                ListaCl.add(cl);
            }

        }catch (HeadlessException | SQLException e){
            System.out.println("Error: " + e.toString());
        }
        return ListaCl;
    }


-----------------------------------------------------------------
    public void ListarClientes(){
        List<Cliente> ListarCl = cliente.ListarCliente();
        String[] titulos = {"ID","Cédula/RUC","Nombre","Teléfono","Dirección","Razón Social"};
        //modelo = (DefaultTableModel) TableCliente.getModel();
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        Object[] obj = new Object[6];
        for(int i = 0; i < ListarCl.size(); i++){
            obj[0] = ListarCl.get(i).getId();
            obj[1] = ListarCl.get(i).getDni();
            obj[2] = ListarCl.get(i).getNombre();
            obj[3] = ListarCl.get(i).getTelefono();
            obj[4] = ListarCl.get(i).getDireccion();
            obj[5] = ListarCl.get(i).getRazon();

            modelo.addRow(obj);
        }
        TableCliente.setModel(modelo);
    }


*/
    public void Agregar_Usuario(String dni, String nombre, String apellido, String fechaINgreso, String telefono, String correo, String user, String password, String rol){

        String sql = "INSERT INTO Usuario (ident_Usu, nom_Usu, ape_Usu, ing_Usu,tel_Usu, ema_Usu, FKtipo_rol, usuN_Usu, pass_Usu) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(sql);
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
            JOptionPane.showMessageDialog(null, e.toString());

        }finally {
            try{
                conn.close();
            }catch (SQLException e1){
                System.out.println("Error: " + e1.toString());
            }
        }

    }


    public void Eliminar_Usuario(String dni){
        String sql = "DELETE FROM usuario WHERE DNI = ?";
        try {
            conn = connection.getConnection();
            ps = conn.prepareStatement(sql);
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
}
