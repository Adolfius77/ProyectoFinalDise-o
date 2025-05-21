/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author riosr
 */
public class AgregarClienteDTO {
    private String idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String correoElectronico;
    private String contraseña;
    private String estado;

    public AgregarClienteDTO(String idCliente, String nombreCliente, String apellidoCliente, String correoElectronico, String contraseña, String estado) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.estado = estado;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "AgregarClienteDTO{" + "idCliente=" + idCliente + ", nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente + ", correoElectronico=" + correoElectronico + ", contrase\u00f1a=" + contraseña + ", estado=" + estado + '}';
    }
    
    
}
