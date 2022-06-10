/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.pojo;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author ale71
 */
public class Prestamo extends Usuario implements Validable{
    private int idPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String idUsuario;
    private int  idLibro;
 
    public Prestamo() {
        this.idPrestamo=0;
        this.fechaPrestamo=LocalDate.now();
        this.idUsuario = "empty";
        this.idLibro = 0;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }
        
    public LocalDate getFechaPrestamo()
    {
        return fechaPrestamo;
    }
    
    public LocalDate getFechaDevolucion()
    {
        return fechaDevolucion;
    }
    
    
    public String getIdUsuario()
    {
        return idUsuario;
    }
    public int getIdLibro()
    {
        return this.idLibro;
    }
    
    
    
    public void setIdPrestamo(int id)
    {
        this.idPrestamo = id;
    }
    
    public void setIdUsuario(String id)
    {
        this.idUsuario = id;
    }
    
    public void setIdLibro(int id)
    {
        this.idLibro = id;
    }
    public void setFechaPrestamo(LocalDate fechita)
    {
        this.fechaPrestamo = fechita;
    }
    public void setFechaDevolucion(LocalDate fechita)
    {
        this.fechaDevolucion = fechita;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "idPrestamo =" + idPrestamo + " ,\n fechaPrestamo =  " + fechaPrestamo + 
                " ,\n fechaDevolucion = " + fechaDevolucion + " , \n idUsuario = " + idUsuario + " ,\n idLibro = " +
                idLibro + " }";
    }

    @Override
    public boolean equals(Object objeto) {
        boolean isEquals = false;
        if (objeto == this) {
            isEquals=true;
        }
        if (objeto!= null && objeto instanceof Prestamo) {
            Prestamo otro = (Prestamo) objeto;
            isEquals=
                super.equals(objeto) &&
                this.idPrestamo == otro.idPrestamo && 
                this.idUsuario.equals(otro.idUsuario)  && 
                this.idLibro == otro.idLibro;
        }
        return isEquals;
    }
    
    @Override
    public void validar() throws SQLException, IllegalArgumentException{
        new ValidacionServant().validarEmpleado(this);
    }
    
}
