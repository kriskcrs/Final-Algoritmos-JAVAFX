package database.dao;

import database.Connect;
import database.models.Producto;
import database.models.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendedorDao {

    public static void createSellerDB(Vendedor vendedor) {
        Connect connect = new Connect();
        PreparedStatement ps = null;
        try (Connection connection = connect.getConnection()) {
            String sql = "INSERT INTO public.vendedor(\"idVendedor\", nombre, apellido, dpi, direccion, telefono)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, vendedor.getIdVendedor());
            ps.setString(2, vendedor.getNombre());
            ps.setString(3, vendedor.getApellido());
            ps.setString(4, vendedor.getDpi());
            ps.setString(5, vendedor.getDireccion());
            ps.setString(6, vendedor.getTelefono());
            ps.executeUpdate();
            System.out.println("Vendedor creado");
        } catch (SQLException e) {
            System.out.println("El vendedor no se pudo crear\n" + e);
        }
        connect.closeConnection();

    }

    public static List viewSellerDB() {
        Connect connect = new Connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Vendedor> vendedores = new ArrayList<>();
        Producto producto = new Producto();
        Vendedor vendedor = new Vendedor();

        try(Connection connection = connect.getConnection()){
            String sql = "SELECT * FROM public.vendedor";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                vendedor.setIdVendedor(rs.getInt(1));
                vendedor.setNombre(rs.getString(2));
                vendedor.setApellido(rs.getString(3));
                vendedor.setDpi(rs.getString(4));
                vendedor.setDireccion(rs.getString(5));
                vendedor.setTelefono(rs.getString(6));
                vendedores.add(vendedor);
            }
        } catch (SQLException e) {
            System.out.println("No se traer el vendedor" + e);
        }
        connect.closeConnection();
        return vendedores ;
    }

    public static List viewSellerByID(String dpi) {
        Connect connect = new Connect();
        PreparedStatement ps;
        ResultSet rs = null;
        List<Vendedor> vendedores = new ArrayList<>();
        Vendedor vendedor = new Vendedor();

        try (Connection connection = connect.getConnection()) {
            String sql = "SELECT \"idVendedor\", nombre, apellido, dpi, direccion, telefono\n" +
                    "\tFROM public.vendedor where dpi=?;";
            ps = connection.prepareStatement(sql);
            ps.setString(1, dpi);
            rs = ps.executeQuery();

            while (rs.next()) {
                vendedor.setIdVendedor(rs.getInt(1));
                vendedor.setNombre(rs.getString(2));
                vendedor.setApellido(rs.getString(3));
                vendedor.setDpi(rs.getString(4));
                vendedor.setDireccion(rs.getString(5));
                vendedor.setTelefono(rs.getString(6));
                vendedores.add(vendedor);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer al vendedor\n" + e);
        }
        connect.closeConnection();
        return vendedores;
    }

    public static void deleteSellerDB(String dpi) {
        Connect connect = new Connect();
        PreparedStatement ps;
        try (Connection connection = connect.getConnection()) {
            String sql = "delete from \"vendedor\" where dpi = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, dpi);
            ps.executeUpdate();
            System.out.println("Vendedor eliminado");
        } catch (SQLException e) {
            System.out.println("El vendedor no se pudo borrar" + e);
        }
        connect.closeConnection();
    }

    public static void updateSeller(Vendedor vendedor) {
        Connect connect = new Connect();
        PreparedStatement ps;
        try (Connection connection = connect.getConnection()) {
            String sql = "update \"vendedor\" set \"nombre\"=?, \"apellido\"=?, \"direccion\"=?, \"telefono\"=?, \"dpi\"=?" +
                    "where \"idVendedor\"=?;";
            ps = connection.prepareStatement(sql);
            ps.setString(1, vendedor.getNombre());
            ps.setString(2, vendedor.getApellido());
            ps.setString(3, vendedor.getDireccion());
            ps.setString(4, vendedor.getTelefono());
            ps.setString(5, vendedor.getDpi());
            ps.setInt(6, vendedor.getIdVendedor());
            ps.executeUpdate();
            System.out.println("Vendedor actualizado");
        } catch (SQLException e) {
            System.out.println("El vendedor no se pudo actualizar" + e);
        }
        connect.closeConnection();
    }

}