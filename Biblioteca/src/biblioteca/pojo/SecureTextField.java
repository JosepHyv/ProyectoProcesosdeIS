/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.pojo;

import biblioteca.pojo.constantes.ConstanteCaracteresLegales;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author ale71
 */
public class SecureTextField extends TextField{
private TextField textField;
private String nombreDelCampo;
private int maximoCaracteres;
private int minimoCaracteres;

    public SecureTextField(){
        textField = ((TextField)this);
        maximoCaracteres = 0;
        minimoCaracteres = 1;
    }
    
    public void setMaximoCaracteres(int maximoCaracteres){
        this.maximoCaracteres=maximoCaracteres;
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String prievioValor, String nuevoValor) {
                if (textField.getText().length()>=maximoCaracteres){
                    String string = textField.getText().substring(0,maximoCaracteres);
                    textField.setText(string);
                }
            }
        });
    }
    
    public void setCaracteresPermitidos(String caracteresPermitidos){
        this.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable, String prievioValor, String nuevoValor) {
                if (!nuevoValor.matches(caracteresPermitidos) ){
                    if (textField.getText().length()!=maximoCaracteres)
                        textField.setText(nuevoValor.replaceAll("[^"+caracteresPermitidos+"]", ""));
                    else
                        textField.setText(nuevoValor.substring(0,maximoCaracteres));
                }
            }
        });
    }
    
    public TextField getTextField() {
        return textField;
    }

    public void setNombreDelCampo(String nombreDelCampo) {
        this.nombreDelCampo = nombreDelCampo;
    }
    
    public void setMinimoCaracteres(int minimoCaracteres) {
        this.minimoCaracteres = minimoCaracteres;
    }

    public void fallarSiFaltanCaracteres() throws IllegalArgumentException{
        if ((this.textField.getText().length()!=this.minimoCaracteres)){
            throw new IllegalArgumentException("Faltan caracteres en el campo "+this.nombreDelCampo);
        }
    }
    
    public void fallarSiEstaEnBlanco() throws IllegalArgumentException{
        if ((this.textField.getText().trim().length() > 0) == false){
            throw new IllegalArgumentException("No puede dejar ningún campo vacío");
        }
    }
    
    
}
