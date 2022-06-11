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

//JDK / STL
import java.util.ArrayList;
/**
 *
 * @author ale71
 */
public class PrestamoDAO {
    public boolean registrarPrestamo(Prestamo prestamoNuevo) throws SQLException{
        String consulta = 
        " INSERT INTO prestamo "
        +"(idPrestamo,fehaPrestamo,fechaDevolucion,idUsuario,idLibro) "
        +"values (?,?,?,?,?);";
        boolean verificacionRegistro = false;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1,prestamoNuevo.getIdPrestamo());
            sentencia.setDate(2,java.sql.Date.valueOf(prestamoNuevo.getFechaPrestamo()));
            sentencia.setDate(3,java.sql.Date.valueOf(prestamoNuevo.getFechaDevolucion()));
            sentencia.setString(4,prestamoNuevo.getIdUsuario());
            sentencia.setInt(5,prestamoNuevo.getIdLibro());
            verificacionRegistro = sentencia.executeUpdate()!=0;
            //System.out.println("Se establecio la conexion");
        }finally{
            db.desconectar();
        }
        return verificacionRegistro;
    }
    
    public int getLastIdPrestamo() throws SQLException
    {
        String consulta = "select max(idPrestamo) as lastP from prestamo";
        int lastId;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            //System.out.println("En el getLastid DAO " + resultado.next());
            if(!resultado.next())
                lastId = 1;
            else lastId = resultado.getInt("lastP") + 1;
            
        }finally{
            db.desconectar();
        }
        return lastId;
    }
    
    
    public ArrayList<Integer> getIdLibrosEnPrestamo() throws SQLException
    {
        ArrayList<Integer> listaId = new ArrayList<Integer>();
        String consulta  = "SELECT idLibro from prestamo";
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion())
        {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next())
            {
                listaId.add(resultado.getInt("idLibro"));
            }
        }
        finally
        {
            db.desconectar();
        }
        return listaId;
    }
   
}
