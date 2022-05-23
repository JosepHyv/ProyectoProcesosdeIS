/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.pojo;

import java.time.LocalDate;

/**
 *
 * @author ale71
 */
public class Empleado extends Usuario{
    private int numEmpleado;
    private LocalDate fechaNacimiento;
    private String nss;
    private String curp;
    private String telefonoEmpleado;
    private String contrasenia;
    private String tipoContratacion;
    private String idUsuario;

    public Empleado() {
        this.numEmpleado=-1;
        this.fechaNacimiento=LocalDate.now();
        this.nss="sinNSS";
        this.curp="sinCURP";
        this.telefonoEmpleado="sinTelefonoEmpleado";
        this.contrasenia="sinContrasenia";
        this.tipoContratacion="sinTipoContratacion";
        this.idUsuario="sinIdUsuario";
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNss() {
        return nss;
    }

    public String getCurp() {
        return curp;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
    
    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return 
            "Empleado{" + "numEmpleado=" + numEmpleado + ", fechaNacimiento=" + 
            fechaNacimiento + ", nss=" + nss + ", curp=" + curp + ", telefono=" + 
            telefonoEmpleado + ", contrasenia=" + contrasenia + ", tipoContratacion=" + 
            tipoContratacion + ", idUsuario=" + idUsuario + '}';
    }
    
    
    @Override
    public boolean equals(Object objeto) {
        boolean isEquals=false;
        if (objeto == this) {
            isEquals=true;
        }
        if (objeto!= null && objeto instanceof Empleado) {
            Empleado otro = (Empleado) objeto;
            isEquals=
                super.equals(objeto) &&
                this.numEmpleado==otro.numEmpleado && 
                this.nss.equals(otro.nss)  && 
                this.curp.equals(otro.curp) &&
                this.tipoContratacion.equals(otro.tipoContratacion);
        }
        return isEquals;
    }
    
}
