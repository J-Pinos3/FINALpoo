package User;
import User.database_usuario.makeSaleWindow_mysql;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class makeSaleWindow extends JFrame{
    private JTextField dniJT;
    private JTextField nameJT;
    private JTextField lastJT;
    private JTextField adressJt;
    private JTextField phoneJT;
    private JButton queryButton;
    private JTextField ivaJT;
    private JTextField totalJT;
    private JTextField moneyUserJT;
    private JButton printButton;
    private JLabel subTotalJL;
    private JLabel cambioJL;
    private JPanel makeSale;

    public makeSaleWindow() {
        setTitle("Producto");
        setContentPane(makeSale);
        setSize(800,650);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DNI = dniJT.getText();
                List<String> Lista_venta = makeSaleWindow_mysql.Buscar_Venta(DNI);
                for (int i = 0;  i < Lista_venta.size(); i ++) {
                    dniJT.setText((Lista_venta.get(0)));
                    nameJT.setText((Lista_venta.get(1)));
                    lastJT.setText((Lista_venta.get(2)));
                    adressJt.setText((Lista_venta.get(3)));
                    phoneJT.setText((Lista_venta.get(4)));
                    ivaJT.setText((Lista_venta.get(5)));
                    totalJT.setText((Lista_venta.get(6)));
                    moneyUserJT.setText((Lista_venta.get(7)));
                    cambioJL.setText((Lista_venta.get(8)));
                }

                // Crear archivo PDF con los datos de venta
                try {
                    crearPDF_Venta(dniJT.getText(), nameJT.getText(), lastJT.getText(), adressJt.getText(),
                            phoneJT.getText(), ivaJT.getText(), totalJT.getText(), moneyUserJT.getText(),
                            cambioJL.getText());
                } catch (FileNotFoundException | DocumentException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void crearPDF_Venta(String dni, String nombre, String apellido, String direccion, String telefono,
                                      String iva, String total, String dinero, String cambio) throws FileNotFoundException, DocumentException {

        // Se crea el documento
        Document documento = new Document();

        // El OutPutStream para el fichero donde crearemos el PDF
        FileOutputStream ficheroPDF = new FileOutputStream("Venta.pdf");

        // Se asocia el documento de OutPutStream
        PdfWriter.getInstance(documento, ficheroPDF);

        // Se abre el documento
        documento.open();

        // Parrafo
        Paragraph titulo = new Paragraph("Datos de venta \n\n",
                FontFactory.getFont("arial",
                        22,
                        Font.BOLD,
                        BaseColor.BLUE
                )
        );

        // Añadimos el titulo al documento
        documento.add(titulo);

        // Creamos una tabla
        PdfPTable tabla = new PdfPTable(2);
        tabla.addCell("Campo");
        tabla.addCell("Valor");
        tabla.addCell("DNI");
        tabla.addCell(dni);
        tabla.addCell("Nombre");
        tabla.addCell(nombre);
        tabla.addCell("Apellido");
        tabla.addCell(apellido);
        tabla.addCell("Dirección");
        tabla.addCell(direccion);
        tabla.addCell("Teléfono");
        tabla.addCell(telefono);
        tabla.addCell("IVA");
        tabla.addCell(iva);
        tabla.addCell("Total");
        tabla.addCell(total);
        tabla.addCell("Dinero");
        tabla.addCell(dinero);
        tabla.addCell("Cambio");
        tabla.addCell(cambio);

        // Añadimos la tabla al documento
        documento.add(tabla);

        // Se cierra el documento
        documento.close();
    }
}
