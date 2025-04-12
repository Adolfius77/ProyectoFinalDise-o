/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expciones;

/**
 *
 * @author emiim
 */

// Exception para cuando un dato no se pueda acceder
public class ErrorDatosException extends Exception {
     public ErrorDatosException(String message) {
        super(message);
    }
    public ErrorDatosException(String message, Throwable cause) {
        super(message, cause);
    }
}
