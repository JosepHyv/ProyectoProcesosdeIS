package biblioteca.dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path; // for general dir on any operating systems :3 my english is so bad :(
import java.nio.file.Paths; // same thing :3
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
            // usando directorios  generales for win ||  unix like systems
            
            Path CURRENT_FILE = Paths.get("");
            String directory = CURRENT_FILE.toAbsolutePath().toString();
            directory = Paths.get(directory, "src" , "biblioteca" , "dataaccess" , "dbconfig.txt").toString();
            System.err.println("File directory is " + directory); // err for debbug
            URL url = new File(directory).toURI().toURL();
            FileInputStream archivoConfiguracion = new FileInputStream(new File(url.getPath()));
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


