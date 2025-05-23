package Negocio;

import DTOS.ConsultarClienteDTO;
import DTOS.ModificarClienteDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class GestionUsuarios {

    private static List<ConsultarClienteDTO> listaUsuarios = new ArrayList<>();
    private static final AtomicLong idCounter = new AtomicLong(System.currentTimeMillis());

   
    private static Map<String, String> almacenContrasenas = new HashMap<>();

    private static final List<String> CORREOS_ADMIN = Arrays.asList(
            "admin@mitas.com",
            "admin2@mitas.com"
    );

    static {
       
        ConsultarClienteDTO user1 = new ConsultarClienteDTO(idCounter.getAndIncrement(), "Usuario", "Prueba", "ado@gmail.com", true, "activo");
        listaUsuarios.add(user1);
        almacenContrasenas.put(user1.getCorreoElectronico().toLowerCase(), "123");

       
        ConsultarClienteDTO admin1 = new ConsultarClienteDTO(idCounter.getAndIncrement(), "Admin", "Principal", "admin@mitas.com", true, "activo");
        listaUsuarios.add(admin1);
        almacenContrasenas.put(admin1.getCorreoElectronico().toLowerCase(), "adminpass");

        System.out.println("Usuarios iniciales cargados en GestionUsuarios. Total: " + listaUsuarios.size());
        System.out.println("Correos de administradores definidos: " + CORREOS_ADMIN);
    }

  

    public static List<ConsultarClienteDTO> getListaUsuario() {
        return new ArrayList<>(listaUsuarios);
    }

  
    public static synchronized boolean agregarUsuarioYContrasena(ConsultarClienteDTO usuario, String contrasena) {
        if (usuario == null || usuario.getCorreoElectronico() == null || contrasena == null || contrasena.isEmpty()) {
            System.err.println("Error: Intento de agregar un usuario nulo, sin correo o sin contraseña.");
            return false;
        }
        for (ConsultarClienteDTO u : listaUsuarios) {
            if (u.getCorreoElectronico().equalsIgnoreCase(usuario.getCorreoElectronico())) {
                System.err.println("Error: El correo electrónico '" + usuario.getCorreoElectronico() + "' ya está registrado.");
                return false;
            }
        }
        try {
            if (usuario.getIdCliente() == 0) {
                 usuario.setIdCliente(idCounter.getAndIncrement());
            }
            listaUsuarios.add(usuario);
            almacenContrasenas.put(usuario.getCorreoElectronico().toLowerCase(), contrasena); // Guardar contraseña
            System.out.println("Usuario agregado: " + usuario.getCorreoElectronico() + " con contraseña.");
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar el usuario: " + e.getMessage());
            return false;
        }
    }
    
    public static synchronized ConsultarClienteDTO autenticarUsuario(String correo, String contrasenaIngresada) {
        String correoLower = correo.toLowerCase();
        String contrasenaAlmacenada = almacenContrasenas.get(correoLower);

        if (contrasenaAlmacenada != null && contrasenaAlmacenada.equals(contrasenaIngresada)) {
            for (ConsultarClienteDTO usuario : listaUsuarios) {
                if (usuario.getCorreoElectronico().equalsIgnoreCase(correo)) {
                    return usuario;
                }
            }
        }
        return null; 
    }

    public static boolean esAdmin(String correo) {
        if (correo == null) {
            return false;
        }
        boolean isAdmin = CORREOS_ADMIN.stream().anyMatch(adminCorreo -> adminCorreo.equalsIgnoreCase(correo));
        return isAdmin; 
    }

    public static ConsultarClienteDTO buscarClientePorId(long idCliente) {
        for (ConsultarClienteDTO cliente : listaUsuarios) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    public static synchronized boolean modificarUsuario(ModificarClienteDTO clienteModificado) {
        if (clienteModificado == null) {
            System.err.println("Error: Intento de modificar un usuario nulo.");
            return false;
        }

        for (int i = 0; i < listaUsuarios.size(); i++) {
            ConsultarClienteDTO clienteExistente = listaUsuarios.get(i);
            if (clienteExistente.getIdCliente() == clienteModificado.getId()) {
                clienteExistente.setNombreCliente(clienteModificado.getNombreCliente());
                clienteExistente.setApellidoCliente(clienteModificado.getApellidoCliente());
                
                if (!clienteExistente.getCorreoElectronico().equalsIgnoreCase(clienteModificado.getCorreoElectronico())) {
                 
                    String contrasenaAntigua = almacenContrasenas.remove(clienteExistente.getCorreoElectronico().toLowerCase());
                    if (contrasenaAntigua != null) {
                         almacenContrasenas.put(clienteModificado.getCorreoElectronico().toLowerCase(), contrasenaAntigua);
                    }
                    for (ConsultarClienteDTO u : listaUsuarios) {
                        if (u.getCorreoElectronico().equalsIgnoreCase(clienteModificado.getCorreoElectronico()) && u.getIdCliente() != clienteModificado.getId()) {
                            System.err.println("Error: El nuevo correo electrónico '" + clienteModificado.getCorreoElectronico() + "' ya está en uso por otro cliente.");
                           
                            if(contrasenaAntigua != null) almacenContrasenas.put(clienteExistente.getCorreoElectronico().toLowerCase(), contrasenaAntigua);
                            return false; 
                        }
                    }
                }
                clienteExistente.setCorreoElectronico(clienteModificado.getCorreoElectronico());
                clienteExistente.setActivo(clienteModificado.isActivo());
                clienteExistente.setEstado(clienteModificado.isActivo() ? "activo" : "inactivo");

                if (clienteModificado.getContraseña() != null && !clienteModificado.getContraseña().isEmpty()) {
                    System.out.println("INFO: Contraseña para el usuario " + clienteExistente.getCorreoElectronico() + " se ha solicitado cambiar.");
                    almacenContrasenas.put(clienteExistente.getCorreoElectronico().toLowerCase(), clienteModificado.getContraseña());
                }

                listaUsuarios.set(i, clienteExistente);
                System.out.println("Usuario modificado: " + clienteExistente.getCorreoElectronico());
                return true;
            }
        }
        System.err.println("Error: Usuario con ID " + clienteModificado.getId() + " no encontrado para modificar.");
        return false;
    }
}