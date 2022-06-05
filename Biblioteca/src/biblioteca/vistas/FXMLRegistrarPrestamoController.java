/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package biblioteca.vistas;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    @FXML
    private Label lbMatricula;
    @FXML
    private Label lbNombreLibro;
    @FXML
    private Label lbDomicilio;
    @FXML
    private Label lbFecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String test = getDate();
        System.out.println(test);
        
        
    }    

    @FXML
    private void btAceptar(ActionEvent event) {
        if(validar())
        {
            //hacerConexion();
            cerrarVentana();
        }
    }

    @FXML
    private void btCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private boolean validar()
    {
        String data = tfMatricula.getText();
        boolean ok = true;
        lbMatricula.setText("");
        lbNombreLibro.setText("");
        lbDomicilio.setText("");
        
        
        if(data.isEmpty())
        {
            lbMatricula.setText("El campo es obligatorio.");
            ok = false;
        }
        
        data = tfNombreLibro.getText();
        if(data.isEmpty())
        {
            lbNombreLibro.setText("El campo es obligatorio.");
            ok = false;
        }
        
        data = tfDomicilio.getText();
        if(data.isEmpty())
        {
            lbDomicilio.setText("El campo es obligatorio.");
            ok = false;
        }
       
        
        return ok;
    }
    
    private void cerrarVentana()
    {
       // Stage escenario = (Stage) (/*Aqui va el nombre de tu boton @Ale*/.getScene().getWindow());
      //  escenario.close();
    }
    
    private String getDate()
    {
        LocalDate fecha = dpDevolucion.getValue();
        String nuevaFecha = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return nuevaFecha;
    }
    
}
