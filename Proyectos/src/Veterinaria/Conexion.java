package Veterinaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/veterinaria";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    /**
     * Devuelve una nueva conexión a la base de datos.
     * Cada llamada crea una conexión independiente.
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Devuelve una nueva conexión usando usuario y contraseña personalizados.
     */
    public static Connection getConexionUsuario(String usuario, String contraseña) throws SQLException {
        return DriverManager.getConnection(URL, usuario, contraseña);
    }
}//javax.swing.JOptionPane.showMessageDialog(this,"No hay ningún producto en la tienda con los parámetros que busca.","Producto no existente",javax.swing.JOptionPane.INFORMATION_MESSAGE);