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
import biblioteca.pojo.SecureTextField;
import biblioteca.pojo.Usuario;
import biblioteca.pojo.constantes.ConstanteCaracteresLegales;
import biblioteca.pojo.constantes.ConstanteTiposDeContratacion;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;

/**
 * FXML Controller class
 *
 * @author ale71
 */
public class FXMLRegistroDeEmpleadosController extends Application implements Initializable {

    @FXML
    private ComboBox<String> comboBoxTipoContratacion;
    @FXML
    private SecureTextField textFieldNombres;
    @FXML
    private SecureTextField textFieldNss;
    @FXML
    private SecureTextField textFieldApellidoM;
    @FXML
    private SecureTextField textFieldCurp;
    @FXML
    private SecureTextField textFieldApellidoP;
    @FXML
    private SecureTextField textFieldRfc;
    @FXML
    private SecureTextField textFieldCalle;
    @FXML
    private SecureTextField textFieldNumero;
    @FXML
    private SecureTextField textFieldMunicipio;
    @FXML
    private SecureTextField textFieldColonia;
    @FXML
    private SecureTextField textFieldEmail;
    @FXML
    private SecureTextField textFieldTelefono1;
    @FXML
    private SecureTextField textFieldTelefono2;
    @FXML
    private Button buttonDarDeAlta;
    @FXML
    private Button buttonCancelar;
    @FXML
    private DatePicker datePickerNacimiento;
    @FXML
    private TextField textFieldNumeroEmpleado;
    @FXML
    private Group textFieldGroup;

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
            limitarTextFields();
            limitarDatePicker();
        } catch (SQLException sqlException) {
            Utilidades.mensajePerdidaDeConexion();
        } 
    }

    private void limitarDatePicker(){
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaDeNacimientoMinima = fechaActual.minusYears(18);
        this.datePickerNacimiento.setValue(fechaDeNacimientoMinima);
        this.datePickerNacimiento.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   setDisable(item.isAfter(fechaDeNacimientoMinima));
               }
           }
        );
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
            validarCamposEspecialesLlenos();
            validarCodigosDeIdentificacion();
            Usuario usuarioDelNuevoEmpleado = crearUsuarioDelNuevoEmpleado();
            Empleado nuevoEmpleado = crearNuevoEmpleado(usuarioDelNuevoEmpleado);
            
            registrarEmpleado(nuevoEmpleado);
            Utilidades.mostrarAlertaSinConfirmacion(
                "Aviso", "La información se ha guardado correctamente",
                Alert.AlertType.INFORMATION);
            terminar();
        }catch (SQLException sqlException) {
            Utilidades.mensajePerdidaDeConexion();            
        } catch (IllegalArgumentException iaException){
            Utilidades.mostrarAlertaConfirmacion(
                "Aviso", iaException.getMessage(), 
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
        this.textFieldNombres.fallarSiEstaEnBlanco();
        this.textFieldApellidoP.fallarSiEstaEnBlanco();
        this.textFieldApellidoM.fallarSiEstaEnBlanco();
        this.textFieldCurp.fallarSiEstaEnBlanco();
        this.textFieldNss.fallarSiEstaEnBlanco();
        this.textFieldRfc.fallarSiEstaEnBlanco();
        this.textFieldCalle.fallarSiEstaEnBlanco();
        this.textFieldNumero.fallarSiEstaEnBlanco();
        this.textFieldColonia.fallarSiEstaEnBlanco();
        this.textFieldMunicipio.fallarSiEstaEnBlanco();
        this.textFieldEmail.fallarSiEstaEnBlanco();
        this.textFieldTelefono1.fallarSiEstaEnBlanco();
        this.textFieldTelefono2.fallarSiEstaEnBlanco();
    }
    
    private void validarCamposEspecialesLlenos() throws IllegalArgumentException{
        final boolean TODOS_LOS_CAMPOS_ESTAN_LLENOS=
            this.datePickerNacimiento.getValue()!=null
            && !this.comboBoxTipoContratacion.getSelectionModel().isEmpty();
        if (TODOS_LOS_CAMPOS_ESTAN_LLENOS==false){
            throw new IllegalArgumentException("No puede dejar ningún campo vacío.");
        }
    }
    
    private void validarCodigosDeIdentificacion() throws IllegalArgumentException{
        this.textFieldCurp.fallarSiFaltanCaracteres();
        this.textFieldNss.fallarSiFaltanCaracteres();
        this.textFieldRfc.fallarSiFaltanCaracteres();
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
            "Cancelar operación", "¿Está seguro de que desea cancelar la operación?", 
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
    
    private void limitarTextFields(){
        this.textFieldNombres.setMaximoCaracteres(50);
        this.textFieldApellidoP.setMaximoCaracteres(50);
        this.textFieldApellidoM.setMaximoCaracteres(50);
        this.textFieldCalle.setMaximoCaracteres(100);
        this.textFieldNumero.setMaximoCaracteres(20);
        this.textFieldColonia.setMaximoCaracteres(50);
        this.textFieldMunicipio.setMaximoCaracteres(50);
        this.textFieldEmail.setMaximoCaracteres(200);
        this.textFieldTelefono1.setMaximoCaracteres(10);
        this.textFieldTelefono2.setMaximoCaracteres(10);
        
        this.textFieldCurp.setNombreDelCampo("CURP");
        this.textFieldCurp.setMaximoCaracteres(18);
        this.textFieldCurp.setMinimoCaracteres(18);
        this.textFieldNss.setNombreDelCampo("NSS");
        this.textFieldNss.setMaximoCaracteres(11);
        this.textFieldNss.setMinimoCaracteres(11);
        this.textFieldRfc.setNombreDelCampo("RFC");
        this.textFieldRfc.setMaximoCaracteres(13);
        this.textFieldRfc.setMinimoCaracteres(13);
        this.textFieldTelefono1.setNombreDelCampo("Telefono 1");
        this.textFieldTelefono2.setNombreDelCampo("Telefono 2");
        this.textFieldTelefono1.setMinimoCaracteres(10);
        this.textFieldTelefono2.setMinimoCaracteres(10);
        
        this.textFieldNombres.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFABETICOS_TEXTFIELD.getCaracteres());
        this.textFieldApellidoP.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFABETICOS_TEXTFIELD.getCaracteres());
        this.textFieldApellidoM.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFABETICOS_TEXTFIELD.getCaracteres());
        this.textFieldCalle.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFANUMERICOS_TEXTFIELD.getCaracteres());
        this.textFieldNumero.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFANUMERICOS_TEXTFIELD.getCaracteres());
        this.textFieldColonia.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFANUMERICOS_TEXTFIELD.getCaracteres());
        this.textFieldMunicipio.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFANUMERICOS_TEXTFIELD.getCaracteres());
        this.textFieldTelefono1.setCaracteresPermitidos(ConstanteCaracteresLegales.NUMERICOS.getCaracteres());
        this.textFieldTelefono2.setCaracteresPermitidos(ConstanteCaracteresLegales.NUMERICOS.getCaracteres());
        this.textFieldCurp.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFANUMERICOS_TEXTFIELD.getCaracteres());
        this.textFieldNss.setCaracteresPermitidos(ConstanteCaracteresLegales.NUMERICOS.getCaracteres());
        this.textFieldRfc.setCaracteresPermitidos(ConstanteCaracteresLegales.ALFANUMERICOS_TEXTFIELD.getCaracteres());
    }    
    
}
