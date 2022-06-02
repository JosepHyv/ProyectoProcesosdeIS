/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.modelo;

import biblioteca.dataaccess.DataBaseConnection;
import biblioteca.encriptacion.EncriptadorSHA512;
import biblioteca.pojo.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ale71
 */
public class PrestamoDAO {
    public boolean registrarPrestamo(Prestamo prestamoNuevo) throws SQLException{
        String consulta = 
        " INSERT INTO Prestamo "
        +"(idPrestamo,fechaPrestamo,fechaDevolucion,idUsuario,idLibro) "
        +"values (?,?,?,?,?);";
        boolean verificacionRegistro = false;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1,prestamoNuevo.getIdPrestamo());
            sentencia.setDate(2,java.sql.Date.valueOf(prestamoNuevo.getFechaPrestamo()));
            sentencia.setDate(3,java.sql.Date.valueOf(prestamoNuevo.getFechaDevolucion()));
            sentencia.setString(4,prestamoNuevo.getIdUsuario());
            sentencia.setString(5,prestamoNuevo.getIdLibro());
            verificacionRegistro = sentencia.executeUpdate()!=0;
        }finally{
            db.desconectar();
        }
        return verificacionRegistro;
    }
    
   
}
