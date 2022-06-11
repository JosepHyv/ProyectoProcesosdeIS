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

//pojos (pollos :p)
import biblioteca.pojo.Usuario;
import biblioteca.pojo.Prestamo;
import biblioteca.pojo.Libro;

//Daos (conectores a la BD :p)
import biblioteca.modelo.UsuarioDAO;
import biblioteca.modelo.PrestamoDAO;
import biblioteca.modelo.LibroDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;

//JDK / STL
import java.util.ArrayList;
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
    
    private ArrayList<Integer> LIBROS_DISPONIBLES;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LocalDate nuevaFecha = this.CURRENT_DATE.plusDays(this.MIN_DIAS);
        dpDevolucion.setValue(nuevaFecha);
    }    

    @FXML
    private void btAceptar(ActionEvent event) {
        if(validarCampos())
        {   
            boolean  registroNuevo = true;
            try
            {
                LibroDAO conexionLibro = new LibroDAO();
                PrestamoDAO conexionPrestamo = new PrestamoDAO();
                UsuarioDAO conexionUsuario = new UsuarioDAO();
                
                //System.out.println("Intentando recuperar el libro " + tfNombreLibro.getText());
                //System.out.println(split(tfNombreLibro.getText()));
                //System.out.println("Intentando recuperar el Usuario " + tfMatricula.getText());
                Usuario usuarioChido = conexionUsuario.getUsuarioPorId(tfMatricula.getText());
                
                //System.out.println("Pase la conexion de libros y de usuarios");
                
                Prestamo nuevoPrestamo = new Prestamo();
                nuevoPrestamo.setIdPrestamo(conexionPrestamo.getLastIdPrestamo());
                //System.out.println("pase el getLastIde " + nuevoPrestamo.getIdPrestamo());
                nuevoPrestamo.setFechaPrestamo(CURRENT_DATE);
                nuevoPrestamo.setFechaDevolucion(dpDevolucion.getValue());
                nuevoPrestamo.setIdUsuario(usuarioChido.getIdUsuario());
                //Debug
                for(Integer i: this.LIBROS_DISPONIBLES)
                    System.out.println("Libros Disponibles: " + i);
                nuevoPrestamo.setIdLibro(this.LIBROS_DISPONIBLES.get(0));
                
                //System.out.println("Prestamo id usuario " + nuevoPrestamo.getIdUsuario());
                //System.out.println("Prestamo id libro " + nuevoPrestamo.getIdLibro());
                
                conexionPrestamo.registrarPrestamo(nuevoPrestamo);
                //System.out.println("En el prestamo la conexion regreso: " + temp);
            }
            catch (SQLException ex) 
            {
                System.out.println(ex.getMessage());
                registroNuevo = false;
                Utilidades.mostrarAlertaConfirmacion("Error", "Ocurrio un error al registrar el prestamo", Alert.AlertType.ERROR);
            }
            if(registroNuevo)
            {
                Utilidades.mostrarAlertaSinConfirmacion("Aviso", "Prestamo Otorgado :3", Alert.AlertType.INFORMATION);
                limpiarVentana();
            }
        }
        else
            Utilidades.mostrarAlertaSinConfirmacion("Error", "Los Campos estan vacios o son inforrectos\nfavor deverificar", Alert.AlertType.ERROR);
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
    
    @FXML
    private void findDataDomicilio(MouseEvent event) {
        tfDomicilio.setText("");
        Usuario persona = new Usuario();
        UsuarioDAO connect = new UsuarioDAO();
        String idUsuario = tfMatricula.getText();
        try {
            if (connect.encontrarUsuarioPorIdUsuario(idUsuario)) {
                //for debbug
                //System.out.println("Encontre un usuario");
                //System.out.println("Encontre a: " + idUsuario);
                persona = connect.getUsuarioPorId(idUsuario);
                String domicilioUsuario = "";
                domicilioUsuario = persona.getCalle()
                        + " " + persona.getNumero()
                        + " " + persona.getColonia()
                        + " " + persona.getMunicipio();
                tfDomicilio.setText(domicilioUsuario);
                
            } else {
                //System.out.println("No encontre a: " + idUsuario);
            }
        } catch (SQLException ex) {
            
            Utilidades.mensajePerdidaDeConexion();
        }

    }
    
        
    private void cerrarVentana(boolean option)
    {
        if(option)
        {
            Stage stage = (Stage) btCancelar.getScene().getWindow();
            stage.close();
        }
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
    
    private boolean validarNombreLibro()
    {
        lbNombreLibro.setText("");
        boolean ok = true;
        String nombreLibro = split(tfNombreLibro.getText());
        LibroDAO connect = new LibroDAO();
        try 
        {
            if(!connect.encontrarLibroPorNombre(nombreLibro))
            {
                ok = false;
                lbNombreLibro.setText("El libro no existe");
                Utilidades.mostrarAlertaConfirmacion(
                            "Error", 
                            "El libro " + tfNombreLibro.getText() + "\n no existe" , Alert.AlertType.ERROR);
            }
        } catch (SQLException ex) {
            ok = false;
            Utilidades.mensajePerdidaDeConexion();
            
        }
            
        return ok;
    }
    
    private ArrayList<Integer> validarLibrosDisponibles()
    {
        boolean ok = true;
        ArrayList<Integer> listaLibro = new ArrayList<Integer>();
        ArrayList<Integer> listaPrestamo = new ArrayList<Integer>();
        ArrayList<Integer> librosDisponibles = new ArrayList<Integer>();
        try {
            LibroDAO temporal = new LibroDAO();
            listaLibro = temporal.getListaIdLibro(split(tfNombreLibro.getText()));
            if(!listaLibro.isEmpty())
            {
                for(Integer i : listaLibro)
                    System.out.println("ID Libro = " + i);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            ok = false;
            Utilidades.mensajePerdidaDeConexion();
        }
        
        if(ok && listaLibro.size() == 0)
        {
            ok = false;
        }
        if(ok && listaLibro.size() == 1)
        {
            ok = false;
        }
        
        if(ok)
        {
            try 
            {
                PrestamoDAO temporal = new PrestamoDAO();
                listaPrestamo = temporal.getIdLibrosEnPrestamo();
                if(!listaPrestamo.isEmpty())
                {
                    for(Integer i : listaPrestamo)
                        System.out.println("en prestamo ID Libro = " + i);
                }
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                ok = false;
                Utilidades.mensajePerdidaDeConexion();
            }
        }
        
        if(ok)
        {
            for(Integer x: listaLibro)
            {
                boolean existe = false;
                for(Integer y: listaPrestamo)
                    if(x == y)
                    {
                        existe = true;
                        break;
                    }
                if(!existe)
                    librosDisponibles.add(x);
            }
        }
        
        return librosDisponibles;
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
        else ok = validarFecha(dateOfdp) && ok ;
        
        
        //verfica si el usuario existe
        //que chafa es java, lo mejor era 
        // tfMatricula.getText().length() && tfDomicilio.getText().isEmpty()
        if(!tfMatricula.getText().isEmpty()  && tfDomicilio.getText().isEmpty())
        {
            ok = false;
            lbDomicilio.setText("El usuario no existe");
        }
        
        if(ok && !validarNombreLibro())
        {
            ok = false;
            lbNombreLibro.setText("El libro no existe");
        }
        
        if(ok)
        {
            this.LIBROS_DISPONIBLES = validarLibrosDisponibles();
            //ok = ok && !this.LIBROS_DISPONIBLES.isEmpty() && this.LIBROS_DISPONIBLES.size() > 1;
        }
        
        if(this.LIBROS_DISPONIBLES.size() <= 1)
        {
            ok = false;
            lbNombreLibro.setText("");
            lbNombreLibro.setText("solo queda 1 libro");
            Utilidades.mostrarAlertaConfirmacion("ERROR", "El prestamo no puede proceder\nsolo queda 1 copia del libro", Alert.AlertType.ERROR);
        }
        
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

    public String split(String line)
    {
        int pos = 0 ;
        int pos2 = line.length() -1;
        while(pos2 > pos && line.charAt(pos2) == ' ') pos2--;
        while(pos < pos2 && line.charAt(pos) == ' ') pos++;
        
        String ans = line.substring(pos, pos2+1);
     //   System.out.println(ans);
        return ans;
    }
    
    public void limpiarVentana()
    {
        this.tfDomicilio.setText("");
        this.tfMatricula.setText("");
        this.dpDevolucion.setValue(CURRENT_DATE.plusDays(this.MIN_DIAS));
        this.tfNombreLibro.setText("");
        this.lbDomicilio.setText("");
        this.lbFecha.setText("");
        this.lbMatricula.setText("");
        
    }
}
