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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private String numEmpleado;
    private String tipoContratacion;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno ;
    private String curp;
    private String nss;
    private String rfc;
    private LocalDate fechaNacimiento;
    private String calle;
    private String numero;
    private String colonia;
    private String municipio;
    private String email;
    private String telefono1;
    private String telefono2;

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
        inicializarComboBoxTipoContratacion();
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
            obtenerEntradas();
            validarEntradas();
            //registrarEmpleado();
            Utilidades.mostrarAlertaSinConfirmacion(
                "Aviso", 
                "La información se ha guardado correctamente",
                Alert.AlertType.INFORMATION);
            terminar();
        } catch (SQLException sqlException) {
            Utilidades.mensajePerdidaDeConexion();
        } catch (IllegalArgumentException iaException){
            Utilidades.mostrarAlertaConfirmacion(
                "Aviso", 
                iaException.getMessage(), 
                Alert.AlertType.WARNING
            );
        }
    }
    
    private void terminar(){
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
    }
    
    private void registrarEmpleado() throws SQLException{
        Usuario usuarioDelNuevoEmpleado = crearUsuarioDelNuevoEmpleado();
        new UsuarioDAO().registrarUsuario(usuarioDelNuevoEmpleado);
        Empleado nuevoEmpleado = crearNuevoEmpleado(usuarioDelNuevoEmpleado.getIdUsuario());
        new EmpleadoDAO().registrarEmpleado(nuevoEmpleado);
    }
    
    private Empleado crearNuevoEmpleado(String idUsuario){
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNumEmpleado(Integer.parseInt(this.numEmpleado));
        nuevoEmpleado.setFechaNacimiento(this.fechaNacimiento);
        nuevoEmpleado.setNss(this.nss);
        nuevoEmpleado.setCurp(this.curp);
        nuevoEmpleado.setTelefonoEmpleado(this.telefono2);
        nuevoEmpleado.setContrasenia(this.curp);
        nuevoEmpleado.setTipoContratacion(
            this.comboBoxTipoContratacion.getSelectionModel().getSelectedItem().toString()
        );
        nuevoEmpleado.setIdUsuario(idUsuario);
        return nuevoEmpleado;
    }
    
    private Usuario crearUsuarioDelNuevoEmpleado(){
        final int NUM_EMPLEADO = Integer.parseInt(this.numEmpleado);
        final String ID_USUARIO_EMPLEADO = "EMPLEADO_"+NUM_EMPLEADO;
        Usuario usuarioDelNuevoEmpleado = new Usuario();
        usuarioDelNuevoEmpleado.setIdUsuario(ID_USUARIO_EMPLEADO);
        usuarioDelNuevoEmpleado.setNombre(this.nombre);
        usuarioDelNuevoEmpleado.setApellidoPaterno(this.apellidoPaterno);
        usuarioDelNuevoEmpleado.setApellidoMaterno(this.apellidoMaterno);
        usuarioDelNuevoEmpleado.setCalle(this.calle);
        usuarioDelNuevoEmpleado.setNumero(this.numero);
        usuarioDelNuevoEmpleado.setColonia(this.colonia);
        usuarioDelNuevoEmpleado.setMunicipio(this.municipio);
        usuarioDelNuevoEmpleado.setEmail(this.email);
        usuarioDelNuevoEmpleado.setTelefono(this.telefono1);
        usuarioDelNuevoEmpleado.setTipoUsuario("EMPLEADO");
        return usuarioDelNuevoEmpleado;
    }
    
    private void validarCamposLlenos(){
        boolean losCamposEstanLlenos =
            !this.numEmpleado.isEmpty()&&
            !this.tipoContratacion.isEmpty() &&
            !this.nombre.isEmpty()&&
            !this.apellidoPaterno.isEmpty()&&
            !this.apellidoMaterno.isEmpty()&&
            !this.curp.isEmpty()&&
            !this.nss.isEmpty() &&
            !this.rfc.isEmpty() &&
             this.fechaNacimiento!=null &&
            !this.calle.isEmpty()&&
            !this.numero.isEmpty() &&
            !this.colonia.isEmpty()&&
            !this.municipio.isEmpty()&&
            !this.email.isEmpty() &&
            !this.telefono1.isEmpty()&&
            !this.telefono2.isEmpty();
        if (losCamposEstanLlenos == false){
            throw new IllegalArgumentException("No puede dejar ningún campo vacío");
        }
    }

    private void validarEntradas() throws SQLException, IllegalArgumentException{ 
        validarCamposLlenos();
        final String CARACTERES_NUMERICOS = "\\d*";
        final String CARACTERES_ALFABETICOS = "[a-zA-ZáéíóúÁÉÍÓÚ ]+";
        final String CARACTERES_ALFANUMERICOS = "[\\d*a-zA-ZáéíóúÁÉÍÓÚ ]+";
        validarCaracteres(this.numEmpleado, "Número de empleado", CARACTERES_NUMERICOS);
        validarCaracteres(this.nombre,"Nombre",CARACTERES_ALFABETICOS);
        validarCaracteres(this.apellidoPaterno,"Apellido paterno",CARACTERES_ALFABETICOS);
        validarCaracteres(this.apellidoMaterno,"Apellido materno",CARACTERES_ALFABETICOS);
        validarCaracteres(this.curp,"CURP",CARACTERES_ALFANUMERICOS);
        validarCaracteres(this.nss,"NSS",CARACTERES_NUMERICOS);
        validarCaracteres(this.rfc,"RFC",CARACTERES_ALFANUMERICOS);
        validarCaracteres(this.calle,"Calle",CARACTERES_ALFANUMERICOS);
        validarCaracteres(this.numero,"Número",CARACTERES_ALFANUMERICOS);
        validarCaracteres(this.colonia,"Colonia",CARACTERES_ALFANUMERICOS);
        validarCaracteres(this.municipio,"Municipio",CARACTERES_ALFANUMERICOS);
        validarCaracteres(this.telefono1,"Teléfono 1",CARACTERES_NUMERICOS);
        validarCaracteres(this.telefono2,"Teléfono 2",CARACTERES_NUMERICOS);
    }
    
    private void obtenerEntradas(){
        this.numEmpleado = this.textFieldNumeroEmpleado.getText();
        this.tipoContratacion = this.comboBoxTipoContratacion.getSelectionModel().getSelectedItem();
        this.nombre = this.textFieldNombres.getText();
        this.apellidoPaterno = this.textFieldApellidoP.getText();
        this.apellidoMaterno = this.textFieldApellidoM.getText();
        this.curp = this.textFieldCurp.getText();
        this.nss = this.textFieldNss.getText();
        this.rfc = this.textFieldRfc.getText();
        this.fechaNacimiento = this.datePickerNacimiento.getValue();
        this.calle = this.textFieldCalle.getText();
        this.numero = this.textFieldNumero.getText();
        this.colonia = this.textFieldColonia.getText();
        this.municipio = this.textFieldMunicipio.getText();
        this.email = this.textFieldEmail.getText();
        this.telefono1 = this.textFieldTelefono1.getText();
        this.telefono2 = this.textFieldTelefono2.getText();
    }
    
    private void validarCaracteres
    (String campo, String nombreCampo, String caracteresAceptados)
    throws IllegalArgumentException{
        if (!campo.matches(caracteresAceptados)){
            throw new IllegalArgumentException("El campo "+nombreCampo+" contiene caracteres no permitidos.");
        }
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        System.out.println("asdf");
    }
    
}
