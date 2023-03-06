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
                String date = pathJT.getText();
                List<String> Lista_fac = rep_mysql.qryAllInventory(date);

                for(int i = 0; i < Lista_fac.size(); i++){
                    String id_fac = Lista_fac.get(i);
                    String ruc = Lista_fac.get(i);
                    String  ci_cli = Lista_fac.get(i);
                    String ci_usu = Lista_fac.get(i);
                    String fecha = Lista_fac.get(i);
                    String cod_prod =Lista_fac.get(i);
                    String total = Lista_fac.get(i);
                    System.out.println(Lista_fac.get(i));

                }
                try {
                    crearPDF(Lista_fac);

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
        FileOutputStream ficheroPDF = new FileOutputStream("Reporte.pdf");

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
        PdfPTable tabla = new PdfPTable(7);
        tabla.addCell("ID FACTURA");
        tabla.addCell("RUC EMPRESA");
        tabla.addCell("CEDULA CLIENTE");
        tabla.addCell("CEDULA USUARIO");
        tabla.addCell("FECHA FACTURA");
        tabla.addCell("COD PRODUCTO");
        tabla.addCell("TOTAL");


        for(int i = 0 ; i < lista.size() ; i++) {
            tabla.addCell(lista.get(i));
            System.out.println(lista.get(i));
        }


        // Añadimos la tabla al documento
        documento.add(tabla);

        // Se cierra el documento
        documento.close();
    }







}
