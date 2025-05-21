/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author riosr
 */
public class ConsultarClienteDTO {
    private String idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String correoElectronico;

    public ConsultarClienteDTO(String idCliente, String nombreCliente, String apellidoCliente, String correoElectronico) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.correoElectronico = correoElectronico;
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

    @Override
    public String toString() {
        return "ConsultarClienteDTO{" + "idCliente=" + idCliente + ", nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente + ", correoElectronico=" + correoElectronico + '}';
    }
}
