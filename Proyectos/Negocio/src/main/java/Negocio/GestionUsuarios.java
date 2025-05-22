/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOS.usuarioDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author USER
 */
public class GestionUsuarios {

    private static List<usuarioDTO> listaUsuarios = new ArrayList();

    private static final List<String> CORREOS_ADMIN = Arrays.asList(
            "admin@mitas.com",
            "admin2@mitas.com"
    );

    static {
        listaUsuarios.add(new usuarioDTO("Usuario", " prueba", "ado@gmail.com", "123"));
        listaUsuarios.add(new usuarioDTO("Admin", "Principal", "admin@mitas.com", "adminpass"));
         
         
    
        System.out.println("Usuarios iniciales cargados en GestionUsuarios. Total: " + listaUsuarios.size());
        System.out.println("Correos de administradores definidos: " + CORREOS_ADMIN);
    }
    public static List<usuarioDTO> getListaUsuario(){
        return listaUsuarios;
    }
    public static boolean agregarUsuario(usuarioDTO usuario){
        if(usuario == null  || usuario.getCorreoElectronico() == null){
            System.err.println("eror intento agregar un usuario nulo o sin correo");
            return  false;
        }
        for (usuarioDTO u : listaUsuarios) {
            if(u.getCorreoElectronico().equalsIgnoreCase(usuario.getCorreoElectronico())){
                System.err.println("Error: El correo electronico '" + usuario.getCorreoElectronico() + "' ya esta registrado.");
                return false;
            }
        }
        try {
            listaUsuarios.add(usuario);
            System.out.println("Usuario agregado : "+ usuario.getCorreoElectronico());
            return true;
        } catch (Exception e) {
            System.err.println("error al agregar el usuario: " + e.getMessage());
            return false;
        }
    }
    public static boolean esAdmin(String correo){
        if (correo == null) {
            return false;
        }
        boolean isAdmin = CORREOS_ADMIN.contains(correo.toLowerCase());
        System.out.println("verificando si " + correo + "es admin " + isAdmin);
        return isAdmin;
    }
}
