/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.vistas;

import biblioteca.pojo.Empleado;
import biblioteca.pojo.InformacionSesion;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author ale71
 */
public class FXMLPanelPrincipalController implements Initializable {

    @FXML
    private Button buttonRegistrarUsuario;
    @FXML
    private Button buttonConsultarUsuarios;
    @FXML
    private Button buttonRegistrarLibro;
    @FXML
    private Button buttonConsultarCatalogoBibliotecario;
    @FXML
    private Button buttonRegistrarPrestamos;
    @FXML
    private Button buttonConsultarPrestamos;
    @FXML
    private Button buttonRegistrarDevolucion;
    @FXML
    private Button buttonRegistrarEmpleado;
    @FXML
    private Button buttonConsultarEmpleados;
    @FXML
    private Button buttonReportePrestamos;
    @FXML
    private Button buttonConsultarReporteDevolucionesTardias;
    @FXML
    private Button buttonLlenarFormatoCompraLibros;
    @FXML
    private MenuItem menuCerrarSesion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final String TIPO_CONTRATACION_EMPLEADO = 
                InformacionSesion.getInformacionSesion().getGlobalTipoContratacionDelEmpleado();
        final String TIPO_CONTRATACION_JEFE = "Jefe";
        if (TIPO_CONTRATACION_EMPLEADO.equals(TIPO_CONTRATACION_JEFE)){
            this.buttonRegistrarEmpleado.setVisible(true);
            this.buttonConsultarEmpleados.setVisible(true);
            this.buttonConsultarReporteDevolucionesTardias.setVisible(true);
            this.buttonLlenarFormatoCompraLibros.setVisible(true);
            this.buttonReportePrestamos.setVisible(true);
        }
    }    

    
    @FXML
    private void llamarRegistrarUsuario(ActionEvent event) {
        System.out.println(InformacionSesion.getInformacionSesion().getGlobalTipoContratacionDelEmpleado());
        Parent root;
        try {
            URL url = new File("src/biblioteca/vistas/FXMLRegistroDeUsuario.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            root = loader.load();
            FXMLRegistroDeUsuarioController controller =
                loader.getController();
            Stage subStage = new Stage();
            Stage stageActual = (Stage)buttonRegistrarUsuario.getScene().getWindow();
            subStage.initOwner(stageActual);
            subStage.initModality(Modality.APPLICATION_MODAL);
            subStage.setTitle("Dar Usuario de Alta");
            subStage.setScene(new Scene(root, 620, 400));
            subStage.showAndWait();
        } catch (IOException ioException) {
            Utilidades.mensajeErrorAlCargarLaInformacionDeLaVentana();
        } catch(IllegalStateException isException){
            Utilidades.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    @FXML
    private void llamarConsultarUsuarios(ActionEvent event) {
    }

    @FXML
    private void llamarRegistrarLibro(ActionEvent event) {
        
    }

    @FXML
    private void llamareConsultarCatalogoBibliotecario(ActionEvent event) {
    }

    @FXML
    private void llamarRegistrarPrestamos(ActionEvent event) throws MalformedURLException {
        URL url = new File("src/biblioteca/vistas/FXMLRegistrarPrestamo.fxml").toURI().toURL();
        llamarSubventana("Registrar prestamo", url);
    }

    @FXML
    private void llamarConsultarPrestamos(ActionEvent event) {
        
    }

    @FXML
    private void llamarRegistrarDevolucion(ActionEvent event) {
    }

    @FXML
    private void llamarRegistrarEmpleado(ActionEvent event) throws MalformedURLException {
        URL url = new File("src/biblioteca/vistas/FXMLRegistroDeEmpleados.fxml").toURI().toURL();
        llamarSubventana("Registro de Empleados", url);
    }

    @FXML
    private void llamarConsultarEmpleados(ActionEvent event) {
    }

    @FXML
    private void llamarConsultarReportePrestamos(ActionEvent event) {
    }

    @FXML
    private void llamarConsultarReporteDevolucionesTardias(ActionEvent event) {
    }

    @FXML
    private void llamarLlenarFormatoCompraLibros(ActionEvent event) {
    }
    
    private void llamarSubventana(String titulo, URL rutaDeVentana){ 
        FXMLLoader loader = new FXMLLoader(rutaDeVentana);
        try {
            Parent root = loader.load();
            Scene escenaFormulario = new Scene(root);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setResizable(false);
            escenarioFormulario.setScene(escenaFormulario);
            escenarioFormulario.setTitle(titulo);
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.showAndWait();
        } catch(IOException ioException){
            ioException.printStackTrace();
            Utilidades.mensajeErrorAlCargarLaInformacionDeLaVentana();
        } catch(IllegalStateException isException){
            Utilidades.mensajeErrorAlCargarLaInformacionDeLaVentana();
            isException.printStackTrace();
        }
    }

    @FXML
    private void confirmarCierreDeSesion(ActionEvent event) {
    }
}
