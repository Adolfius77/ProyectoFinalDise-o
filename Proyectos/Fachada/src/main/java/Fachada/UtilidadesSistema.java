/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fachada;

import Modelo.Libro;

/**
 *
 * @author riosr
 */
public class UtilidadesSistema {
     public boolean validarDatos(Libro libro) {
        return libro.getTitulo() != null && !libro.getTitulo().isEmpty();
    }

    public void logTransaccion(String mensaje) {
        System.out.println("Se valido " + mensaje);
    }
}
