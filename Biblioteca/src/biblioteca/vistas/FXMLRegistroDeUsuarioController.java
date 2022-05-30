/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package biblioteca.vistas;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ComboBox <String> cbTipoDeUsuario;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfNumeroDeDomicilio;
    @FXML
    private TextField tfMunicipio;
    @FXML
    private TextField tfColonia;



    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Estudiante","Profesor");
        //cbTipoDeUsuario.setItems(list); //Aqui me da error creo que no entiendo los combobox asdfg :c 
    }    
    

    @FXML
    private void btnContinuar(ActionEvent event){
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
        String tipoUsuario = cbTipoDeUsuario.getSelectionModel().getSelectedItem();  //Revisar

        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty() || calle.isEmpty() || numero.isEmpty() || colonia.isEmpty() || municipio.isEmpty() || email.isEmpty() || telefono.isEmpty() || tipoUsuario.isEmpty()) {
            lbErrorCampos.setText("Campos solicitados incompletos, por favor ingrese todos los campos");
        }
        else{
            System.out.println("Usuario creado satisfactoriamente YEE HAW");
        }
    }
    @FXML
    private void btnCancelar(ActionEvent event){
        System.out.println("Usted ha salido de la ventana de registro :0");
        //Aqui irá lo de volver al menu principal
    }
}
