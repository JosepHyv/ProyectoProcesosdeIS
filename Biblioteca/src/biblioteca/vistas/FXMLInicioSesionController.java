/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package biblioteca.vistas;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        lbErrorEmpleado.setText("");
        lbErrorPassword.setText("");
        
        String numero = tfNumeroEmpleado.getText();
        String password = pfPassword.getText();
        boolean isValid = true;
        
        if(numero.isEmpty())
        {
           lbErrorEmpleado.setText("El campo es obligatorio");
           isValid = false;
        }
            
        if(password.isEmpty())
        {
           lbErrorPassword.setText("El campo es obligatorio");
           isValid = false;
        }
        
        if(isValid)
        {
            irPantallaPrincipal();
            //solo para debbug
            System.out.println("Inicio de sesion Valido :)"); 
        }
    }
    
    private void irPantallaPrincipal()
    {
        /* procastinando 
            Propuestas:
            Hacer un menu
            Hacer un tipo chrome con 4 pestañas por cada CU
            
            Si ven esto, hare las DAO y los POJO
            */

        
    }

    
}
