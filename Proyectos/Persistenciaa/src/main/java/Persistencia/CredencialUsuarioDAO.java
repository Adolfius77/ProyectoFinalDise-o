/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.CredencialUsuario;
import expciones.ErrorDatosException;
import java.util.Optional;

/**
 *
 * @author emiim
 */
public interface CredencialUsuarioDAO {
    void guardar(CredencialUsuario credencial) throws ErrorDatosException;
    Optional<CredencialUsuario> buscarPorNombreUsuario(String nombreUsuario) throws ErrorDatosException;
    void actualizarContrasena(String nombreUsuario, String nuevaContrasenaHasheada) throws ErrorDatosException;
    void eliminarPorUsuarioId(Long usuarioId) throws ErrorDatosException;
}
