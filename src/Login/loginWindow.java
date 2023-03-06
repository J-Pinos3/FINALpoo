package Login;
import javax.swing.*;
import database.Login_mysql.login_mysql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginWindow extends JFrame{
    private JPanel pantallaLogin;
    private JComboBox rolBox;
    private JTextField usuarioTField;
    private JPasswordField contraTField;
    private JButton iniciarSesiónButton;
    private JButton salirButton;

    public loginWindow() {
        setContentPane(pantallaLogin);
        setVisible(true);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,500);
        login_mysql lmsql = new login_mysql();


        //*********************************************
        iniciarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre_usuario = usuarioTField.getText();
                String clave_usuario = contraTField.getText();
                lmsql.IniciarSesion(nombre_usuario, clave_usuario);
                lmsql.IniciarSesion_vendedor(nombre_usuario,clave_usuario);
                dispose();
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
