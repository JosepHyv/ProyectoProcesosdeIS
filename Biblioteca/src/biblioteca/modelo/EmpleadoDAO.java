/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.modelo;

import biblioteca.dataaccess.DataBaseConnection;
import biblioteca.pojo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ale71
 */
public class EmpleadoDAO {
    public boolean registrarEmpleado(Empleado empleadoNuevo) throws SQLException{
        String query = 
        "INSERT INTO empleados values (?,?,?,?,?,?,?,?);";
        boolean verificacionRegistro = false;
        DataBaseConnection db = new DataBaseConnection();
        try(Connection connection = db.getConexion()){
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setInt(1,empleadoNuevo.getNumEmpleado());
            sentencia.setDate(2,java.sql.Date.valueOf(empleadoNuevo.getFechaNacimiento()));
            sentencia.setString(3,empleadoNuevo.getNss());
            sentencia.setString(4,empleadoNuevo.getCurp());
            sentencia.setString(5,empleadoNuevo.getTelefono());
            sentencia.setString(6,empleadoNuevo.getContrasenia());
            sentencia.setString(7,empleadoNuevo.getTipoContratacion());
            sentencia.setString(8,empleadoNuevo.getIdUsuario());
            verificacionRegistro = sentencia.executeUpdate()!=0;
        }finally{
            db.desconectar();
        }
        return verificacionRegistro;
    }

}
