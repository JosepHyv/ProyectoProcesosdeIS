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
public enum ConstanteCaracteresLegales {
    NUMERICOS("\\d*"), ALFABETICOS("[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+"),
    ALFANUMERICOS("[\\d*a-zA-ZáéíóúÁÉÍÓÚ ]+"),VACIO(".*\\w.*");
    
    private String caracteres;

    private ConstanteCaracteresLegales(String caracteres) {
        this.caracteres = caracteres;
    }

    public String getCaracteres() {
        return this.caracteres;
    }
}
