/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package biblioteca.vistas;

import biblioteca.pojo.Prestamo;
import biblioteca.pojo.Usuario;

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
            
        }
        else
        {
            
        }
    }

    @FXML
    private void btCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private boolean validaFecha(LocalDate fecha)
    {
        LocalDate fechaActual = LocalDate.now();
        boolean ok = true;
        if(fechaActual.isEqual(fecha) || fechaActual.isAfter(fecha))
            ok = false;
        return ok;
    }
    
    private boolean validar()
    {
        String data = tfMatricula.getText();
        boolean ok = true;
        lbMatricula.setText("");
        lbNombreLibro.setText("");
        lbDomicilio.setText("");
        lbFecha.setText("");
        
        
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
        
        data = getDate();
        if(data.isEmpty())
        {
            lbFecha.setText("El campo es obligatorio.");
            ok = false;
        }
        else
        {
            if(!validaFecha(dpDevolucion.getValue()))
            {
                lbFecha.setText("La fecha es Invalida");
                ok = false;
            }
        }
       
        if(ok)
        {
            Prestamo nuevoPrestamo = new Prestamo();
            nuevoPrestamo.setIdLibro(tfNombreLibro.getText());
            nuevoPrestamo.setFechaDevolucion(dpDevolucion.getValue());
            Usuario alquilador = new Usuario();        
            registrarPrestamo(alquilador, nuevoPrestamo);
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
