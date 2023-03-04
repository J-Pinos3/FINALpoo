package Admin;

import database.*;
import database.Usurios.usuario_mysql;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class userWindow extends JFrame{
    private JPanel employeWindow;
    private JTextField dniJT;
    private JTextField nameJT;
    private JTextField lastNameJT;
    private JTextField phoneJT;
    private JTextField emailJT;
    private JTextField userJT;
    private JTextField dateIngJT;
    private JPasswordField passwordJP;

    private JComboBox rolesCB;
    private JButton CREARButton;
    private JButton ACTUALIZARButton;
    private JButton ELIMINARButton;
    private JButton BUSCARButton;
    private usuario_mysql umsql = new usuario_mysql();
    public userWindow() {

        // Characteristics of Window
        setContentPane(employeWindow);
        setVisible(true);
        setSize(550, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        umsql.CargarRol(rolesCB);

        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DNI = dniJT.getText();
                List<String> Lista_user = umsql.Buscar_Usuario(DNI);

                for(int i = 0; i < Lista_user.size(); i++){
                    dniJT.setText((Lista_user.get(0)));
                    nameJT.setText((Lista_user.get(1)));
                    lastNameJT.setText((Lista_user.get(2)));
                    dateIngJT.setText((Lista_user.get(3)));
                    phoneJT.setText((Lista_user.get(4)));
                    emailJT.setText((Lista_user.get(5)));
                    rolesCB.setSelectedItem((Lista_user.get(6)));
                    userJT.setText((Lista_user.get(7)));
                    passwordJP.setText((Lista_user.get(8)));

                }

            }
        });


        CREARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario_mysql umsql = new usuario_mysql();

                int selec =  rolesCB.getSelectedIndex();
                String roluser = (String)rolesCB.getItemAt(selec);

                System.out.println(roluser);
                umsql.Agregar_Usuario( dniJT.getText(), nameJT.getText(), lastNameJT.getText(),
                        dateIngJT.getText(), phoneJT.getText(), emailJT.getText(),
                         userJT.getText(), passwordJP.getText(),roluser );
                limpiarbotones();
            }
        });


        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario_mysql umsql = new usuario_mysql();
                umsql.Eliminar_Usuario(dniJT.getText());
                limpiarbotones();

            }
        });




        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selec =  rolesCB.getSelectedIndex();
                String roluser = (String)rolesCB.getItemAt(selec);
                umsql.Actualizar_Usuario(dniJT.getText(), nameJT.getText(), lastNameJT.getText(),
                        dateIngJT.getText(), phoneJT.getText(), emailJT.getText(),
                         userJT.getText(), passwordJP.getText(),roluser);
                limpiarbotones();
            }
        });
    }
    private void limpiarbotones(){
        dniJT.setText("");
        lastNameJT.setText("");
        passwordJP.setText("");
        phoneJT.setText("");
        emailJT.setText("");
        dateIngJT.setText("");
        userJT.setText("");
        umsql.CargarRol(rolesCB);

    }



}
