/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vidrieria
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/vidrieria";
    //Generalmente este es el usuario, pero si no cambienlo
    private static final String USER = "root";
    //ponga su contraseña de MYSQL
    private static final String PASS = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
