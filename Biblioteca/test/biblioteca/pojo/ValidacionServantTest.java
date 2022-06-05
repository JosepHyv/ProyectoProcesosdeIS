/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.pojo;

import biblioteca.pojo.constantes.ConstanteCaracteresLegales;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ale71
 */
public class ValidacionServantTest{
    
    public ValidacionServantTest() {
    }

    /**
     * Test of validarUsuario method, of class ValidacionServant.
     */
    @Test
    public void testValidarCaracteres_Alfabetico() {
        System.out.println("validarCaracteres_Alfabetico");
        String campo = "234";
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarCaracteres(campo,"Campo",ConstanteCaracteresLegales.ALFABETICOS_VALIDACION.getCaracteres());
        });
        String expectedMessage = "El campo Campo contiene caracteres "
                    + "no permitidos.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    public void testValidarCaracteres_EmailFallo() {
        System.out.println("validarCaracteres_Alfabetico");
        String campo = "ew?qr@fdsa.com";
        System.out.println(campo);
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarCaracteres(campo,"Email",ConstanteCaracteresLegales.EMAIL.getCaracteres());
        });
        String expectedMessage = "El campo Email contiene caracteres "
                    + "no permitidos.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    public void testValidarCaracteres_EmailFallo2() {
        System.out.println("validarCaracteres_Alfabetico");
        String campo = "fdsafdsa.com";
        System.out.println(campo);
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarCaracteres(campo,"Email",ConstanteCaracteresLegales.EMAIL.getCaracteres());
        });
        String expectedMessage = "El campo Email contiene caracteres "
                    + "no permitidos.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    public void testValidarCaracteres_EmailFallo3() {
        System.out.println("validarCaracteres_Alfabetico");
        String campo = "fdsa@fdsa";
        System.out.println(campo);
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarCaracteres(campo,"Email",ConstanteCaracteresLegales.EMAIL.getCaracteres());
        });
        String expectedMessage = "El campo Email contiene caracteres "
                    + "no permitidos.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    public void testValidarCaracteres_EmailFallo4() {
        System.out.println("validarCaracteres_Alfabetico");
        String campo = "fdsa@.com";
        System.out.println(campo);
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarCaracteres(campo,"Email",ConstanteCaracteresLegales.EMAIL.getCaracteres());
        });
        String expectedMessage = "El campo Email contiene caracteres "
                    + "no permitidos.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    public void testValidarCaracteres_Email() {
        System.out.println("validarCaracteres_Alfabetico");
        String campo = "e_wqr@fdsa.com";
        ValidacionServant instance = new ValidacionServant();
        instance.validarCaracteres(campo,"Email",ConstanteCaracteresLegales.EMAIL.getCaracteres());
    }
    
    @Test
    public void testValidarCaracteres_AlfabeticoIlegal() {
        System.out.println("validarCaracteres_Alfabetico");
        String campo = ".+-/";
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarCaracteres(campo,"Campo",ConstanteCaracteresLegales.ALFABETICOS_VALIDACION.getCaracteres());
        });
        String expectedMessage = "El campo Campo contiene caracteres "
                    + "no permitidos.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    public void testValidarCaracteres_Alfanumerico() {
        System.out.println("validarCaracteres_Alfanumerico");
        String campo = "--SELECT*/";
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarCaracteres(campo,"Campo",ConstanteCaracteresLegales.ALFANUMERICOS_TEXTFIELD.getCaracteres());
        });
        String expectedMessage = "El campo Campo contiene caracteres "
                    + "no permitidos.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
    
    @Test
    public void testValidarCaracteres_Numerico() {
        System.out.println("validarCaracteres_Alfanumerico");
        String campo = "asdf";
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarCaracteres(campo,"Campo",ConstanteCaracteresLegales.NUMERICOS.getCaracteres());
        });
        String expectedMessage = "El campo Campo contiene caracteres "
                    + "no permitidos.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    public void testValidarCaracteres_NumericoIlegal() {
        System.out.println("validarCaracteres_Alfanumerico");
        String campo = ",.2,342.";
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarCaracteres(campo,"Campo",ConstanteCaracteresLegales.NUMERICOS.getCaracteres());
        });
        String expectedMessage = "El campo Campo contiene caracteres "
                    + "no permitidos.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test of validarNumEmpleadoNuevo method, of class ValidacionServant.
     */
    @Test
    public void testValidarNumEmpleadoNuevo() {
        System.out.println("validarNumEmpleadoNuevo");
        int numEmpleado = 1234;
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarNumEmpleadoNuevo(numEmpleado);
        });
        String expectedMessage = "El identificador del empleado que ha ingresado "
                + "ya existe en el sistema. Por favor, verifíque la información e inténtelo de nuevo.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test of validarCURPNuevo method, of class ValidacionServant.
     */
    @Test
    public void testValidarCURPNuevo() throws Exception {
        System.out.println("validarCURPNuevo");
        Empleado objeto = new Empleado();
        objeto.setNss("CAFA941025HVZHRL03");
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarCURPNuevo(objeto.getNss());
        });
        String expectedMessage = "El CURP del empleado que ha ingresado "
                + "ya existe en el sistema. Por favor, verifíque la información e inténtelo de nuevo.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test of validarNSSNuevo method, of class ValidacionServant.
     */
    @Test
    public void testValidarNSSNuevo() throws Exception {
        System.out.println("validarNSSNuevo");
        Empleado objeto = new Empleado();
        objeto.setNss("08169403626");
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarNSSNuevo(objeto.getNss());
        });
        String expectedMessage = "El NSS del empleado que ha ingresado "
                + "ya existe en el sistema. Por favor, verifíque la información e inténtelo de nuevo.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test of validarRFCNuevo method, of class ValidacionServant.
     */
    @Test
    public void testValidarRFCNuevo() throws Exception {
        System.out.println("validarRFCNuevo");
        Empleado objeto = new Empleado();
        objeto.setRfc("CAFA941025HVZHRL03");
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarRFCNuevo(objeto.getRfc());
        });
        String expectedMessage = "El RFC del empleado que ha ingresado "
                + "ya existe en el sistema. Por favor, verifíque la información e inténtelo de nuevo.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test of validarIdUsuarioNuevo method, of class ValidacionServant.
     */
    @Test
    public void testValidarIdUsuarioNuevo() throws Exception {
        System.out.println("validarIdUsuarioNuevo");
        String idUsuario = "EMPLEADO1236";
        ValidacionServant instance = new ValidacionServant();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.validarIdUsuarioNuevo(idUsuario);
        });
        String expectedMessage = "El identificador de usuario ingresado "
                + "ya existe en el sistema. Por favor, verifíque la información e inténtelo de nuevo.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
}
