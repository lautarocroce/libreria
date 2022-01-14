/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPRING.Ejercicio_01_libreria.Errores;

/**
 *
 * @author Homero
 */
public class ErrorService extends Exception {

    public ErrorService(String mensaje) {
        super(mensaje);
    }

}
