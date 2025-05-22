/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementaciones;

import Modelo.CredencialUsuario;
import Persistencia.CredencialUsuarioDAO;
import expciones.ErrorDatosException;
import java.util.Optional;

/**
 *
 * @author emiim
 */
public class CredencialUsuarioDAOImpl implements CredencialUsuarioDAO{

    @Override
    public void guardar(CredencialUsuario credencial) throws ErrorDatosException {
        if (credencial == null || credencial.getNombreUsuario() == null || credencial.getContrasenaHasheada() == null) {
            throw new ErrorDatosException("Credencial, nombre de usuario o contraseña hasheada nulos");
        }
        System.out.println("Guardando credencial para usuario: " + credencial.getNombreUsuario() + " (Hash: " + credencial.getContrasenaHasheada().substring(0, 10) + "...)");
    }

    @Override
    public Optional<CredencialUsuario> buscarPorNombreUsuario(String nombreUsuario) throws ErrorDatosException {
        System.out.println("Buscando credencial por nombre de usuario: " + nombreUsuario);
        // Aquí iría la lógica real de SELECT ... WHERE nombre_usuario = ?
        // Devolvería la entidad con el hash almacenado.
        return Optional.empty(); // Placeholder
    }

    @Override
    public void actualizarContrasena(String nombreUsuario, String nuevaContrasenaHasheada) throws ErrorDatosException {
        if (nombreUsuario == null || nuevaContrasenaHasheada == null) {
             throw new ErrorDatosException("Nombre de usuario o nueva contraseña hasheada nulos");
         }
        System.out.println("Actualizando contraseña hasheada para usuario: " + nombreUsuario);
    }

    @Override
    public void eliminarPorUsuarioId(Long usuarioId) throws ErrorDatosException {
        if (usuarioId == null) {
            throw new ErrorDatosException("Usuario ID nulo para eliminar credencial");
        }
         System.out.println("Eliminando credencial(es) para Usuario ID: " + usuarioId);
    }
    
}
