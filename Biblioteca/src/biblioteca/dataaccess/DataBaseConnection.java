package biblioteca.dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnection {
    private Connection conexion;   
    
    public Connection getConexion() throws SQLException{
        conectar();
        return conexion;
    }
    public void conectar() throws SQLException {
        try {
            FileInputStream archivoConfiguracion = new FileInputStream(new File("C:\\Users\\ale71\\proyecto2\\ProyectoProcesosdeIS\\Biblioteca\\src\\biblioteca\\dataaccess\\dbconfig.txt"));
            Properties atributos = new Properties();
            atributos.load(archivoConfiguracion);
            archivoConfiguracion.close();
            Class.forName("java.sql.Driver");
            String direccionBD = atributos.getProperty("DireccionBD");
            String usuario = atributos.getProperty("Usuario");
            String contrasenia = atributos.getProperty("Contrasenia");
            conexion = DriverManager.getConnection(direccionBD, usuario, contrasenia);
        } catch (FileNotFoundException fnfException) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, fnfException);
        } catch (IOException ioException){
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ioException);
        } catch (ClassNotFoundException cnfException) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, cnfException);
        } 
    }
    public void desconectar() throws SQLException{
        if (conexion!=null){
            if (!conexion.isClosed()){
                conexion.close();
            }
        }
    }
}


