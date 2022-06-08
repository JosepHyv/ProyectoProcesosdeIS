/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.modelo;

import biblioteca.encriptacion.EncriptadorSHA512;
import biblioteca.pojo.Empleado;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author ale71
 */
public class EmpleadoDAOTest {
    private Empleado empleadoTest;
    public EmpleadoDAOTest() {
    }

    @Before
    public void setUp() throws Exception {
        empleadoTest = new Empleado();
        empleadoTest.setNumEmpleado(420);
        empleadoTest.setIdUsuario("EMPLEADOTEST");
        empleadoTest.setContrasenia(
                new EncriptadorSHA512().encriptarCadena("EMPLEADOTEST")
        );
    }

    /**
     * Test of registrarEmpleado method, of class EmpleadoDAO.
     */
    @Test
    public void testRegistrarEmpleado() throws Exception {
        System.out.println("registrarEmpleado");
        Empleado empleadoNuevo = new Empleado();
        empleadoNuevo.setNumEmpleado(420);
        empleadoNuevo.getUsuario().setIdUsuario("EMPLEADOTEST");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.registrarUsuario(empleadoNuevo.getUsuario());
        
        EmpleadoDAO instance = new EmpleadoDAO();
        boolean expResult = true;
        boolean result = instance.registrarEmpleado(empleadoNuevo);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegistrarEmpleadoFallo() throws Exception {
        System.out.println("registrarEmpleado");
        EmpleadoDAO instance = new EmpleadoDAO();
        Empleado empleadoTest = new Empleado();
        empleadoTest.setIdUsuario("EMPLEADOTEST");
        empleadoTest.setContrasenia(
                new EncriptadorSHA512().encriptarCadena("EMPLEADOTEST")
        );
        Exception exception = assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
            instance.registrarEmpleado(empleadoTest);
        });
        String expectedMessage = "Cannot add or update a child row: a foreign key constraint fails";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
    /**
     * Test of encontrarEmpleadoRegistradoPorNumEmpleado method, of class EmpleadoDAO.
     */
    @Test
    public void testEncontrarEmpleadoRegistradoPorNumEmpleado() throws Exception {
        System.out.println("encontrarEmpleadoRegistradoPorNumEmpleado");
        int numEmpleado = 420;
        EmpleadoDAO instance = new EmpleadoDAO();
        boolean expResult = true;
        boolean result = instance.encontrarEmpleadoRegistradoPorNumEmpleado(numEmpleado);
        assertEquals(expResult, result);
    }

