/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.pojo.constantes;

/**
 *
 * @author ale71
 */
public enum ConstanteTiposDeContratacion {
    AUXILIAR("Auxiliar",1), ENCARGADO("Encargado",2), JEFE("Jefe",3);
    
    private String nombreTipoDeContratacion;
    private int idTipoDeContratacion;

    private ConstanteTiposDeContratacion(String nombreTipoDeContratacion, int idTipoDeContratacion) {
        this.nombreTipoDeContratacion = nombreTipoDeContratacion;
        this.idTipoDeContratacion = idTipoDeContratacion;
    }

    public String getNombreTipoDeContratacion() {
        return nombreTipoDeContratacion;
    }

    public void setNombreRol(String nombreTipoDeContratacion) {
        this.nombreTipoDeContratacion = nombreTipoDeContratacion;
    }

    public int getIdTipoDeContratacion() {
        return idTipoDeContratacion;
    }

    public void setIdRol(int idTipoDeContratacion) {
        this.idTipoDeContratacion = idTipoDeContratacion;
    }
    
    public String[] getNombresTiposDeContratacion(){
        String[] nombresTiposDeContratacion = {
            this.AUXILIAR.getNombreTipoDeContratacion(),
            this.ENCARGADO.getNombreTipoDeContratacion(),
            this.JEFE.getNombreTipoDeContratacion()
        };
        return nombresTiposDeContratacion;
    }
}
