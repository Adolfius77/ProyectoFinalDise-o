/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Objects;

/**
 *
 * @author emiim
 */

// Representa el dto de inicio de sesion
public class CredencialUsuario {
    private Long id;
    private String nombreUsuario;
    private String contrasenaHasheada;
    private Long usuarioId;

    public CredencialUsuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenaHasheada() {
        return contrasenaHasheada;
    }

    public void setContrasenaHasheada(String contrasenaHasheada) {
        this.contrasenaHasheada = contrasenaHasheada;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

     @Override
    public boolean equals(Object o) { /* Basado en id o nombreUsuario */
       if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredencialUsuario that = (CredencialUsuario) o;
        return Objects.equals(id, that.id) || (id == null && Objects.equals(nombreUsuario, that.nombreUsuario));
     }
    @Override
    public int hashCode() { return Objects.hash(id != null ? id : nombreUsuario); }
    @Override
    public String toString() { return "CredencialUsuario{id=" + id + ", nombreUsuario='" + nombreUsuario + '\'' + '}'; } // No mostrar hash
}
