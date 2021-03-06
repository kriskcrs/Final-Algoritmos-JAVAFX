package UI.controllers;

import UI.utils.CreateExcelFile;
import UI.utils.FileModel;
import UI.utils.HeadersModels;
import database.models.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class ReportController {

    @FXML
    private TextField nitClienteInput;

    @FXML
    private TextField nombreClienteInput;

    @FXML
    private TextField idProductoInput;

    @FXML
    private TextField nombreProductoInput;

    @FXML
    private TextField idProveedorInput;

    @FXML
    private TextField nombreProveedorInput;


    /*
     * Clientes
     * */

    /**
     * Descripcion: Este metodo obtiene toda la informacion de los clientes y ejecuta internamente otro metodo de
     * la clase createExcelFile llamado exportClientsToExcel para crear un archivo con extension xlsx
     * */
    public void exportClients() {
        List<Cliente> clientes = database.service.ClienteService.listClient();
        String[] headers = HeadersModels.clientHeaders;
        FileModel fileModel = new FileModel("Clientes", "Clientes");
        try {
            UI.utils.CreateExcelFile.exportClientsToExcel(clientes, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de los clientes y ejecuta internamente otro metodo de
     * la clase createExcelFile llamado exportDebtorsCustomersToExcel para crear un archivo con extension xlsx
     * */
    public void exportDebtors() {
        List<SaldoPendiente> saldosPendientes = database.service.SaldoPendienteService.viewAllDobter();
        String[] headers = HeadersModels.saldoPendienteHeaders;
        FileModel fileModel = new FileModel("Deudores", "Deudores");
        try {
            UI.utils.CreateExcelFile.exportDebtorCustomersToExcel(saldosPendientes, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de un cliente especificado a traves de un textfield
     * y ejecuta internamente otro metodo de la clase createExcelFile llamado exportClientsToExcel para crear un
     * archivo con extension xlsx
     * */
    public void exportClient() {
        String nit = nitClienteInput.getText();
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = database.service.ClienteService.listClientNit(nit);
        clientes.add(cliente);
        String[] headers = HeadersModels.clientHeaders;
        FileModel fileModel = new FileModel("Cliente", "individualCliente");
        try {
            UI.utils.CreateExcelFile.exportClientsToExcel(clientes, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de un cliente especificado a traves de un textfield
     * y ejecuta internamente otro metodo de la clase createExcelFile llamado exportSalesByClientToExcel para crear un
     * archivo con extension xlsx que contiene la facturas del cliente
     * */
    public void exportSalesByClient(){
        String nit = nitClienteInput.getText();
        List<Venta> ventas = database.service.VentaService.viewBillClient(nit);
        String[] headers = HeadersModels.ventaHeaders;
        FileModel fileModel = new FileModel("FacturasCliente", "Facturas");
        try {
            UI.utils.CreateExcelFile.exportSalesByClientToExcel(ventas, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de los clientes y ejecuta internamente otro metodo de
     * la clase createExcelFile llamado exportDebtorsCustomersToExcel para crear un archivo con extension xlsx
     * que contiene las cuentas que el cliente debe
     * */
    public void exportAccountsReceivable(){
        String nit = nitClienteInput.getText();
        List<SaldoPendiente> saldosPendientes = database.service.SaldoPendienteService.viewDobter(nit);
        String[] headers = HeadersModels.saldoPendienteHeaders;
        FileModel fileModel = new FileModel("clienteSaldosPendientes", "deudas");
        try {
            UI.utils.CreateExcelFile.exportDebtorCustomersToExcel(saldosPendientes, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /*
     * Productos
     * */

    /**
     * Descripcion: Este metodo obtiene toda la informacion de los productos y ejecuta internamente otro metodo de
     * la clase createExcelFile llamado exportProductsToExcel para crear un archivo con extension xlsx
     * */
    public void exportProducts() {
        List<Producto> productos = database.service.ProductoService.listProduct();
        String[] headers = HeadersModels.productoHeaders;
        FileModel fileModel = new FileModel("Productos", "Productos");
        try {
            UI.utils.CreateExcelFile.exportProductsToExcel(productos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de los productos y ejecuta internamente otro metodo de
     * la clase createExcelFile llamado exportProductsToExcel para crear un archivo con extension xlsx que
     * contiene los productos de los cuales no hay existencias en bodega
     * */
    public void exportProductsOutOfStock() {
        List<Producto> productos = database.service.ProductoService.stockZero();
        String[] headers = HeadersModels.productoHeaders;
        FileModel fileModel = new FileModel("ProductosSinExistencias", "Sin existencias");
        try {
            UI.utils.CreateExcelFile.exportProductsToExcel(productos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de los productos y ejecuta internamente otro metodo de
     * la clase createExcelFile llamado exportProductsToExcel para crear un archivo con extension xlsx que
     * contiene los productos mas vendidos
     * */
    public void exportBestSellerProducts() {
        List<Producto> productos = database.service.ProductoService.bestSellers();
        String[] headers = HeadersModels.productoHeaders;
        FileModel fileModel = new FileModel("ProductosMejoresVendidos", "Mejores vendidos");
        try {
            UI.utils.CreateExcelFile.exportProductsToExcel(productos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de los productos y ejecuta internamente otro metodo de
     * la clase createExcelFile llamado exportProductsToExcel para crear un archivo con extension xlsx que
     * contiene los productos mas caros
     * */
    public void exportMoreExpensiveProducts() {
        List<Producto> productos = database.service.ProductoService.highestPrice();
        String[] headers = HeadersModels.productoHeaders;
        FileModel fileModel = new FileModel("ProductosMasCaros", "Prod. mas caros");
        try {
            UI.utils.CreateExcelFile.exportProductsToExcel(productos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de los productos y ejecuta internamente otro metodo de
     * la clase createExcelFile llamado exportProductsToExcel para crear un archivo con extension xlsx que
     * contiene los productos mas baratos
     * */
    public void exportCheaperProducts() {
        List<Producto> productos = database.service.ProductoService.lowestPrice();
        String[] headers = HeadersModels.productoHeaders;
        FileModel fileModel = new FileModel("ProductosMasBaratos", "Prod. mas baratos");
        try {
            UI.utils.CreateExcelFile.exportProductsToExcel(productos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de un producto especificado a traves de un textfield
     * y ejecuta internamente otro metodo de la clase createExcelFile llamado exportProductsToExcel para crear un
     * archivo con extension xlsx
     * */
    public void exportProduct(){
        int id = Integer.parseInt(idProductoInput.getText());
        List<Producto> productos = new ArrayList<>();
        Producto producto = database.service.ProductoService.listProductByID(id);
        productos.add(producto);
        String[] headers = HeadersModels.productoHeaders;
        FileModel fileModel = new FileModel("Producto", "individual producto");
        try {
            UI.utils.CreateExcelFile.exportProductsToExcel(productos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de los productos vendidos y ejecuta internamente
     * otro metodo de la clase createExcelFile llamado exportSalesByProductToExcel para crear un archivo con extension
     * xlsx
     * */
    public void exportAllSalesProduct(){
        List<VentaProducto> ventaProductos = database.service.VentaProductoService.viewAllProductSales();
        String[] headers = HeadersModels.ventaProductosHeaders;
        FileModel fileModel = new FileModel("ProductosVendidos", "ProductosVendidos");
        try {
            UI.utils.CreateExcelFile.exportSalesByProductToExcel(ventaProductos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de los productos y ejecuta internamente otro metodo de
     * la clase createExcelFile llamado exportProvidersToExcel para crear un archivo con extension xlsx
     * */
    public void exportProviders() {
        List<Proveedor> proveedores = database.service.ProveedorService.listProvider();
        String[] headers = HeadersModels.proveedorHeaders;
        FileModel fileModel = new FileModel("Proveedores", "listado proveedores");
        try {
            UI.utils.CreateExcelFile.exportProvidersToExcel(proveedores, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion de un proveeodr especificado a traves de un textfield
     * y ejecuta internamente otro metodo de la clase createExcelFile llamado exportProvidersToExcel para crear un
     * archivo con extension xlsx
     * */
    public void exportProvider() {
        int id = Integer.parseInt(idProveedorInput.getText());
        List<Proveedor> proveedores = new ArrayList<>();
        Proveedor proveedor = database.service.ProveedorService.listProviderByID(id);
        proveedores.add(proveedor);
        String[] headers = HeadersModels.proveedorHeaders;
        FileModel fileModel = new FileModel("Proveedores", "individual proveedores");
        try {
            UI.utils.CreateExcelFile.exportProvidersToExcel(proveedores, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /*
    * Utils
    * */

    /**
     * Descripcion: Metodo que lanza una alerta que indica que la descarga del reporte fue exitosa e indica
     * la ruta de descarga
     * */
    public void alertInfoSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("Archivo creado correctamente en la ruta \n" +
                CreateExcelFile.reportPath);
        alert.showAndWait();
    }

    /**
     * Descripcion: Metodo que que recibe como parametro un error y lanza una alerta con el error especifico
     * al descargar un reporte
     * @param error del tipo Error
     * */
    public void alertError(Error error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(error + "ocurrio un error al " +
                "crear el archivo .xls");
        alert.showAndWait();
    }

    /**
     * Descripcion: metodo que busca un cliente cuando se presiona enter en un textField de la UI utilizado
     * para introducir el nit del cliente
     * */
    public void searchClient(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String nit = nitClienteInput.getText();
            Cliente cliente = database.service.ClienteService.listClientNit(nit);
            nombreClienteInput.setText(cliente.getNombre() + " " + cliente.getApellido());
        }
    }

    /**
     * Descripcion: metodo que busca un producto cuando se presiona enter en un textField de la UI utilizado
     * para introducir el id del Producto
     * */
    public void searchProduct(KeyEvent keyEvent){
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            int id = Integer.parseInt(idProductoInput.getText());
            Producto producto = database.service.ProductoService.listProductByID(id);
            nombreProductoInput.setText(producto.getNombre());
        }
    }

    /**
     * Descripcion: metodo que busca un proveedor cuando se presiona enter en un textField de la UI utilizado
     * para introducir el id del proveedor
     * */
    public void searchProvider(KeyEvent keyEvent){
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            int id = Integer.parseInt(idProveedorInput.getText());
            Proveedor proveedor = database.service.ProveedorService.listProviderByID(id);
            nombreProveedorInput.setText(proveedor.getNombre());
        }
    }


}
