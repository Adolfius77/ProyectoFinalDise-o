/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOS.LibroDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class BoProductos {
    private static List<LibroDTO> listaDelibros = new ArrayList<>();
    
    public static boolean agregarLibro(LibroDTO libro){
        if(libro != null && !libro.getTitulo().isEmpty() && libro.getIsbn().isEmpty()){
            listaDelibros.add(libro);
            System.out.println("libro agregado correctamente "+ libro);
            return true;
        }else{
            System.out.println("error el libro no puede ser nulo o debe tener un titulo o isbn");
            return false;
        }
    }
    public static  List<LibroDTO> obetenerLibrosAgregados(){
        return new ArrayList<>(listaDelibros);
    }
}
