package User;
import database.Company.company_mysql;
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
    private JTable tabla;
    private JButton buscarButton;
    private JComboBox producClienteBox;
    private JPanel pantallaInventario;
    connection conn = new connection();
    Connection con = null;
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

                limpiarEntradas();
            }
        });
    }

    //Limpiar entradas
    public void limpiarEntradas(){
        textoField.setText("");
        codNomBox.setSelectedIndex(0);
        producClienteBox.setSelectedIndex(0);
    }
    //Metodo para mostrar Datos
    public void mostrarDatos(){


        DefaultTableModel mod = new DefaultTableModel();
        mod.addColumn("Codigo Producto");
        mod.addColumn("Detalle");
        mod.addColumn("Precio Unitario");
        mod.addColumn("Precio Venta");
        mod.addColumn("Stock");
        mod.addColumn("Descripcion");
        tabla.setModel(mod);


    }
}
