/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapters;

import DTOS.DTOInicioSesion;
import Modelo.CredencialUsuario;

/**
 *
 * @author emiim
 */
public class InicioSesionAdapter {
    public CredencialUsuario toEntityForStorage(DTOInicioSesion dto, Long usuarioId) {
        if (dto == null) return null;

        CredencialUsuario entidad = new CredencialUsuario();
        entidad.setNombreUsuario(dto.getNombreUsuario());
        entidad.setUsuarioId(usuarioId);
        // String hash = passwordEncoder.encode(dto.getConstraseña());
        // entidad.setContrasenaHasheada(hash);
        entidad.setContrasenaHasheada("HASH_DE(" + dto.getConstraseña() + ")"); // ¡¡EJEMPLO, USAR LIBRERIA REAL!!
        return entidad;
    }

    public DTOInicioSesion toDto(CredencialUsuario entidad) {
        if (entidad == null) return null;
        return new DTOInicioSesion(entidad.getNombreUsuario(), null); // Contraseña siempre null
    }
}
