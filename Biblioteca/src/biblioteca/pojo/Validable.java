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
public interface Validable {
    public void validar() throws SQLException, IllegalArgumentException;
}
