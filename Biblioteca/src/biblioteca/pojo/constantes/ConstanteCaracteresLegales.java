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
    NUMERICOS("\\d*"), 
    ALFABETICOS_VALIDACION("[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+"),
    ALFABETICOS_TEXTFIELD("a-zA-ZñÑáéíóúÁÉÍÓÚ +"),
    ALFANUMERICOS_VALIDACION("[\\d*a-zA-ZáéíóúÁÉÍÓÚ ]+"),
    ALFANUMERICOS_TEXTFIELD("\\d*a-zA-ZáéíóúÁÉÍÓÚ +"),
    VACIO(".*\\w.*"),
    EMAIL("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    
    private String caracteres;

    private ConstanteCaracteresLegales(String caracteres) {
        this.caracteres = caracteres;
    }

    public String getCaracteres() {
        return this.caracteres;
    }
}
