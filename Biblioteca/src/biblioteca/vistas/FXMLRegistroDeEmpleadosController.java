/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.vistas;

import biblioteca.dataaccess.DataBaseConnection;
import biblioteca.modelo.EmpleadoDAO;
import biblioteca.modelo.UsuarioDAO;
import biblioteca.pojo.Empleado;
import biblioteca.pojo.Usuario;
import biblioteca.pojo.constantes.ConstanteTiposDeContratacion;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author ale71
 */
public class FXMLRegistroDeEmpleadosController extends Application implements Initializable {

    @FXML
    private ComboBox<String> comboBoxTipoContratacion;
    @FXML
    private TextField textFieldNombres;
    @FXML
    private TextField textFieldNss;
    @FXML
    private TextField textFieldApellidoM;
    @FXML
    private TextField textFieldCurp;
    @FXML
    private TextField textFieldApellidoP;
    @FXML
    private TextField textFieldRfc;
    @FXML
    private TextField textFieldCalle;
    @FXML
    private TextField textFieldNumero;
    @FXML
    private TextField textFieldMunicipio;
    @FXML
    private TextField textFieldColonia;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldTelefono1;
    @FXML
    private TextField textFieldTelefono2;
    @FXML
    private Button buttonDarDeAlta;
    @FXML
    private Button buttonCancelar;
    @FXML
    private DatePicker datePickerNacimiento;
    @FXML
    private TextField textFieldNumeroEmpleado;

    public void start(Stage stage) throws Exception {
        URL url = new File("src/biblioteca/vistas/FXMLRegistroDeEmpleados.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        DataBaseConnection db = new DataBaseConnection();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            inicializarTextFieldNumEmpleados();
            inicializarComboBoxTipoContratacion();
        } catch (SQLException sqlException) {
            Utilidades.mensajePerdidaDeConexion();
        }
    }

    private void inicializarTextFieldNumEmpleados() throws SQLException{
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        final int NUMERO_DEL_NUEVO_EMPLEADO = empleadoDAO.obtenerNumEmpleadoSiguiente();
        this.textFieldNumeroEmpleado.setText(Integer.toString(NUMERO_DEL_NUEVO_EMPLEADO));
        this.textFieldNumeroEmpleado.setEditable(false);
    }

    private void inicializarComboBoxTipoContratacion(){
        ObservableList<String> tiposDeContratacion = FXCollections.observableArrayList(
            ConstanteTiposDeContratacion.JEFE.getNombresTiposDeContratacion()
        );
        this.comboBoxTipoContratacion.setItems(tiposDeContratacion);
    }
    
    @FXML
    private void darDeAlta(ActionEvent event) {
        try {
            validarCamposLlenos();
            Usuario usuarioDelNuevoEmpleado = crearUsuarioDelNuevoEmpleado();
            Empleado nuevoEmpleado = crearNuevoEmpleado(usuarioDelNuevoEmpleado);
            registrarEmpleado(nuevoEmpleado);
            Utilidades.mostrarAlertaSinConfirmacion(
                "Aviso", 
                "La información se ha guardado correctamente",
                Alert.AlertType.INFORMATION);
            terminar();
        }catch (SQLException sqlException) {
            Utilidades.mensajePerdidaDeConexion();            
        } catch (IllegalArgumentException iaException){
            Utilidades.mostrarAlertaConfirmacion(
                "Aviso", 
                iaException.getMessage(), 
                Alert.AlertType.WARNING
            );
        }
    }
    
    private void terminar() throws SQLException{
        this.textFieldNumeroEmpleado.setText("");
        this.comboBoxTipoContratacion.getSelectionModel().clearSelection();
        this.textFieldNombres.setText("");
        this.textFieldApellidoP.setText("");
        this.textFieldApellidoM.setText("");
        this.textFieldCurp.setText("");
        this.textFieldNss.setText("");
        this.textFieldRfc.setText("");
        this.datePickerNacimiento.setValue(null);
        this.textFieldCalle.setText("");
        this.textFieldNumero.setText("");
        this.textFieldColonia.setText("");
        this.textFieldMunicipio.setText("");
        this.textFieldEmail.setText("");
        this.textFieldTelefono1.setText("");
        this.textFieldTelefono2.setText("");
        this.inicializarTextFieldNumEmpleados();
    }
    
    private void registrarEmpleado(Empleado nuevoEmpleado) throws SQLException{
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.registrarUsuario(nuevoEmpleado.getUsuario());
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        empleadoDAO.registrarEmpleado(nuevoEmpleado);
    }
    
