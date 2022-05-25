/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.vistas;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 *
 * @author THE CHAVI’S
 */
public class Utilidades {
    private static Optional<ButtonType> option;
    public static void mostrarAlertaSinConfirmacion(String titulo, String mensaje, Alert.AlertType tipoAlerta){
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public static void mostrarAlertaConfirmacion(String titulo, String mensaje, Alert.AlertType tipoAlerta){
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        option = alerta.showAndWait();
    }

    public static Optional<ButtonType> getOption() {
        return option;
    }
    
    public static void mensajePerdidaDeConexion(){
        Utilidades.mostrarAlertaSinConfirmacion(
            "Pérdida de conexión", 
            "No se pudo conectar con la base de datos. "+
            "Por favor, inténtelo más tarde.", 
            Alert.AlertType.ERROR
        );
    }
    
    public static void mensajeErrorAlCargarLaInformacionDeLaVentana(){
          Utilidades.mostrarAlertaSinConfirmacion("Error de sistema", "Hubo un error "
          + "al cargar la información. Por favor, inténtelo más tarde",Alert.AlertType.ERROR);
    }
    
}
