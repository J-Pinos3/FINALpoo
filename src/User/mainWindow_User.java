package User;
import javax.swing.*;

public class mainWindow_User extends  JFrame{
    private JMenu subMenuProduct, subMenuClient, subMenuFac,subMenu;
    private JMenuItem itemProducto;
    private  JMenuItem itemClient;
    private JMenuItem itemFac;




    public mainWindow_User(){
        // Create a main MenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create submenus for CRUD



            subMenuProduct = new JMenu("Product");
            menuBar.add(subMenuProduct);

            subMenuClient = new JMenu("Clientes");
            menuBar.add(subMenuClient);

            subMenuFac = new JMenu("Facturas");
            menuBar.add(subMenuFac);



            itemClient = new JMenuItem("Cliente");
            subMenuClient.add(itemClient);
            itemClient.addActionListener(e -> new sellWindow());

            itemFac = new JMenuItem("Factura");
            subMenuFac.add(itemFac);
            itemFac.addActionListener(e -> new makeSaleWindow());


            itemProducto = new JMenuItem("Producto");
            subMenuProduct.add(itemProducto);
            itemProducto.addActionListener(e -> new searchWindow());




        // Characteristics of Window
        setTitle("Veci's Market");
        setSize(617,650);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);






    }
}