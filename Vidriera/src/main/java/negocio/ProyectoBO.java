/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dao.ProyectoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import modelo.Proyecto;
import utils.Conexion;

public class ProyectoBO {

    private final ProyectoDAO proyectoDAO;
    private final Connection conexion;

    public ProyectoBO() {
        try {
            this.conexion = Conexion.getConnection();
            this.proyectoDAO = new ProyectoDAO(conexion);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("No se pudo inicializar la conexión a la base de datos.");
        }
    }

    public boolean crearProyecto(Proyecto p) {
        if (p.getCliente() == null || p.getCliente().getIdCliente() <= 0) {
            return false;
        }
        if (p.getFechaInicio() == null || p.getFechaEntregaEstimada() == null) {
            return false;
        }
        return proyectoDAO.crearProyecto(p);
    }

    public List<Proyecto> obtenerProyectos() {
        return proyectoDAO.obtenerTodos();
    }

    public Proyecto obtenerProyectoPorId(int id) {
        return proyectoDAO.obtenerPorId(id);
    }

    public boolean actualizarProyecto(Proyecto p) {
        if (p.getIdProyecto() <= 0) {
            return false;
        }
        return proyectoDAO.actualizarProyecto(p);
    }

    public boolean eliminarProyecto(int id) {
        return proyectoDAO.eliminarProyecto(id);
    }
}
