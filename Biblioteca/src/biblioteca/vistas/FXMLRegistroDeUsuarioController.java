/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package biblioteca.vistas;

import biblioteca.modelo.UsuarioDAO;
import biblioteca.pojo.Usuario;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import biblioteca.pojo.SecureTextField;
import biblioteca.pojo.constantes.ConstanteCaracteresLegales;


/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class FXMLRegistroDeUsuarioController implements Initializable {
    @FXML
    private Label lbErrorCampos;
    @FXML
    private SecureTextField tfNombre;
    @FXML
    private SecureTextField tfApellidoPaterno;
    @FXML
    private SecureTextField tfApellidoMaterno;
    @FXML
    private SecureTextField tfCorreoElectronico;
    @FXML
    private SecureTextField tfNumeroTelefonico;
    @FXML
    private ComboBox <String> comboBoxTipoUsuario;
    @FXML
    private SecureTextField tfCalle;
    @FXML
    private SecureTextField tfNumeroDeDomicilio;
    @FXML
    private SecureTextField tfMunicipio;
    @FXML
    private SecureTextField tfColonia;
    @FXML
    private SecureTextField tfIdentificador;
    @FXML
    private Button btnContinuar;
    @FXML
    private Button btnCancelar;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList(
                "Estudiante","Profesor"  
        );
        this.comboBoxTipoUsuario.setItems(list);
        asignarCondiciones();
    }    
    
