package UI.utils;


import database.models.Cliente;
import database.models.Producto;
import database.models.Proveedor;
import database.models.SaldoPendiente;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CreateExcelFile {

    /*
     * Variables y metodos genericos
     * */

    public static String reportPath;

    public static String now = generateNow();

    private static final String GLOBALUSER = System.getProperty("user.name");

    public static void createFile(String reportPath, XSSFWorkbook libro) {
        try (OutputStream fileOut = new FileOutputStream(reportPath)) {
            libro.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("_dMMMyyyy_HH-mm"));
    }

    public static void setBoldStyle(XSSFWorkbook libro) {
        //Generar el estilo de fuente del libro a Bold
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        style.setFont(font);

    }

    /*
     * Metodos para exportar a excel
     * */

    public static void exportClientsToExcel(List<Cliente> clientes, String[] header, FileModel fileConfig) {

        String fileName = fileConfig.getFileName() + "Report" + now + ".xlsx";
        String hoja = fileConfig.getSheetName();
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);
        setBoldStyle(libro);
        int index = 0;

        for (int i = 0; i <= clientes.size(); i++) {
            XSSFRow row = hoja1.createRow(i); //Se crea la fila
            for (int j = 0; j < header.length; j++) {
                if (i == 0) { //para la cabecera
                    XSSFCell cell = row.createCell(j); //Se crean las celdas para la cabecera
                    cell.setCellValue(header[j]); //Se añade el contenido
                }
            }
            if (i != 0 && index < clientes.size()) {
                Cliente cliente = clientes.get(index);
                XSSFCell id = row.createCell(0);
                id.setCellValue(cliente.getIdCliente());
                XSSFCell nit = row.createCell(1);
                nit.setCellValue(cliente.getNit());
                XSSFCell nombre = row.createCell(2);
                nombre.setCellValue(cliente.getNombre());
                XSSFCell apellido = row.createCell(3);
                apellido.setCellValue(cliente.getApellido());
                XSSFCell direccion = row.createCell(4);
                direccion.setCellValue(cliente.getDireccion());
                XSSFCell telefono = row.createCell(5);
                telefono.setCellValue(cliente.getTelefono());
                index++;
            }
        }
        reportPath = "C:\\Users\\" + GLOBALUSER + "\\Downloads\\" + fileName;
        createFile(reportPath, libro);
    }

    public static void exportDebtorCustomersToExcel(List<SaldoPendiente> saldosPendientes, String[] header, FileModel fileConfig) {
        String fileName = fileConfig.getFileName() + "Report" + now + ".xlsx";
        String hoja = fileConfig.getSheetName();
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);
        setBoldStyle(libro);
        int index = 0;

        for (int i = 0; i <= saldosPendientes.size(); i++) {
            XSSFRow row = hoja1.createRow(i); //Se crea la fila
            for (int j = 0; j < header.length; j++) {
                if (i == 0) { //para la cabecera
                    XSSFCell cell = row.createCell(j); //Se crean las celdas para la cabecera
                    cell.setCellValue(header[j]); //Se añade el contenido
                }
            }
            if (i != 0 && index < saldosPendientes.size()) {
                SaldoPendiente saldoPendiente = saldosPendientes.get(index);
                XSSFCell id = row.createCell(0);
                id.setCellValue(saldoPendiente.getIdSaldoPendiente());
                XSSFCell nit = row.createCell(1);
                nit.setCellValue(saldoPendiente.getNitClient());
                XSSFCell nombre = row.createCell(2);
                nombre.setCellValue(saldoPendiente.getNombreCliente());
                XSSFCell totalPagar = row.createCell(3);
                totalPagar.setCellValue("Q" + saldoPendiente.getTotalPagar());
                XSSFCell serieVenta = row.createCell(4);
                serieVenta.setCellValue(saldoPendiente.getSerieVenta());
                XSSFCell deudaPendiente = row.createCell(5);
                deudaPendiente.setCellValue("Q" + saldoPendiente.getDeudaPendiente());
                XSSFCell abono = row.createCell(6);
                abono.setCellValue("Q" + saldoPendiente.getAbono());
                XSSFCell tipoPago = row.createCell(7);
                tipoPago.setCellValue(saldoPendiente.getTipoPago());

                index++;
            }
        }
        reportPath = "C:\\Users\\" + GLOBALUSER + "\\Downloads\\" + fileName;
        createFile(reportPath, libro);
    }

    public static void exportProductsToExcel(List<Producto> productos, String[] header, FileModel fileConfig) {
        String fileName = fileConfig.getFileName() + "Report" + now + ".xlsx";
        String hoja = fileConfig.getSheetName();
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);
        setBoldStyle(libro);
        int index = 0;
        if (productos.get(0).getStock() == null){
            System.out.println("productos mas vendidos");
            header[4] = "Cantidad productos vendidos";
            header[5] = "Ingresos generados";
            header[6] = "";
        }


        for (int i = 0; i <= productos.size(); i++) {
            XSSFRow row = hoja1.createRow(i); //Se crea la fila
            for (int j = 0; j < header.length; j++) {
                if (i == 0) { //para la cabecera
                    XSSFCell cell = row.createCell(j); //Se crean las celdas para la cabecera
                    cell.setCellValue(header[j]); //Se añade el contenido
                }
            }
            if (i != 0 && index < productos.size()) {
                Producto producto = productos.get(index);
                XSSFCell id = row.createCell(0);
                id.setCellValue(producto.getIdProducto());
                XSSFCell nombre = row.createCell(1);
                nombre.setCellValue(producto.getNombre());
                XSSFCell precio = row.createCell(2);
                precio.setCellValue("Q" + producto.getPrecio());
                XSSFCell descripcion = row.createCell(3);
                descripcion.setCellValue(producto.getDescripcion());
                if (producto.getStock() != null) {
                    XSSFCell stock = row.createCell(4);
                    stock.setCellValue(producto.getStock());
                    if (producto.getIdProvider() == null){
                        XSSFCell provider = row.createCell(5);
                        provider.setCellValue(producto.getProvider());
                    } else {
                        XSSFCell idProvider = row.createCell(5);
                        idProvider.setCellValue(producto.getIdProvider());
                        XSSFCell provider = row.createCell(6);
                        provider.setCellValue(producto.getProvider());
                    }
                }
                if (producto.getBestSellerCount() != null) {
                    XSSFCell bestSeller = row.createCell(4);
                    bestSeller.setCellValue(producto.getBestSellerCount());
                    XSSFCell ingresosGenerados = row.createCell(5);
                    ingresosGenerados.setCellValue(producto.getBestSellerCount() * producto.getPrecio());
                }

                index++;
            }
        }


        reportPath = "C:\\Users\\" + GLOBALUSER + "\\Downloads\\" + fileName;
        createFile(reportPath, libro);
    }

    public static void exportProvidersToExcel(List<Proveedor> proveedores, String[] header, FileModel fileConfig){
        String fileName = fileConfig.getFileName() + "Report" + now + ".xlsx";
        String hoja = fileConfig.getSheetName();
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);
        setBoldStyle(libro);
        int index = 0;

        for (int i = 0; i <= proveedores.size(); i++) {
            XSSFRow row = hoja1.createRow(i); //Se crea la fila
            for (int j = 0; j < header.length; j++) {
                if (i == 0) { //para la cabecera
                    XSSFCell cell = row.createCell(j); //Se crean las celdas para la cabecera
                    cell.setCellValue(header[j]); //Se añade el contenido
                }
            }
            if (i != 0 && index < proveedores.size()) {
                Proveedor proveedor = proveedores.get(index);
                XSSFCell id = row.createCell(0);
                id.setCellValue(proveedor.getIdProveedor());
                XSSFCell nombre = row.createCell(1);
                nombre.setCellValue(proveedor.getNombre());
                XSSFCell descripcion = row.createCell(2);
                descripcion.setCellValue(proveedor.getDescripcion());
                index++;
            }
        }
        reportPath = "C:\\Users\\" + GLOBALUSER + "\\Downloads\\" + fileName;
        createFile(reportPath, libro);
    }

}