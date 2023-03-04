package Admin;

import database.Company.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class companyWindow extends JFrame{
    private JTextField rucTField;
    private JTextField nombreTField;
    private JTextField cellTField;
    private JTextField emailTField;
    private JTextField directionTField;
    private JPanel pantallaEmpresa;
    private JButton BUSCARButton;
    private JButton CREARButton;
    private JButton ACTUALIZARButton;
    private JButton ELIMINARButton;
    private company_mysql com_mysql = new company_mysql();

    public companyWindow() {

        setTitle("Empresa");
        setContentPane(pantallaEmpresa);
        setSize(800,650);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CREARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                com_mysql.Agregar_Empresa(rucTField.getText(), nombreTField.getText(), cellTField.getText(), emailTField.getText(), directionTField.getText());
                limpiarbotones();
            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                com_mysql.Eliminar_Empresa(rucTField.getText());
                limpiarbotones();
            }
        });
        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                com_mysql.Actualizar_Empresa(rucTField.getText(), nombreTField.getText(), cellTField.getText(), emailTField.getText(), directionTField.getText());
                limpiarbotones();
            }
        });
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Ruc = rucTField.getText();
                List<String> Lista_empresa = com_mysql.Buscar_Empresa(Ruc);
                for (int i = 0;  i < Lista_empresa.size(); i ++){
                    rucTField.setText((Lista_empresa.get(0)));
                   nombreTField.setText((Lista_empresa.get(1)));
                    cellTField.setText((Lista_empresa.get(2)));
                    emailTField.setText((Lista_empresa.get(3)));
                    directionTField.setText((Lista_empresa.get(4)));
                }
            }
        });
    }

    private void limpiarbotones(){
        rucTField.setText("");
        nombreTField.setText("");
        cellTField.setText("");
        emailTField.setText("");
        directionTField.setText("");

    }
}
