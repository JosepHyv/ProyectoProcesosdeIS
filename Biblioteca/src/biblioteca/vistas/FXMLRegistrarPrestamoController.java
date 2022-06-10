/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package biblioteca.vistas;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import javafx.scene.control.ButtonType;

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
    private Button btCancelar;
    @FXML
    private Label lbMatricula;
    @FXML
    private Label lbNombreLibro;
    @FXML
    private Label lbDomicilio;
    @FXML
    private Label lbFecha;

    private LocalDate CURRENT_DATE = LocalDate.now();
    
    private int MIN_DIAS = 1;
    
    private int MAX_DIAS = 7;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LocalDate nuevaFecha = this.CURRENT_DATE.plusDays(1);
        dpDevolucion.setValue(nuevaFecha);
    }    

    @FXML
    private void btAceptar(ActionEvent event) {
        if(validarCampos())
            Utilidades.mostrarAlertaSinConfirmacion("Aviso", "Las cosas funcionan :3", Alert.AlertType.INFORMATION);
        else
            Utilidades.mostrarAlertaSinConfirmacion("Error", "Los Campos estan vacios o son inforrectos\nfavor deverificar", Alert.AlertType.ERROR);
    }
    
    private void cerrarVentana(boolean option)
    {
        if(option)
        {
            Stage stage = (Stage) btCancelar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void optCancelar(ActionEvent event) {
        Utilidades.mostrarAlertaConfirmacion(
                "Cancelar",
                "¿Está seguro de que desea cancelar el prestamo?", 
                Alert.AlertType.CONFIRMATION);
        boolean option = Utilidades.getOption().orElse(ButtonType.CANCEL).getButtonData().isDefaultButton();
        cerrarVentana(option);
        
    }
    
    private boolean validarFecha(LocalDate fecha)
    {
        DayOfWeek day;
        boolean ok = true;
        int diasValidos = 0;
        int diferenciaDias = 0 ;
        LocalDate fechaIterable = this.CURRENT_DATE;
        lbFecha.setText("");
        
        if(fecha.isBefore(this.CURRENT_DATE) || fecha.isEqual(this.CURRENT_DATE))
        {
            ok = false;
            lbFecha.setText("La fecha debe ser mayor\na la Fecha Actual.");
        }
        
        
        day = DayOfWeek.of(fecha.get(ChronoField.DAY_OF_WEEK));
        if(ok && (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY))
        {
            ok = false;
           lbFecha.setText("La devolucion no puede ser\nen fin de semana");
        }
        
        while(ok && fecha.isAfter(fechaIterable) && !fechaIterable.equals(fecha))
        {
            fechaIterable = fechaIterable.plusDays(1);
            diferenciaDias++;
            day = DayOfWeek.of(fechaIterable.get(ChronoField.DAY_OF_WEEK));
            diasValidos +=  (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) ? 1 : 0;
          //  System.out.println(fechaIterable + " hoy es " + day);
        }
        //System.out.println("Numero de dias validos " + diasValidos + " Diferencias en dias " + diferenciaDias);
        if(ok && (diasValidos < this.MIN_DIAS || diasValidos > this.MAX_DIAS))
        {
            ok = false;
            lbFecha.setText("El numero de dias del prestamo debe estar en\nel rango de " + this.MIN_DIAS + " a " + this.MAX_DIAS + " dias sin contar fines de\nsemana (sabados y domingos).");
           
        }
        
        return ok;
    }
    
    private boolean validarCampos()
    {
        boolean ok = true;
        
        String MESSAGE = "El campo es Obligatorio.";
        
        lbMatricula.setText("");
        lbNombreLibro.setText("");
        lbDomicilio.setText("");
        lbFecha.setText("");
        
        
        
        if(tfMatricula.getText().isEmpty())
        {
            ok = false;
            lbMatricula.setText(MESSAGE);
        }
        if(tfNombreLibro.getText().isEmpty())
        {
            ok = false;
            lbNombreLibro.setText(MESSAGE);
        }
        
        if(tfDomicilio.getText().isEmpty())
        {
            ok = false;
            lbDomicilio.setText(MESSAGE);
        }
        
      
        
            
        LocalDate dateOfdp = dpDevolucion.getValue();
        if(dateOfdp == null)
        {
            ok = false;
            lbFecha.setText("Debe ingresar una fecha valida.");
        }
        else ok = validarFecha(dateOfdp);
        
        System.out.println("La fecha que tiene es: " + dateOfdp);
        
        return ok; //ok && validarFecha(dpDevolucion.getValue());        
    }
    
    
    public void setMinDias(int dias)
    {
        if(dias < 1) this.MIN_DIAS = 1;
        else this.MIN_DIAS = dias;
    }
    
    public void setMaxDias(int dias)
    {
        if(dias < 1 || dias < this.MIN_DIAS) this.MAX_DIAS = 7;
        else this.MAX_DIAS = dias;
    }
    
   
    
}
