/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author USER
 */
public class usuarioDTO {
    private long id;
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String contrasena;
    private boolean activo;
    private String estado;

    public usuarioDTO(long id, String nombres, String apellidos, String correoElectronico, String contrasena, boolean activo, String estado) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.id = id;
        this.activo = activo;
        this.estado = activo ? "activo" : "inactivo";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "usuarioDTO{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", correoElectronico=" + correoElectronico + ", contrasena=" + contrasena + ", activo=" + activo + ", estado=" + estado + '}';
    }

    
   
    
}
