
import java.sql.Connection;
import java.sql.SQLException;
import utils.Conexion;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Vidrieria
 */
public class TestConexion {
    public static void main(String[] args) {
        try {
            Connection con = Conexion.getConnection();
            System.out.println("¡Conexión exitosa!");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
