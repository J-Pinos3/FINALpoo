package User;
import database.Facturas.facturas_mysql;
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
    private JTextField dniCliente;
    private JTextField dniProveedor;
    private JTextField fecha_venta;
    private JTextField codigoProducto;
    private JTextField rucEmpresa;
    private JButton queryButton;
    private JTextField descuento;
    private JTextField totalJT;
    private JTextField moneyUserJT;
    private JButton printButton;
    private JLabel subTotalJL;
    private JLabel cambioJL;
    private JPanel makeSale;
    private JComboBox cmbCedVendedor;
    private JComboBox cmbRucEmpresa;
    private JTextField iva_Texto;
    private JButton PAGARButton;
    private JTextField cantidadProducto;
    private facturas_mysql  fac_mysql = new facturas_mysql();

    public makeSaleWindow() {
        setTitle("Producto");
        setContentPane(makeSale);
        setSize(800,650);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        fac_mysql.CargarCedulaVendedor(cmbCedVendedor);
        fac_mysql.CargarRucEmpresa(cmbRucEmpresa);


        PAGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selec = cmbCedVendedor.getSelectedIndex();
                String ced_vendedor = (String)cmbCedVendedor.getItemAt(selec);

                int selec2 = cmbRucEmpresa.getSelectedIndex();
                String ruc_emp = (String)cmbRucEmpresa.getItemAt(selec2);

                fac_mysql.insertar_cabecera(ruc_emp,dniCliente.getText(),ced_vendedor,fecha_venta.getText());
                fac_mysql.insertar_detalle(codigoProducto.getText(),Double.parseDouble(iva_Texto.getText()),Double.parseDouble(descuento.getText()),Double.parseDouble(moneyUserJT.getText()),Double.parseDouble(cantidadProducto.getText()));

            }
        });
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List <String> Fac = fac_mysql.exportar_pdf();
                for(int i = 0; i < Fac.size(); i++){
                    String id_fac = Fac.get(i);
                    String  cod_prod = Fac.get(i);
                    String Iva = Fac.get(i);
                    String descuento = Fac.get(i);
                    String total = Fac.get(i);

                    System.out.println(Fac.get(i));

                }
                try {
                    crearPDF(Fac);
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
        FileOutputStream ficheroPDF = new FileOutputStream("Factura.pdf");

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
        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell("ID FACTURA");
        tabla.addCell("COD PRODUCTO");
        tabla.addCell("IVA");
        tabla.addCell("DESCUENTO");
        tabla.addCell("TOTAL");


        for(int i = 0 ; i < lista.size() ; i++) {
            tabla.addCell(lista.get(i));
        }


        // Añadimos la tabla al documento
        documento.add(tabla);

        // Se cierra el documento
        documento.close();
    }

}