    private void validarCamposLlenos() throws IllegalArgumentException{
        final boolean TODOS_LOS_CAMPOS_ESTAN_LLENOS=
            validarCamposDeTextoLlenos(this.textFieldNombres.getText())
            && validarCamposDeTextoLlenos(this.textFieldNombres.getText())
            && validarCamposDeTextoLlenos(this.textFieldApellidoP.getText())
            && validarCamposDeTextoLlenos(this.textFieldApellidoM.getText())
            && validarCamposDeTextoLlenos(this.textFieldCalle.getText())
            && validarCamposDeTextoLlenos(this.textFieldNumero.getText())
            && validarCamposDeTextoLlenos(this.textFieldColonia.getText())
            && validarCamposDeTextoLlenos(this.textFieldMunicipio.getText())
            && validarCamposDeTextoLlenos(this.textFieldEmail.getText())
            && validarCamposDeTextoLlenos(this.textFieldTelefono1.getText())
            && validarCamposDeTextoLlenos(this.textFieldTelefono2.getText())
            && this.datePickerNacimiento.getValue()!=null
            && !this.comboBoxTipoContratacion.getSelectionModel().isEmpty();
        if (TODOS_LOS_CAMPOS_ESTAN_LLENOS==false){
            throw new IllegalArgumentException("No puede dejar ningún campo vacío.");
        }
    }
    
    private boolean validarCamposDeTextoLlenos(String cadenaEnElCampo){
        return (cadenaEnElCampo.trim().length() > 0);
    }
    
    private Empleado crearNuevoEmpleado(Usuario usuarioDelNuevoEmpleado) 
    throws SQLException, IllegalArgumentException{
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNumEmpleado(Integer.parseInt(this.textFieldNumeroEmpleado.getText()));
        nuevoEmpleado.setFechaNacimiento(this.datePickerNacimiento.getValue());
        nuevoEmpleado.setNss(this.textFieldNss.getText());
        nuevoEmpleado.setCurp(this.textFieldCurp.getText());
        nuevoEmpleado.setRfc(this.textFieldRfc.getText());
        nuevoEmpleado.setTelefonoEmpleado(this.textFieldTelefono2.getText());
        nuevoEmpleado.setContrasenia(usuarioDelNuevoEmpleado.getIdUsuario());
        nuevoEmpleado.setTipoContratacion(
            this.comboBoxTipoContratacion.getSelectionModel().getSelectedItem()
        );
        nuevoEmpleado.setUsuario(usuarioDelNuevoEmpleado);
        nuevoEmpleado.validar();
        
        return nuevoEmpleado;
    }
    
    private Usuario crearUsuarioDelNuevoEmpleado() throws SQLException, IllegalArgumentException{
        final int NUM_EMPLEADO = Integer.parseInt(this.textFieldNumeroEmpleado.getText());
        final String ID_USUARIO_EMPLEADO = "EMPLEADO"+NUM_EMPLEADO;
        Usuario usuarioDelNuevoEmpleado = new Usuario();
        usuarioDelNuevoEmpleado.setIdUsuario(ID_USUARIO_EMPLEADO);
        usuarioDelNuevoEmpleado.setNombre(this.textFieldNombres.getText());
        usuarioDelNuevoEmpleado.setApellidoPaterno(this.textFieldApellidoP.getText());
        usuarioDelNuevoEmpleado.setApellidoMaterno(this.textFieldApellidoM.getText());
        usuarioDelNuevoEmpleado.setCalle(this.textFieldCalle.getText());
        usuarioDelNuevoEmpleado.setNumero(this.textFieldNumero.getText());
        usuarioDelNuevoEmpleado.setColonia(this.textFieldColonia.getText());
        usuarioDelNuevoEmpleado.setMunicipio(this.textFieldMunicipio.getText());
        usuarioDelNuevoEmpleado.setEmail(this.textFieldEmail.getText());
        usuarioDelNuevoEmpleado.setTelefono(this.textFieldTelefono1.getText());
        usuarioDelNuevoEmpleado.setTipoUsuario("EMPLEADO");
        usuarioDelNuevoEmpleado.validar();
        
        return usuarioDelNuevoEmpleado;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Utilidades.mostrarAlertaConfirmacion(
            "Cancelar operación", 
            "¿Está seguro de que desea cancelar la operación?", 
            Alert.AlertType.CONFIRMATION);
        boolean confirmarCancelacion =
            Utilidades.getOption().orElse(ButtonType.CANCEL).getButtonData().isDefaultButton();
        cerrar(confirmarCancelacion);
    }

    private void cerrar(boolean confirmacion){
        if (confirmacion == true){
            Stage stage = (Stage)buttonCancelar.getScene().getWindow();
            stage.close();   
        }
    }
    
    
    
}
