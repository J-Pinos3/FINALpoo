package User;

import database.Clientes.cliente_mysql;

import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class sellWindow extends JFrame{
    private JTextField cedCliente;
    private JTextField nomCliente;
    private JTextField apeCliente;
    private JTextField telfCliente;
    private JTextField mailCliente;
    private JTextField direcCliente;
    private JButton CREARButton;
    private JButton BUSCARButton;
    private JButton ACTUALIZARButton;
    private JButton ELIMINARButton;
    private JPanel pestanaCliente;
    private cliente_mysql cli_mysql = new cliente_mysql();


    public sellWindow() {

        setContentPane(pestanaCliente);
        setVisible(true);
        setSize(550, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        CREARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cli_mysql.Agregar_Cliente(cedCliente.getText(), nomCliente.getText(), apeCliente.getText(), telfCliente.getText(), mailCliente.getText(), direcCliente.getText());
                limpiarbotones();
            }
        });


        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cli_mysql.Eliminar_Cliente(cedCliente.getText());
                limpiarbotones();
            }
        });


        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cli_mysql.Actualizar_Cliente(cedCliente.getText(), nomCliente.getText(), apeCliente.getText(), telfCliente.getText(), mailCliente.getText(), direcCliente.getText());
                limpiarbotones();
            }
        });


        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String DNI = cedCliente.getText();
                List<String> Lista_user = cli_mysql.Buscar_Cliente(DNI);

                for(int i = 0; i < Lista_user.size(); i++){
                    cedCliente.setText((Lista_user.get(0)));
                    nomCliente.setText((Lista_user.get(1)));
                    apeCliente.setText((Lista_user.get(2)));
                    telfCliente.setText((Lista_user.get(3)));
                    mailCliente.setText((Lista_user.get(4)));
                    direcCliente.setText((Lista_user.get(5)));
                }
            }
        });


    }

    private void limpiarbotones(){
        cedCliente.setText("");
        nomCliente.setText("");
        apeCliente.setText("");
        telfCliente.setText("");
        mailCliente.setText("");
        direcCliente.setText("");

    }

}
