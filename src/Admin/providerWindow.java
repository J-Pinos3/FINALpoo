package Admin;

import database.Proveedores.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class providerWindow extends JFrame{
    private JTextField txtRuc;
    private JTextField txtNombre;
    private JTextField txtTelef;
    private JTextField txtEmail;
    private JButton CREARButton;
    private JButton BUSCARButton;
    private JButton ELIMINARButton;
    private JButton ACTUALIZARButton;
    private JPanel proveedorwindoww;
    private proveedor_mysql promysql = new proveedor_mysql();

    public providerWindow() {
        setTitle("Proveedor");
        setContentPane(proveedorwindoww);
        setSize(800,650);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        CREARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promysql.Agregar_Proveedor(txtRuc.getText(), txtNombre.getText(), txtTelef.getText(), txtEmail.getText());
                limpiarbotones();
            }
        });


        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promysql.Eliminar_Proveedor(txtRuc.getText());
                limpiarbotones();
            }
        });
        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promysql.Actualizar_Proveedor(txtRuc.getText(), txtNombre.getText(), txtTelef.getText(), txtEmail.getText());
                limpiarbotones();
            }
        });
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Ruc = txtRuc.getText();
                List<String> Lista_prov = promysql.Buscar_Proveedor(Ruc);
                for (int i = 0;  i < Lista_prov.size(); i ++){
                    txtRuc.setText((Lista_prov.get(0)));
                    txtNombre.setText((Lista_prov.get(1)));
                    txtTelef.setText((Lista_prov.get(2)));
                    txtEmail.setText((Lista_prov.get(3)));
                }
            }
        });
    }

    private void limpiarbotones(){
        txtRuc.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
        txtTelef.setText("");
    }
}
