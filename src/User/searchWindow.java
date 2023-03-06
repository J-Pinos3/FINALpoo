package User;

import database.Productos.producto_mysql;
import database.connection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class searchWindow extends JFrame{
    private JComboBox codNomBox;
    private JTextField textoField;
    private JButton buscarButton;
    private JComboBox producClienteBox;
    private JPanel pantallaInventario;
    private JTable tabla;
    private JButton buscarPorCodigoButton;
    connection conn = new connection();
    Connection con = null;
    private PreparedStatement ps;
    private ResultSet rs;
    private producto_mysql pro_mysql = new producto_mysql();


    public searchWindow(){
        setTitle("Inventario");
        setContentPane(pantallaInventario);
        setSize(800,650);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    con = conn.getConnection();
                    String[] titulos = {"Código","Detalle","Precio Unitario","Precio Venta","Stock","Descuento"};
                    String[] registros = new String[7];

                    DefaultTableModel model = new DefaultTableModel(null,titulos);
                    tabla.setModel(model);
                    ps = con.prepareStatement("SELECT * FROM producto");
                    rs = ps.executeQuery();

                    while(rs.next()){
                        registros[0] = rs.getString("cod_Pro");
                        registros[1] = rs.getString("det_Pro");
                        registros[2] = rs.getString("preUni_Pro");
                        registros[3] = rs.getString("preVen_Pro");
                        registros[4] = rs.getString("sto_Pro");
                        registros[5] = rs.getString("desc_Pro");
                        model.addRow(registros);
                    }

                    con.close();
                    rs.close();
                    ps.close();

                    }catch(SQLException e3){
                        JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos: " + e3);
                    }

                }
            });

        buscarPorCodigoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    con = conn.getConnection();
                    String[] titulos = {"Código","Detalle","Precio Unitario","Precio Venta","Stock","Descuento"};
                    String[] registros = new String[6];
                    String[] etiquetas = {"Código de producto", "Detalle del producto", "Precio unitario", "Precio de venta", "Stock disponible", "Descuento aplicado"};
                    Object[][] datos = new Object[0][0];
                    DefaultTableModel model = new DefaultTableModel(null, titulos);


                    tabla.setModel(model);
                    ps = con.prepareStatement("SELECT * FROM producto WHERE cod_Pro = ?");
                    ps.setString(1,textoField.getText());
                    rs = ps.executeQuery();


                    while(rs.next()){
                        registros[0] = rs.getString("cod_Pro");
                        registros[1] = rs.getString("det_Pro");
                        registros[2] = rs.getString("preUni_Pro");
                        registros[3] = rs.getString("preVen_Pro");
                        registros[4] = rs.getString("sto_Pro");
                        registros[5] = rs.getString("desc_Pro");
                        model.addRow(registros);
                    }



                    con.close();
                    rs.close();
                    ps.close();
                }catch(SQLException e3){
                    JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos: " + e3);
                }
            }
        });
    }

        //Limpiar entradas
        public void limpiarEntradas(){
            textoField.setText("");
            codNomBox.setSelectedIndex(0);
            producClienteBox.setSelectedIndex(0);
        }
    }
