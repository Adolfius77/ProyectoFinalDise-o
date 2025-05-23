package Negocio;

import DTOS.ConsultarClienteDTO;
import DTOS.ModificarClienteDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class GestionUsuarios {

    private static List<ConsultarClienteDTO> listaUsuarios = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicLong idCounter = new AtomicLong(System.currentTimeMillis() / 1000); 

    
    private static Map<String, String> almacenContrasenas = Collections.synchronizedMap(new HashMap<>());

    private static final List<String> CORREOS_ADMIN = Arrays.asList(
            "admin@mitas.com",
            "admin2@mitas.com"
    );

    static {
        
        long userId1 = idCounter.getAndIncrement();
        listaUsuarios.add(new ConsultarClienteDTO(userId1, "Usuario", "Prueba", "ado@gmail.com", true, "activo"));
        almacenContrasenas.put("ado@gmail.com", "123");

        long adminId1 = idCounter.getAndIncrement();
        listaUsuarios.add(new ConsultarClienteDTO(adminId1, "Admin", "Principal", "admin@mitas.com", true, "activo"));
        almacenContrasenas.put("admin@mitas.com", "adminpass");

        System.out.println("Usuarios iniciales cargados en GestionUsuarios. Total: " + listaUsuarios.size());
        System.out.println("Correos de administradores definidos: " + CORREOS_ADMIN);
    }

    public static List<ConsultarClienteDTO> getListaUsuario() {
        return new ArrayList<>(listaUsuarios);
    }

    /**
     * @param usuario.
     * @param contrasena.
     * @return.
     */
    public static synchronized boolean agregarUsuarioYContrasena(ConsultarClienteDTO usuario, String contrasena) {
        if (usuario == null || usuario.getCorreoElectronico() == null || contrasena == null || contrasena.isEmpty()) {
            System.err.println("Error en GestionUsuarios.agregarUsuarioYContrasena: Datos de usuario o contraseña inválidos.");
            return false;
        }
        
        for (ConsultarClienteDTO u : listaUsuarios) {
            if (u.getCorreoElectronico().equalsIgnoreCase(usuario.getCorreoElectronico())) {
                System.err.println("Error en GestionUsuarios: El correo electrónico '" + usuario.getCorreoElectronico() + "' ya está registrado.");
                return false;
            }
        }
        try {
            
            if (usuario.getIdCliente() == 0) {
                 usuario.setIdCliente(idCounter.getAndIncrement());
            }
            listaUsuarios.add(usuario);
            
            almacenContrasenas.put(usuario.getCorreoElectronico().toLowerCase(), contrasena);
            System.out.println("Usuario agregado por GestionUsuarios: " + usuario.getCorreoElectronico() + " con ID: " + usuario.getIdCliente());
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar el usuario en GestionUsuarios: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**

     * @param correo.
     * @param contrasenaIngresada.
     * @return.
     */
    public static synchronized ConsultarClienteDTO autenticarUsuario(String correo, String contrasenaIngresada) {
        if (correo == null || contrasenaIngresada == null) {
            return null;
        }
        String correoLower = correo.toLowerCase();
        String contrasenaAlmacenada = almacenContrasenas.get(correoLower);

        if (contrasenaAlmacenada != null && contrasenaAlmacenada.equals(contrasenaIngresada)) {
            for (ConsultarClienteDTO usuario : listaUsuarios) {
                if (usuario.getCorreoElectronico().equalsIgnoreCase(correoLower)) {
                    return usuario; // Authentication successful
                }
            }
        }
        return null; 
    }

    public static boolean esAdmin(String correo) {
        if (correo == null) {
            return false;
        }
        return CORREOS_ADMIN.stream().anyMatch(adminCorreo -> adminCorreo.equalsIgnoreCase(correo.trim()));
    }

    /**
     * @param idCliente.
     * @return.
     */
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
            System.err.println("Error en GestionUsuarios.modificarUsuario: clienteModificado es nulo.");
            return false;
        }

        for (int i = 0; i < listaUsuarios.size(); i++) {
            ConsultarClienteDTO clienteExistente = listaUsuarios.get(i);
            if (clienteExistente.getIdCliente() == clienteModificado.getId()) {
                String correoOriginal = clienteExistente.getCorreoElectronico().toLowerCase();
                String nuevoCorreo = clienteModificado.getCorreoElectronico().toLowerCase();

                
                if (!correoOriginal.equals(nuevoCorreo)) {
                    for (ConsultarClienteDTO u : listaUsuarios) {
                        if (u.getCorreoElectronico().equalsIgnoreCase(nuevoCorreo) && u.getIdCliente() != clienteModificado.getId()) {
                            System.err.println("Error en GestionUsuarios: El nuevo correo electrónico '" + clienteModificado.getCorreoElectronico() + "' ya está en uso por otro cliente.");
                            return false;
                        }
                    }
                    
                    String pass = almacenContrasenas.remove(correoOriginal);
                    if (pass != null) {
                        almacenContrasenas.put(nuevoCorreo, pass);
                    }
                }
                
                
                clienteExistente.setNombreCliente(clienteModificado.getNombreCliente());
                clienteExistente.setApellidoCliente(clienteModificado.getApellidoCliente());
                clienteExistente.setCorreoElectronico(clienteModificado.getCorreoElectronico());
                clienteExistente.setActivo(clienteModificado.isActivo());
                clienteExistente.setEstado(clienteModificado.isActivo() ? "activo" : "inactivo");

                
                if (clienteModificado.getContraseña() != null && !clienteModificado.getContraseña().isEmpty()) {
                    almacenContrasenas.put(nuevoCorreo, clienteModificado.getContraseña());
                    System.out.println("INFO: Contraseña actualizada para el usuario " + nuevoCorreo);
                }

                listaUsuarios.set(i, clienteExistente);
                System.out.println("Usuario modificado en GestionUsuarios: " + clienteExistente.getCorreoElectronico());
                return true;
            }
        }
        System.err.println("Error en GestionUsuarios: Usuario con ID " + clienteModificado.getId() + " no encontrado para modificar.");
        return false;
    }
}
