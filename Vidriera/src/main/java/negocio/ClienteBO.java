/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dao.ClienteDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import modelo.Cliente;
import utils.Conexion;

public class ClienteBO {

    private final ClienteDAO clienteDAO;
    private final Connection conexion;

    public ClienteBO() {
        try {
            this.conexion = Conexion.getConnection();
            this.clienteDAO = new ClienteDAO(conexion);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("No se pudo inicializar la conexión a la base de datos.");
        }
    }

    public boolean crearCliente(Cliente c) {
        // Validaciones básicas
        if (c.getNombre() == null || c.getNombre().trim().isEmpty()) {
            System.out.println("El nombre es obligatorio");
            return false;
        }
        return clienteDAO.crearCliente(c);
    }

    public List<Cliente> obtenerClientes() {
        return clienteDAO.obtenerTodos();
    }

    public Cliente obtenerClientePorId(int id) {
        return clienteDAO.obtenerPorId(id);
    }

    public Cliente obtenerPornombre(String nombre) {
        return clienteDAO.obtenerPorNombre(nombre);
    }

    public boolean actualizarCliente(Cliente c) {
        if (c.getIdCliente() <= 0) {
            System.out.println("ID de cliente inválido");
            return false;
        }
        return clienteDAO.actualizarCliente(c);
    }

    public boolean eliminarCliente(int idCliente) {
        return clienteDAO.eliminarCliente(idCliente);
    }

    public boolean existeCliente(int idCliente) {
        return clienteDAO.existeCliente(idCliente);
    }
    
}
