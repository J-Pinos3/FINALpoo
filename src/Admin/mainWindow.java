package Admin;
import Admin.*;
import jdk.jfr.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mainWindow extends  JFrame{
    private JMenu subMenuUsers, subMenuProducts, subMenuProviders, subMenuReports, subMenuSell, subMenuEmpresa;
    private JMenuItem itemUsuario;
    private JMenuItem itemProducto;
    private  JMenuItem itemReports;
    private  JMenuItem itemShell;
    private JMenuItem itemPath;
    private JMenuItem itemProvider;
    private  JMenuItem itemEmpresa;



    public mainWindow(){
        // Create a main MenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create submenus for CRUD

            subMenuSell = new JMenu("Factura");
            menuBar.add(subMenuSell);

            subMenuUsers = new JMenu("Usuarios");
            menuBar.add(subMenuUsers);

            subMenuProducts = new JMenu("Productos");
            menuBar.add(subMenuProducts);

            subMenuProviders = new JMenu("Provedores");
            menuBar.add(subMenuProviders);

            subMenuReports = new JMenu("Reportes");
            menuBar.add(subMenuReports);

            subMenuEmpresa = new JMenu("Empresa");
            menuBar.add(subMenuEmpresa);


            // Items of 'Sell'
            itemUsuario = new JMenuItem("Usuarios");
            subMenuUsers.add(itemUsuario);

            itemUsuario.addActionListener(e -> new userWindow());

            itemEmpresa = new JMenuItem("Empresa");
            subMenuEmpresa.add(itemEmpresa);
            itemEmpresa.addActionListener(e -> new companyWindow());

            itemProvider = new JMenuItem("Provedor");
            subMenuProviders.add(itemProvider);
            itemProvider.addActionListener(e -> new providerWindow());


            itemProducto = new JMenuItem("Producto");
            subMenuProducts.add(itemProducto);
            itemProvider.addActionListener(e -> new productWindow());

            itemReports = new JMenuItem("Reporte");
            subMenuReports.add(itemReports);
            itemReports.addActionListener(e -> new  reportWindow());




        // Characteristics of Window
        setTitle("Veci's Market");
        setSize(617,650);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);






    }
}