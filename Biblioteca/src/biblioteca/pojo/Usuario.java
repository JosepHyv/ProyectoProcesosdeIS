/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.pojo;

import java.sql.SQLException;

/**
 *
 * @author ale71
 */
public class Usuario implements Validable{
    private String idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String calle;
    private String numero;
    private String colonia;
    private String municipio;
    private String email;
    private String telefono;
    private String tipoUsuario;

    public Usuario() {
        this.idUsuario="sinIdUsuario";
        this.nombre="sinNombre";
        this.apellidoPaterno="sinApellidoPaterno";
        this.apellidoMaterno="sinApellidoMaterno";
        this.calle="sinCalle";
        this.numero="sinNumero";
        this.colonia="sinColonia";
        this.municipio="sinMunicipio";
        this.email="sinEmail";
        this.telefono="123";
        this.tipoUsuario="sinTipoUsuario";
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getColonia() {
        return colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return 
            "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + 
            ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + 
            apellidoMaterno + ", calle=" + calle + ", numero=" + numero + 
            ", colonia=" + colonia + ", municipio=" + municipio + ", email=" + 
            email + ", telefono=" + telefono + ", tipoUsuario=" + tipoUsuario + '}';
    }
    
    @Override
    public boolean equals(Object objeto) {
        boolean isEquals=false;
        if (objeto == this) {
            isEquals=true;
        }
        if (objeto!= null && objeto instanceof Usuario) {
            Usuario otro = (Usuario) objeto;
            isEquals=
                this.idUsuario==otro.idUsuario&& 
                this.nombre.equals(otro.nombre)  && 
                this.apellidoPaterno.equals(otro.apellidoPaterno) &&
                this.apellidoMaterno.equals(otro.apellidoPaterno);
        }
        return isEquals;
    }

    @Override
    public void validar() throws SQLException, IllegalArgumentException {
        new ValidacionServant().validarUsuario(this);
    }
    
}
