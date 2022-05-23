/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package biblioteca.vistas;

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
    private Button btnIniciarSesion;
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
    
}
