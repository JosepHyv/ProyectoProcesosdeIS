/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.modelo;

//pollos 
import biblioteca.pojo.Libro;

import biblioteca.dataaccess.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JosepHy
 */
public class LibroDAO {
    public boolean registrarLibro(Libro libroNuevo) throws SQLException
    {
        /// IMPLEMENTAR PARA CU REGISTRAR LIBRO (HECTOR)
        String consulta = "";
        boolean verificacion = false;
        
        return verificacion;
    }
    
    public boolean encontrarLibroPorNombre(String nombreLibro) throws SQLException
    {
        boolean existe = false;
        String consulta = "SELECT titulo FROM Libros where titulo = ?";
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion())
        {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombreLibro);
            ResultSet resultado = sentencia.executeQuery();
            existe = resultado.next();
        }
        finally
        {
            db.desconectar();
        }
        
        return existe;
    }
    
    public Libro getLibroPorNombre(String nombreLibro) throws SQLException
    {
        Libro libroEncontrado = new Libro();
        String consulta = "SELECT * " +
                "FROM Libros " +
                "WHERE titulo = ?";
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion())
        {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nombreLibro);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next())
            {
                libroEncontrado.setIdLibro(resultado.getInt("idLibro"));
                libroEncontrado.setAutor(resultado.getString("autor"));
                libroEncontrado.setTitulo(resultado.getString("titulo"));
                libroEncontrado.setEditorial(resultado.getString("editorial"));
                libroEncontrado.setEdicion(resultado.getString("edicion"));
            }
        }
        finally
        {
            db.desconectar();
        }
        return libroEncontrado;
    }
}