    @Test
    public void testEncontrarEmpleadoRegistradoPorNumEmpleadoFallo() throws Exception {
        System.out.println("encontrarEmpleadoRegistradoPorNumEmpleado");
        int numEmpleado = 69;
        EmpleadoDAO instance = new EmpleadoDAO();
        boolean expResult = false;
        boolean result = instance.encontrarEmpleadoRegistradoPorNumEmpleado(numEmpleado);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of encontrarEmpleadoRegistradoPorCURP method, of class EmpleadoDAO.
     */
    @Test
    public void testEncontrarEmpleadoRegistradoPorCURP() {
        try {
            System.out.println("encontrarEmpleadoRegistradoPorCURP");
            String curp = "CAFA941025HVZHRL03";
            EmpleadoDAO instance = new EmpleadoDAO();
            boolean expResult = true;
            boolean result = instance.encontrarEmpleadoRegistradoPorCURP(curp);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testEncontrarEmpleadoRegistradoPorCURPFallo() {
        try {
            System.out.println("encontrarEmpleadoRegistradoPorCURP");
            String curp = "iwannadie";
            EmpleadoDAO instance = new EmpleadoDAO();
            boolean expResult = false;
            boolean result = instance.encontrarEmpleadoRegistradoPorCURP(curp);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test of encontrarEmpleadoRegistradoPorNSS method, of class EmpleadoDAO.
     */
    @Test
    public void testEncontrarEmpleadoRegistradoPorNSS() {
        try {
            System.out.println("encontrarEmpleadoRegistradoPorNSS");
            String nss = "08169403626";
            EmpleadoDAO instance = new EmpleadoDAO();
            boolean expResult = true;
            boolean result = instance.encontrarEmpleadoRegistradoPorNSS(nss);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testEncontrarEmpleadoRegistradoPorNSSFallo() {
        try {
            System.out.println("encontrarEmpleadoRegistradoPorNSS");
            String nss = "pleasejustreleasemefromthismortalcoil";
            EmpleadoDAO instance = new EmpleadoDAO();
            boolean expResult = false;
            boolean result = instance.encontrarEmpleadoRegistradoPorNSS(nss);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Test of encontrarEmpleadoRegistradoPorRFC method, of class EmpleadoDAO.
     */
    @Test
    public void testEncontrarEmpleadoRegistradoPorRFC() {
        try {
            System.out.println("encontrarEmpleadoRegistradoPorRFC");
            String rfc = "CAFA941025UQ9";
            EmpleadoDAO instance = new EmpleadoDAO();
            boolean expResult = true;
            boolean result = instance.encontrarEmpleadoRegistradoPorRFC(rfc);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testEncontrarEmpleadoRegistradoPorRFCFallo() {
        try {
            System.out.println("encontrarEmpleadoRegistradoPorRFC");
            String rfc = "ibegofyoutojustshootmeoutback";
            EmpleadoDAO instance = new EmpleadoDAO();
            boolean expResult = false;
            boolean result = instance.encontrarEmpleadoRegistradoPorRFC(rfc);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of obtenerNumEmpleadoSiguiente method, of class EmpleadoDAO.
     */
    @Test
    public void testObtenerNumEmpleadoSiguiente() {
        try {
            System.out.println("obtenerNumEmpleadoSiguiente");
            EmpleadoDAO instance = new EmpleadoDAO();
            int expResult = 1243;
            int result = instance.obtenerNumEmpleadoSiguiente();
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testObtenerNumEmpleadoSiguienteFallo() {
        try {
            System.out.println("obtenerNumEmpleadoSiguiente");
            EmpleadoDAO instance = new EmpleadoDAO();
            int expResult = -1;
            int result = instance.obtenerNumEmpleadoSiguiente();
            assertNotEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test of encontrarEmpleadoPorNumEmpleadoYContrasenia method, of class EmpleadoDAO.
     */
    @Test
    public void testEncontrarEmpleadoPorNumEmpleadoYContrasenia() {
        try {
            System.out.println("encontrarEmpleadoPorNumEmpleadoYContrasenia");
            int numEmpleado = 420;
            String contraseniaSinEncriptar = "EMPLEADOTEST";
            EmpleadoDAO instance = new EmpleadoDAO();
            boolean expResult = true;
            boolean result = instance.encontrarEmpleadoPorNumEmpleadoYContrasenia(numEmpleado, contraseniaSinEncriptar);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testEncontrarEmpleadoPorNumEmpleadoYContraseniaFallo() {
        try {
            System.out.println("encontrarEmpleadoPorNumEmpleadoYContrasenia");
            int numEmpleado = 420;
            String contraseniaSinEncriptar = "LETMEIN";
            EmpleadoDAO instance = new EmpleadoDAO();
            boolean expResult = false;
            boolean result = instance.encontrarEmpleadoPorNumEmpleadoYContrasenia(numEmpleado, contraseniaSinEncriptar);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of obtenerEmpleadoPorNumEmpleadoYContrasenia method, of class EmpleadoDAO.
     */
    @org.junit.Test
    public void testObtenerEmpleadoPorNumEmpleadoYContrasenia() {
        try {
            System.out.println("obtenerEmpleadoPorNumEmpleadoYContrasenia");
            int numEmpleado = 1240;
            String contraseniaSinEncriptar = "EMPLEADO1240";
            EmpleadoDAO instance = new EmpleadoDAO();
            Empleado expResult = empleadoTest;
            Empleado result = instance.obtenerEmpleadoPorNumEmpleadoYContrasenia(numEmpleado, contraseniaSinEncriptar);
            System.out.println(result.toString());
            System.out.println(expResult.toString());
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