private Usuario crearUsuario() throws SQLException{
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setIdUsuario(tfIdentificador.getText());
        usuarioNuevo.setNombre(tfNombre.getText());
        usuarioNuevo.setApellidoPaterno(this.tfApellidoPaterno.getText());
        usuarioNuevo.setApellidoMaterno(this.tfApellidoMaterno.getText());
        usuarioNuevo.setCalle(this.tfCalle.getText());
        usuarioNuevo.setColonia(this.tfColonia.getText());
        usuarioNuevo.setNumero(this.tfNumeroDeDomicilio.getText());
        usuarioNuevo.setColonia(this.tfColonia.getText());
        usuarioNuevo.setMunicipio(this.tfMunicipio.getText());
        String emailUsuarioNuevo = (this.tfCorreoElectronico.getText()+"@escuela.com");
        usuarioNuevo.setEmail(emailUsuarioNuevo);
        usuarioNuevo.setTelefono(this.tfNumeroTelefonico.getText());
        usuarioNuevo.setTipoUsuario(this.comboBoxTipoUsuario.getSelectionModel().getSelectedItem());
        return usuarioNuevo;
    }

    @FXML
    private void validar(){
        try{
            validarCamposLlenos();
            validarLongitud();
            validarCB();
        
            registrarUsuario();

            lbErrorCampos.setText("");
            tfNombre.setText("");
            tfApellidoPaterno.setText("");
            tfApellidoMaterno.setText("");
            tfCorreoElectronico.setText("");
            tfNumeroTelefonico.setText("");
            tfCalle.setText("");
            tfNumeroDeDomicilio.setText("");
            tfMunicipio.setText("");
            tfColonia.setText("");
            tfIdentificador.setText("");     
            this.comboBoxTipoUsuario.getSelectionModel().clearSelection();     
                   
        }catch (IllegalArgumentException iaException){
            Utilidades.mostrarAlertaConfirmacion(
                "Aviso", iaException.getMessage(), 
                Alert.AlertType.WARNING
            );
        }
    }

    private void registrarUsuario(){
        try {
            Usuario usuarioNuevo = crearUsuario();
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            if(usuarioDAO.encontrarUsuarioPorIdUsuario (tfIdentificador.getText())){
              Utilidades.mostrarAlertaConfirmacion("Aviso", 
                "Este identificador ya existe en la base de datos", Alert.AlertType.ERROR);  
            }else{
                usuarioDAO.registrarUsuario(usuarioNuevo);
                Utilidades.mostrarAlertaConfirmacion("Aviso", 
                "La información se ha registrado exitosamente", Alert.AlertType.INFORMATION);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Utilidades.mostrarAlertaConfirmacion("Error de conexión", 
                "No ha sido posible establecer conexión con la base de datos.", Alert.AlertType.ERROR);
        }
        
    }

    @FXML
    private void btnCancelar(ActionEvent event){
        Utilidades.mostrarAlertaConfirmacion(
            "¿Desea confirmar esta acción?", "Se perderá la información introducida en los campos", 
            Alert.AlertType.CONFIRMATION);
        boolean confirmarCancelacion =
            Utilidades.getOption().orElse(ButtonType.CANCEL).getButtonData().isDefaultButton();
        cerrar(confirmarCancelacion);
    }

    @FXML
    private void cerrar(boolean confirmacion){
        if (confirmacion == true){
            Stage stage = (Stage)btnCancelar.getScene().getWindow();
            stage.close();   
        }
    }

    @FXML
    private void btnContinuar(ActionEvent event){
        Utilidades.mostrarAlertaConfirmacion(
            "¿Desea confirmar esta acción?", "¿Desea confirmar la creación de un nuevo usuario con los datos introducidos?", 
            Alert.AlertType.CONFIRMATION);
        boolean confirmarCreacion =
            Utilidades.getOption().orElse(ButtonType.CANCEL).getButtonData().isDefaultButton();
        guardarUsuario(confirmarCreacion);
}
    @FXML
    private void guardarUsuario(boolean confirmacion){
        if (confirmacion==true){
            validar();
        }
    }

    private void asignarCondiciones(){
        this.tfNombre.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFABETICOS_TEXTFIELD.getCaracteres());
        this.tfNombre.setMaximoCaracteres(50);
        this.tfApellidoPaterno.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFABETICOS_TEXTFIELD.getCaracteres());
        this.tfApellidoPaterno.setMaximoCaracteres(50);
        this.tfApellidoMaterno.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFABETICOS_TEXTFIELD.getCaracteres());
        this.tfApellidoMaterno.setMaximoCaracteres(50);
        this.tfCorreoElectronico.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFANUMERICOS_TEXTFIELD.getCaracteres());
        this.tfCorreoElectronico.setMaximoCaracteres(9);
        this.tfCorreoElectronico.setMinimoCaracteres(9);
        this.tfNumeroTelefonico.setCaracteresPermitidos(ConstanteCaracteresLegales.NUMERICOS.getCaracteres());
        this.tfNumeroTelefonico.setMaximoCaracteres(10);
        this.tfNumeroTelefonico.setMinimoCaracteres(10);
        this.tfCalle.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFANUMERICOS_TEXTFIELD.getCaracteres());
        this.tfCalle.setMaximoCaracteres(50);
        this.tfNumeroDeDomicilio.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFANUMERICOS_TEXTFIELD.getCaracteres());
        this.tfCalle.setMaximoCaracteres(50);
        this.tfMunicipio.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFABETICOS_TEXTFIELD.getCaracteres());
        this.tfMunicipio.setMaximoCaracteres(50);
        this.tfIdentificador.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFANUMERICOS_TEXTFIELD.getCaracteres());
        this.tfIdentificador.setMaximoCaracteres(50);

        this.tfNumeroTelefonico.setNombreDelCampo("Número Telefonico");
        this.tfCorreoElectronico.setNombreDelCampo("Correo Electronico");

    }

    public void validarLongitud()throws IllegalArgumentException{
        this.tfNumeroTelefonico.fallarSiFaltanCaracteres();
        this.tfCorreoElectronico.fallarSiFaltanCaracteres();
    }

    public void validarCamposLlenos() throws IllegalArgumentException{
        this.tfNombre.fallarSiEstaEnBlanco();
        this.tfApellidoPaterno.fallarSiEstaEnBlanco();
        this.tfApellidoMaterno.fallarSiEstaEnBlanco();
        this.tfCorreoElectronico.fallarSiEstaEnBlanco();
        this.tfNumeroTelefonico.fallarSiEstaEnBlanco();
        this.tfCalle.fallarSiEstaEnBlanco();
        this.tfNumeroDeDomicilio.fallarSiEstaEnBlanco();
        this.tfMunicipio.fallarSiEstaEnBlanco();
        this.tfColonia.fallarSiEstaEnBlanco();
        this.tfIdentificador.fallarSiEstaEnBlanco();
    }
    private void validarCB() throws IllegalArgumentException{
        final boolean TODOS_LOS_CAMPOS_ESTAN_LLENOS= !this.comboBoxTipoUsuario.getSelectionModel().isEmpty();
        if (TODOS_LOS_CAMPOS_ESTAN_LLENOS==false){
            throw new IllegalArgumentException("Por favor, seleccione un tipo de usuario.");
        }
    }

}