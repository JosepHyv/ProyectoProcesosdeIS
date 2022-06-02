/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.modelo;

import biblioteca.dataaccess.DataBaseConnection;
import biblioteca.encriptacion.EncriptadorSHA512;
import biblioteca.pojo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ale71
 */
public class EmpleadoDAO {
    public boolean registrarEmpleado(Empleado empleadoNuevo) throws SQLException{
        String consulta = 
        " INSERT INTO empleados "
        +"(numEmpleados,fechaNacimiento,nss,curp,rfc,telefono,contrasenia,tipoContratacion,idUsuario) "
        +"values (?,?,?,?,?,?,?,?,?);";
        boolean verificacionRegistro = false;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1,empleadoNuevo.getNumEmpleado());
            sentencia.setDate(2,java.sql.Date.valueOf(empleadoNuevo.getFechaNacimiento()));
            sentencia.setString(3,empleadoNuevo.getNss());
            sentencia.setString(4,empleadoNuevo.getCurp());
            sentencia.setString(5,empleadoNuevo.getRfc());
            sentencia.setString(6,empleadoNuevo.getTelefonoEmpleado());
            EncriptadorSHA512 encriptador = new EncriptadorSHA512();
            sentencia.setString(7,encriptador.encriptarCadena(empleadoNuevo.getContrasenia()));
            sentencia.setString(8,empleadoNuevo.getTipoContratacion());
            sentencia.setString(9,empleadoNuevo.getUsuario().getIdUsuario());
            verificacionRegistro = sentencia.executeUpdate()!=0;
        }finally{
            db.desconectar();
        }
        return verificacionRegistro;
    }
    
    public boolean encontrarEmpleadoRegistradoPorNumEmpleado(int numEmpleado) throws SQLException{
        String consulta = 
        "SELECT * FROM empleados WHERE numEmpleados = ?;";
        boolean verificacionExistencia = false;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, numEmpleado);
            ResultSet resultado = sentencia.executeQuery();
            verificacionExistencia = resultado.next();
        }finally{
            db.desconectar();
        }
        return verificacionExistencia;
    }
    
    public boolean encontrarEmpleadoRegistradoPorCURP(String curp) throws SQLException{
        String consulta = 
        "SELECT * FROM empleados WHERE curp = ?;";
        boolean verificacionExistencia = false;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, curp);
            ResultSet resultado = sentencia.executeQuery();
            verificacionExistencia = resultado.next();
        }finally{
            db.desconectar();
        }
        return verificacionExistencia;
    }
    
    public boolean encontrarEmpleadoRegistradoPorNSS(String nss) throws SQLException{
        String consulta = 
        "SELECT * FROM empleados WHERE nss = ?;";
        boolean verificacionExistencia = false;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nss);
            ResultSet resultado = sentencia.executeQuery();
            verificacionExistencia = resultado.next();
        }finally{
            db.desconectar();
        }
        return verificacionExistencia;
    }
    
    public boolean encontrarEmpleadoRegistradoPorRFC(String rfc) throws SQLException{
        String consulta = 
        "SELECT * FROM empleados WHERE rfc = ?;";
        boolean verificacionExistencia = false;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, rfc);
            ResultSet resultado = sentencia.executeQuery();
            verificacionExistencia = resultado.next();
        }finally{
            db.desconectar();
        }
        return verificacionExistencia;
    }
    
    public int obtenerNumEmpleadoSiguiente() throws SQLException{
        String consulta = 
        "SELECT MAX(numEmpleados) FROM empleados;";
        int numEmpleadoSiguiente = 0;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()){
                numEmpleadoSiguiente = Integer.parseInt(resultado.getString("MAX(numEmpleados)"));
            }
        }finally{
            db.desconectar();
        }
        return numEmpleadoSiguiente+1;
    }
    
    public boolean encontrarEmpleadoPorNumEmpleadoYContrasenia
    (int numEmpleado, String contraseniaSinEncriptar) throws SQLException{
        String consulta = 
        "SELECT * FROM empleados WHERE numEmpleados = ? AND contrasenia = ?;";
        boolean verificacionExistencia = false;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, numEmpleado);
            EncriptadorSHA512 encriptador = new EncriptadorSHA512();
            sentencia.setString(2, encriptador.encriptarCadena(contraseniaSinEncriptar));
            ResultSet resultado = sentencia.executeQuery();
            verificacionExistencia = resultado.next();
        }finally{
            db.desconectar();
        }
        return verificacionExistencia;
    }
    public Empleado obtenerEmpleadoPorNumEmpleadoYContrasenia
    (int numEmpleado, String contraseniaSinEncriptar) throws SQLException{
        String consulta = 
        "SELECT * FROM empleados WHERE numEmpleados = ? AND contrasenia = ?;";
        Empleado empleadoEncontrado = new Empleado();
        DataBaseConnection db = new DataBaseConnection();
        try(Connection conexion = db.getConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            EncriptadorSHA512 encriptador = new EncriptadorSHA512();
            sentencia.setInt(1, numEmpleado);
            sentencia.setString(2, encriptador.encriptarCadena(contraseniaSinEncriptar));
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()){
                empleadoEncontrado.setNumEmpleado(numEmpleado);
                empleadoEncontrado.setNss(resultado.getString("nss"));
                empleadoEncontrado.setCurp(resultado.getString("curp"));
                empleadoEncontrado.setContrasenia(resultado.getString("contrasenia"));
                empleadoEncontrado.setTipoContratacion(resultado.getString("tipoContratacion"));
            }
        }finally{
            db.desconectar();
        }
        return empleadoEncontrado;
    }
}
