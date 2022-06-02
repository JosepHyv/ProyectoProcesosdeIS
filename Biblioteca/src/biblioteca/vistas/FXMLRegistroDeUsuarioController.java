/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package biblioteca.vistas;

import biblioteca.modelo.UsuarioDAO;
import biblioteca.pojo.Usuario;
import biblioteca.pojo.constantes.ConstanteTiposDeContratacion;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class FXMLRegistroDeUsuarioController implements Initializable {
    @FXML
    private Label lbErrorCampos;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCorreoElectronico;
    @FXML
    private TextField tfNumeroTelefonico;
    @FXML
    private TextField tfCalle;
    
    @FXML
    private TextField tfNumeroDeDomicilio;
    @FXML
    private TextField tfMunicipio;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfIdentificador;
    @FXML
    private ComboBox<String> comboBoxTipoUsuario;
    @FXML
    private Button btnContinuar;


    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList(
                "Estudiante","Profesor"  
        );
        this.comboBoxTipoUsuario.setItems(list);
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
        usuarioNuevo.setEmail(this.tfCorreoElectronico.getText());
        usuarioNuevo.setTelefono(this.tfNumeroTelefonico.getText());
        usuarioNuevo.setTipoUsuario(this.comboBoxTipoUsuario.getSelectionModel().getSelectedItem());
        
        return usuarioNuevo;
    }
    
    @FXML
    private void continuar(ActionEvent event){
        lbErrorCampos.setText("");
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String calle = tfCalle.getText();
        String numero = tfNumeroDeDomicilio.getText();
        String colonia = tfColonia.getText();
        String municipio = tfMunicipio.getText();
        String email = tfCorreoElectronico.getText();
        String telefono = tfNumeroTelefonico.getText();
        String identificador = tfIdentificador.getText();
        String tipoUsuario = comboBoxTipoUsuario.getSelectionModel().getSelectedItem();  //Revisar

        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty() || calle.isEmpty() || numero.isEmpty() || colonia.isEmpty() || municipio.isEmpty() || email.isEmpty() || telefono.isEmpty() || tipoUsuario.isEmpty() ||identificador.isEmpty()) {
            lbErrorCampos.setText("Campos solicitados incompletos, por favor ingrese todos los campos");
        }
        else{
            registrarInformacion();
        }
    }
    
    private void registrarInformacion(){
        try {
            Usuario usuarioNuevo = crearUsuario();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.registrarUsuario(usuarioNuevo);
            Utilidades.mostrarAlertaConfirmacion("Aviso", 
                "La información se ha registrado exitosamente", Alert.AlertType.INFORMATION);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Utilidades.mostrarAlertaConfirmacion("Error de conexión", 
                "No ha sido posible establecer conexión con la base de datos.", Alert.AlertType.ERROR);
        }
        
    }
    
    @FXML
    private void btnCancelar(ActionEvent event){
        System.out.println("Usted ha salido de la ventana de registro :0");
        //Aqui irá lo de volver al menu principal
    }

}
