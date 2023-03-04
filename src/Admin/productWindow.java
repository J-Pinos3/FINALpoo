package Admin;

import database.Productos.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class productWindow extends JFrame{
    private JTextField codePrJT;
    private JTextArea detPrJT;
    private JTextField priceCJT;
    private JTextField priceVJT;
    private JComboBox providersCB;
    private JTextField stockJT;
    private JTextField descJT;
    private JButton CREATEButton;
    private JButton BUSCARButton;
    private JButton ELIMINARButton;
    private JPanel createPPanel;
    private JButton ACTUALIZARButton;
    private producto_mysql pro_mysql = new producto_mysql();

    public productWindow() {

        setTitle("Producto");
        setContentPane(createPPanel);
        setSize(800,650);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pro_mysql.CargarProveedor(providersCB);

        CREATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selec = providersCB.getSelectedIndex();
                String nom_prove = (String)providersCB.getItemAt(selec);

                pro_mysql.Agregar_Producto(codePrJT.getText(), detPrJT.getText(), Double.parseDouble(priceCJT.getText()) , Double.parseDouble(priceVJT.getText()) , Integer.parseInt(stockJT.getText()) , Double.parseDouble(descJT.getText()) , nom_prove);
                limpiarbotones();
            }
        });


        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pro_mysql.Eliminar_Producto(codePrJT.getText());
                limpiarbotones();
            }
        });


        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selec = providersCB.getSelectedIndex();
                String nom_prove = (String)providersCB.getItemAt(selec);

                pro_mysql.Actualizar_Producto(codePrJT.getText(), detPrJT.getText(), Double.parseDouble(priceCJT.getText()) , Double.parseDouble(priceVJT.getText()) , Integer.parseInt(stockJT.getText()) , Double.parseDouble(descJT.getText()) , nom_prove);
                limpiarbotones();
            }
        });


        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DNI = codePrJT.getText();
                List<String> Lista_user = pro_mysql.Buscar_Producto(DNI);

                for(int i = 0; i < Lista_user.size(); i++){
                    codePrJT.setText((Lista_user.get(0)));
                    detPrJT.setText((Lista_user.get(1)));
                    priceCJT.setText((Lista_user.get(2)));
                    priceVJT.setText((Lista_user.get(3)));
                    stockJT.setText((Lista_user.get(4)));
                    descJT.setText((Lista_user.get(5)));
                    providersCB.setSelectedItem((Lista_user.get(6)));

                }
            }
        });
    }

    private void limpiarbotones(){
        codePrJT.setText("");
        detPrJT.setText("");
        priceCJT.setText("");
        priceVJT.setText("");
        stockJT.setText("");
        descJT.setText("");
        pro_mysql.CargarProveedor(providersCB);
    }

}
