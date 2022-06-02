/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package biblioteca.vistas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author JosepHy
 */
public class FXMLRegistrarPrestamoController implements Initializable {

    @FXML
    private TextField tfMatricula;
    @FXML
    private TextField tfNombreLibro;
    @FXML
    private TextField tfDomicilio;
    @FXML
    private DatePicker dpDevolucion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btAceptar(ActionEvent event) {
    }

    @FXML
    private void btCancelar(ActionEvent event) {
    }
    
}
