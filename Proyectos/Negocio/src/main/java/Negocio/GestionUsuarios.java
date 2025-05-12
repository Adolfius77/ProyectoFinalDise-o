/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOS.usuarioDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class GestionUsuarios {
    private static List<usuarioDTO> listaUsuarios = new ArrayList();
    
    public static  List<usuarioDTO> getListaUsuarios(){
        return listaUsuarios;
    }
   public static boolean agregarUsuario(usuarioDTO usuario) {
    try {
        
        listaUsuarios.add(usuario);
        return true; 
    } catch (Exception e) {
     
        System.err.println("Error al agregar usuario: " + e.getMessage());
        return false; 
    }
}
}
