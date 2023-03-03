package Admin;

import database.*;
import database.Usurios.usuario_mysql;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;


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

        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DNI = dniJT.getText();
                List<String> ListarCl = umsql.Buscar_Usuario(DNI);
                umsql.Buscar_Usuario(DNI);



            }
        });


        CREARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario_mysql umsql = new usuario_mysql();

                int selec =  rolesCB.getSelectedIndex();
                String roluser = (String)rolesCB.getItemAt(selec);

                umsql.Agregar_Usuario( dniJT.getText(), nameJT.getText(), lastNameJT.getText(),
                        dateIngJT.getText(), phoneJT.getText(), emailJT.getText(),
                        roluser, userJT.getText(), passwordJP.getText() );
            }
        });

        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario_mysql umsql = new usuario_mysql();
                umsql.Eliminar_Usuario(dniJT.getText());
            }
        });
    }



}
