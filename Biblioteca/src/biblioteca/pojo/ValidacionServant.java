/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.pojo;

import biblioteca.modelo.EmpleadoDAO;
import biblioteca.modelo.UsuarioDAO;
import biblioteca.pojo.constantes.ConstanteCaracteresLegales;
import java.sql.SQLException;

/**
 *
 * @author ale71
 */
public class ValidacionServant {    
    public void validarUsuario(Validable objeto) throws SQLException, IllegalArgumentException{
        if (objeto!= null && objeto instanceof Usuario){
            Usuario usuarioAValidar = (Usuario) objeto;
            validarCaracteresEnCamposDelUsuario(usuarioAValidar);
            validarIdUsuarioNuevo(usuarioAValidar.getIdUsuario());
        }
    }
    
    public void validarEmpleado(Validable objeto) throws SQLException, IllegalArgumentException{
        if (objeto!= null && objeto instanceof Empleado){
            Empleado empleadoAValidar = (Empleado) objeto;
            validarCaracteresEnCamposDelEmpleado(empleadoAValidar);
            validarNumEmpleadoNuevo(empleadoAValidar.getNumEmpleado());
            validarCURPNuevo(empleadoAValidar.getCurp());
            validarNSSNuevo(empleadoAValidar.getNss());
            validarRFCNuevo(empleadoAValidar.getRfc());
        }
    }
    
    public void validarCaracteresEnCamposDelEmpleado(Empleado empleado){
        validarCaracteres(Integer.toString(empleado.getNumEmpleado()), "Número de empleado", 
                ConstanteCaracteresLegales.NUMERICOS.getCaracteres());
        validarCaracteres(empleado.getNss(), "NSS", 
                ConstanteCaracteresLegales.NUMERICOS.getCaracteres());
        validarCaracteres(empleado.getCurp(), "CURP", 
                ConstanteCaracteresLegales.ALFANUMERICOS_VALIDACION.getCaracteres());
        validarCaracteres(empleado.getRfc(), "RFC", 
                ConstanteCaracteresLegales.ALFANUMERICOS_VALIDACION.getCaracteres());
        validarCaracteres(empleado.getTelefonoEmpleado(), "Teléfono 2", 
                ConstanteCaracteresLegales.NUMERICOS.getCaracteres());
        validarCaracteres(empleado.getEmail(),"Email",
                ConstanteCaracteresLegales.EMAIL.getCaracteres());
    }
    
    public void validarCaracteresEnCamposDelUsuario(Usuario usuario){
        validarCaracteres(usuario.getIdUsuario(), "IdUsuario", 
                ConstanteCaracteresLegales.ALFANUMERICOS_VALIDACION.getCaracteres());
        validarCaracteres(usuario.getNombre(), "Nombres", 
                ConstanteCaracteresLegales.ALFABETICOS_VALIDACION.getCaracteres());
        validarCaracteres(usuario.getApellidoPaterno(), "Apellido Paterno", 
                ConstanteCaracteresLegales.ALFABETICOS_VALIDACION.getCaracteres());
        validarCaracteres(usuario.getApellidoMaterno(), "Apellido Materno", 
                ConstanteCaracteresLegales.ALFABETICOS_VALIDACION.getCaracteres());
        validarCaracteres(usuario.getCalle(), "Calle", 
                ConstanteCaracteresLegales.ALFANUMERICOS_VALIDACION.getCaracteres());
        validarCaracteres(usuario.getNumero(), "Número", 
                ConstanteCaracteresLegales.ALFANUMERICOS_VALIDACION.getCaracteres());
        validarCaracteres(usuario.getColonia(), "Colonia", 
                ConstanteCaracteresLegales.ALFANUMERICOS_VALIDACION.getCaracteres());
        validarCaracteres(usuario.getMunicipio(), "Municipio", 
                ConstanteCaracteresLegales.ALFANUMERICOS_VALIDACION.getCaracteres());
        validarCaracteres(usuario.getTelefono(), "Telefono", 
                ConstanteCaracteresLegales.NUMERICOS.getCaracteres());
    }
    
    public void validarNumEmpleadoNuevo(int numEmpleado) throws SQLException, IllegalArgumentException{
        final boolean EL_NUM_EMPLEADO_EXISTE_EN_BASE_DE_DATOS = 
            new EmpleadoDAO().encontrarEmpleadoRegistradoPorNumEmpleado(numEmpleado);
        if (EL_NUM_EMPLEADO_EXISTE_EN_BASE_DE_DATOS == true){
            throw new IllegalArgumentException("El identificador del empleado que ha ingresado "
                + "ya existe en el sistema. Por favor, verifíque la información e inténtelo de nuevo.");
        }
    }
    
    public void validarCURPNuevo(String curp) throws SQLException, IllegalArgumentException{
        final boolean EL_CURP_EXISTE_EN_BASE_DE_DATOS = 
            new EmpleadoDAO().encontrarEmpleadoRegistradoPorCURP(curp);
        if (EL_CURP_EXISTE_EN_BASE_DE_DATOS == true){
            throw new IllegalArgumentException("El CURP del empleado que ha ingresado "
                + "ya existe en el sistema. Por favor, verifíque la información e inténtelo de nuevo.");
        }
    }
    
    public void validarNSSNuevo(String nss) throws SQLException, IllegalArgumentException{
        final boolean EL_NSS_EXISTE_EN_BASE_DE_DATOS = 
            new EmpleadoDAO().encontrarEmpleadoRegistradoPorNSS(nss);
        if (EL_NSS_EXISTE_EN_BASE_DE_DATOS == true){
            throw new IllegalArgumentException("El NSS del empleado que ha ingresado "
                + "ya existe en el sistema. Por favor, verifíque la información e inténtelo de nuevo.");
        }
    }
    
    public void validarRFCNuevo(String rfc) throws SQLException, IllegalArgumentException{
        boolean EL_RFC_EXISTE_EN_BASE_DE_DATOS = 
            new EmpleadoDAO().encontrarEmpleadoRegistradoPorRFC(rfc);
        if (EL_RFC_EXISTE_EN_BASE_DE_DATOS == true){
            
            throw new IllegalArgumentException("El RFC del empleado que ha ingresado "
                + "ya existe en el sistema. Por favor, verifíque la información e inténtelo de nuevo.");
        }
    }
    
    public void validarIdUsuarioNuevo(String idUsuario) throws SQLException, IllegalArgumentException{
        final boolean EL_ID_USUARIO_EXISTE_EN_BASE_DE_DATOS = 
            new UsuarioDAO().encontrarUsuarioPorIdUsuario(idUsuario);
        if (EL_ID_USUARIO_EXISTE_EN_BASE_DE_DATOS == true){
            throw new IllegalArgumentException("El identificador de usuario ingresado "
                + "ya existe en el sistema. Por favor, verifíque la información e inténtelo de nuevo.");
        }
    }
    
    public void validarCaracteres
    (String campo, String nombreCampo, String caracteresAceptados)
    throws IllegalArgumentException{
        if (!campo.matches(caracteresAceptados)){
            throw new IllegalArgumentException("El campo "+nombreCampo+" contiene caracteres "
                    + "no permitidos.");
        }
    }
}
