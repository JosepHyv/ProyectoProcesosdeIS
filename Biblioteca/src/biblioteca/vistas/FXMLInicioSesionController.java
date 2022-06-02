/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package biblioteca.vistas;

import biblioteca.encriptacion.EncriptadorSHA512;
import biblioteca.modelo.EmpleadoDAO;
import biblioteca.pojo.Empleado;
import biblioteca.pojo.InformacionSesion;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JosepHy
 */
public class FXMLInicioSesionController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField tfNumeroEmpleado;
    @FXML
    private Label lbErrorEmpleado;
    @FXML
    private Label lbErrorPassword;
    @FXML
    private PasswordField pfPassword;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    
    
    @FXML
    private void btnIniciarSesion(ActionEvent event) 
    {
        try {
            
            lbErrorEmpleado.setText("");
            lbErrorPassword.setText("");
            
            String strNumeroEmpleado = tfNumeroEmpleado.getText();
            int numeroEmpleado = Integer.parseInt(strNumeroEmpleado);
            String password = pfPassword.getText();
            boolean isValid = false;
            
            if(strNumeroEmpleado.isEmpty())
            {
                lbErrorEmpleado.setText("El campo es obligatorio");
                isValid = false;
            } 
            
            if(password.isEmpty())
            {
                lbErrorPassword.setText("El campo es obligatorio");
                isValid = false;
            }

            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            Empleado empleadoObtenido = empleadoDAO.obtenerEmpleadoPorNumEmpleadoYContrasenia(numeroEmpleado, password);
            int numeroEmpleadoObtenido = empleadoObtenido.getNumEmpleado();
            String contraseniaObtanida = empleadoObtenido.getContrasenia();
            isValid=(numeroEmpleadoObtenido == numeroEmpleado) && (new EncriptadorSHA512().encriptarCadena(password).equals(contraseniaObtanida));
            
            if(isValid)
            {
                Utilidades.mostrarAlertaSinConfirmacion("Aviso", "Bienvenido(a).", Alert.AlertType.INFORMATION);
                InformacionSesion.getInformacionSesion().setTipoContratacionDelEmpleado(empleadoObtenido.getTipoContratacion());
                irPantallaPrincipal();
            }else{
                Utilidades.mostrarAlertaSinConfirmacion("Error", 
                    "El número de empleado y/o contraseña son incorrectos", Alert.AlertType.WARNING);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void irPantallaPrincipal()
    {
        Stage escenarioPrincipal = (Stage) tfNumeroEmpleado.getScene().getWindow();
        try {
            URL url = new File("src/biblioteca/vistas/FXMLPanelPrincipal.fxml").toURI().toURL();
            Scene pantallaPrincipal =
            new Scene(FXMLLoader.load(url));
            escenarioPrincipal.setScene(pantallaPrincipal);
            escenarioPrincipal.setTitle("Sistema de Préstamos Bibliotecario");
            escenarioPrincipal.show();
        } catch(IOException ioException){
            Utilidades.mensajeErrorAlCargarLaInformacionDeLaVentana();
            ioException.printStackTrace();
        }   catch(IllegalStateException isException){
            Utilidades.mensajeErrorAlCargarLaInformacionDeLaVentana();
            isException.printStackTrace();
        }
        
    }
}
