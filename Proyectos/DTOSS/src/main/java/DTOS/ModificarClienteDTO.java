/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author riosr
 */
public class ModificarClienteDTO {
    private static final long serialversion = 301L;
    private long id;
    private String nombreCliente;
    private String apellidoCliente;
    private String correoElectronico;
    private String contraseña;
    private boolean activo;

    public ModificarClienteDTO(long id, String nombreCliente, String apellidoCliente, String correoElectronico, boolean activo, String contraseña) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.activo = activo;
    }
    
    public ModificarClienteDTO(long id, String nombreCliente, String apellidoCliente, String correoElectronico, boolean activo){
        this(id, nombreCliente, apellidoCliente, correoElectronico, activo, null);
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "ModificarClienteDTO{" + "nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente + ", correoElectronico=" + correoElectronico + ", contrase\u00f1a=" + contraseña + ", activo=" + activo + '}';
    }
   
    public static ModificarClienteDTO fromUsuarioDTO(usuarioDTO usuario){
        if(usuario == null){
            return null;
        }
        
        return new ModificarClienteDTO(
                usuario.getId(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getCorreoElectronico(),
                usuario.isActivo(),
                null
        );
    }
    
}
