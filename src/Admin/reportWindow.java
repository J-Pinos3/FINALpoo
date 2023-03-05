package Admin;
import database.Reporte.reporte_mysql;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;



public class reportWindow extends JFrame{
    private JTextField pathJT;
    private JButton GenerateButton;
    private JPanel configurationW;
    private reporte_mysql rep_mysql = new reporte_mysql();



    public reportWindow() {
        setContentPane(configurationW);
        setVisible(true);
        setSize(550, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);



        GenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<String> Lista_user = rep_mysql.qryAllInventory();

                for(int i = 0; i < Lista_user.size(); i++){
                    String cedula = Lista_user.get(i);

                    String nombre = Lista_user.get(i);

                    String apellido = Lista_user.get(i);

                    String fecha = Lista_user.get(i);
                    String celular =Lista_user.get(i);

                    String correo = Lista_user.get(i);

                    String rol = Lista_user.get(i);
                    String usuario = Lista_user.get(i) ;

                    String pass = Lista_user.get(i);

                    System.out.println(Lista_user.get(i));

                }
                try {
                    crearPDF(Lista_user);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (DocumentException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void crearPDF(List<String> lista) throws FileNotFoundException, DocumentException {
        // Se crea el documento
        Document documento = new Document();

        // El OutPutStream para el fichero donde crearemos el PDF
        FileOutputStream ficheroPDF = new FileOutputStream("Usuarios.pdf");

        // Se asocia el documento de OutPutStream
        PdfWriter.getInstance(documento, ficheroPDF);

        // Se abre el documento
        documento.open();

        // Parrafo
        Paragraph titulo = new Paragraph("Lista de personas \n\n",
                FontFactory.getFont("arial",
                        22,
                        Font.BOLD,
                        BaseColor.BLUE
                )
        );

        // Añadimos el titulo al documento
        documento.add(titulo);

        // Creamos una tabla
        PdfPTable tabla = new PdfPTable(9);
        tabla.addCell("ID");
        tabla.addCell("NOMBRE");
        tabla.addCell("APELLIDO");
        tabla.addCell("FECHA INGRESO");
        tabla.addCell("TELEFONO");
        tabla.addCell("EMAIL");
        tabla.addCell("ROL");
        tabla.addCell("USUARIO");
        tabla.addCell("PASSWORD");

        for(int i = 0 ; i < lista.size() ; i++) {
            tabla.addCell(lista.get(i));

        }



        // Añadimos la tabla al documento
        documento.add(tabla);

        // Se cierra el documento
        documento.close();
    }







}
