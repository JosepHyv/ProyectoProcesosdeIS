/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.modelo;

import biblioteca.pojo.Usuario;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;

/**
 *
 * @author ale71
 */
public class UsuarioDAOTest {
    
    public UsuarioDAOTest() {
    }

    /**
     * Test of registrarUsuario method, of class UsuarioDAO.
     */
    @Test
    public void testRegistrarUsuario() throws Exception {
        System.out.println("registrarUsuario");
        Usuario usuarioNuevo = new Usuario();
        UsuarioDAO instance = new UsuarioDAO();
        boolean expResult = true;
        boolean result = instance.registrarUsuario(usuarioNuevo);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testRegistrarUsuarioFallo() throws Exception {
        System.out.println("registrarUsuario");
        UsuarioDAO instance = new UsuarioDAO();
        Usuario usuarioNuevo = new Usuario();
        Exception exception = assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
            instance.registrarUsuario(usuarioNuevo);
        });
        String expectedMessage = "Duplicate entry 'sinIdUsuario' for key 'usuarios.PRIMARY'";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test of encontrarUsuarioPorIdUsuario method, of class UsuarioDAO.
     */
    @Test
    public void testEncontrarUsuarioPorIdUsuario() {
        try {
            System.out.println("encontrarUsuarioPorIdUsuario");
            String idUsuario = "sinIdUsuario";
            UsuarioDAO instance = new UsuarioDAO();
            boolean expResult = true;
            boolean result = instance.encontrarUsuarioPorIdUsuario(idUsuario);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testEncontrarUsuarioPorIdUsuarioFallo() {
        try {
            System.out.println("encontrarUsuarioPorIdUsuario");
            String idUsuario = "whymustthispaincontinue";
            UsuarioDAO instance = new UsuarioDAO();
            boolean expResult = false;
            boolean result = instance.encontrarUsuarioPorIdUsuario(idUsuario);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
