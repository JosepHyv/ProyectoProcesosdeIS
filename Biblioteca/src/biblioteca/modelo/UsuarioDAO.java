/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.modelo;

import biblioteca.dataaccess.DataBaseConnection;
import biblioteca.pojo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ale71
 */
public class UsuarioDAO {
    
    public boolean registrarUsuario(Usuario usuarioNuevo) throws SQLException{
        String consulta = 
        "INSERT INTO usuarios "+
            "(idUsuario,nombres,apellidoPaterno,apellidoMaterno,calle,"+
            "numero,colonia,municipio,email,telefono,tipoUsuario)"+
        "values (?,?,?,?,?,?,?,?,?,?,?);";
        boolean verificacionRegistro = false;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,usuarioNuevo.getIdUsuario());
            sentencia.setString(2,usuarioNuevo.getNombre());
            sentencia.setString(3,usuarioNuevo.getApellidoPaterno());
            sentencia.setString(4,usuarioNuevo.getApellidoMaterno());
            sentencia.setString(5,usuarioNuevo.getCalle());
            sentencia.setString(6,usuarioNuevo.getNumero());
            sentencia.setString(7,usuarioNuevo.getColonia());
            sentencia.setString(8,usuarioNuevo.getMunicipio());
            sentencia.setString(9,usuarioNuevo.getEmail());
            sentencia.setString(10,usuarioNuevo.getTelefono());
            sentencia.setString(11,usuarioNuevo.getTipoUsuario());
            verificacionRegistro = sentencia.executeUpdate()!=0;
        }finally{
            db.desconectar();
        }
        return verificacionRegistro;
    }
    
    public boolean encontrarUsuarioPorIdUsuario (String idUsuario) throws SQLException{
        String consulta = 
        "SELECT idUsuario "+
        "FROM   usuarios "+
        "WHERE  idUsuario = ?";
        boolean verificacionExistencia = false;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, idUsuario);
            ResultSet resultado = sentencia.executeQuery();
            verificacionExistencia = resultado.next();
        }finally{
            db.desconectar();
        }
        return verificacionExistencia;
    }
    
    public Usuario getUsuarioPorId(String idUsuario) throws SQLException
    {
        Usuario usuarioEncontrado = new Usuario();
        String consulta = "SELECT * " + 
        "FROM usuarios "+
        "WHERE idUsuario = ?";
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion())
        {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,idUsuario);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next())
            {
                usuarioEncontrado.setIdUsuario(idUsuario);
                usuarioEncontrado.setNombre(resultado.getString("nombres"));
                usuarioEncontrado.setCalle(resultado.getString("calle")); 
                usuarioEncontrado.setNumero(resultado.getString("numero"));
                usuarioEncontrado.setColonia(resultado.getString("colonia"));
                usuarioEncontrado.setMunicipio(resultado.getString("municipio"));
                usuarioEncontrado.setEmail(resultado.getString("email"));
                usuarioEncontrado.setTelefono(resultado.getString("telefono"));
                usuarioEncontrado.setTipoUsuario(resultado.getString("tipoUsuario"));
            }   
        }
        finally
        {
            db.desconectar();
        }
        
        return usuarioEncontrado;
    }
    
    
}
