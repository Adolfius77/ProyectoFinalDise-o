/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

import java.io.Serializable;

/**
 *
 * @author riosr
 */
public class ConsultarClienteDTO implements Serializable{
    private static final long serialversion = 201L;
    private long idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String correoElectronico;
    private boolean activo;
    private String estado;

    public ConsultarClienteDTO(long idCliente, String nombreCliente, String apellidoCliente, String correoElectronico, boolean activo, String estado) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.correoElectronico = correoElectronico;
        this.activo = activo;
        this.estado = activo ? "activo" : "inactivo";
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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

    
    public static ConsultarClienteDTO fromUsuarioDTO (usuarioDTO usuario){
        if(usuario == null){
            return null;
        }
        
        return new ConsultarClienteDTO(usuario.getId(), usuario.getNombres(), usuario.getApellidos(),usuario.getCorreoElectronico(), true, usuario.getEstado());
    }
    
    
    @Override
    public String toString() {
        return "ConsultarClienteDTO{" + "idCliente=" + idCliente + ", nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente + ", correoElectronico=" + correoElectronico + ", activo=" + activo + ", estado=" + estado + '}';
    }

    
    
}
