package Admin;
import Admin.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mainWindow extends  JFrame{
    private JMenu subMenuUsers, subMenuProducts, subMenuProviders, subMenuRoles, subMenuInventory
            , subMenuReports, subMenuSell, subMenuOption;
    private JMenuItem itemUsuario;
    private JMenuItem itemDeleteU;
    private JMenuItem itemRegisterP;
    private JMenuItem itemUpdateP;
    private JMenuItem itemDeletePr;
    private JMenuItem itemUpdatePr;
    private JMenuItem itemDeleteR;
    private JMenuItem itemUpdateR;
    private JMenuItem itemInventory;
    private JMenuItem itemSearchAll;
    private JMenuItem itemPath;
    private JMenuItem itemEmp;
    private JMenuItem itemUpdateU;
    private JMenuItem itemGenerateSell;
    private JMenuItem itemDeleteP;
    private JMenuItem itemRegisterPr;

    private ImageIcon image;


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

            subMenuProviders = new JMenu("Proveedores");
            menuBar.add(subMenuProviders);

            subMenuRoles = new JMenu("Roles");
            menuBar.add(subMenuRoles);

            subMenuInventory = new JMenu("Consultar");
            menuBar.add(subMenuInventory);

            subMenuReports = new JMenu("Reportes");
            menuBar.add(subMenuReports);

            subMenuOption = new JMenu("Opciones");
            menuBar.add(subMenuOption);


            // Items of 'Sell'
            itemGenerateSell = new JMenuItem("Generar");
            subMenuSell.add(itemGenerateSell);

            itemUsuario = new JMenuItem("Usuarios");
            subMenuUsers.add(itemUsuario);

            itemUsuario.addActionListener(e -> new userWindow());






        // Characteristics of Window
        setTitle("Veci's Market");
        setSize(617,650);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);






    }
}