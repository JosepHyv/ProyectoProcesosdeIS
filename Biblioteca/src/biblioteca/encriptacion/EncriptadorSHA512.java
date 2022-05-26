/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.encriptacion;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ale71
 */
public class EncriptadorSHA512 {
    public String encriptarCadena(String contraseniaTextoPlano){
        String contraseniaEncriptada= null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(contraseniaTextoPlano.getBytes("utf8"));
            contraseniaEncriptada = String.format("%0128x", new BigInteger(1, digest.digest())); 
        } catch (NoSuchAlgorithmException nsaException) {
            Logger.getLogger(EncriptadorSHA512.class.getName()).log(Level.SEVERE, null, nsaException);
        } catch (UnsupportedEncodingException ueException) {
            Logger.getLogger(EncriptadorSHA512.class.getName()).log(Level.SEVERE, null, ueException);
        }
        return contraseniaEncriptada;
    } 
}
