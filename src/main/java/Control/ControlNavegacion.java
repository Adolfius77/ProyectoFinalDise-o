/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DTOS.ConsultarClienteDTO;
import DTOS.EntradaHistorialDTO;
import DTOS.LibroDTO;
import Negocio.ManejoPagos;
import Presentacion.GUIAdmin;
import Presentacion.GUIAgregarLibro;
import Presentacion.GUICambioContraseña;
import Presentacion.GUICarrito;
import Presentacion.GUICategorias;
import Presentacion.GUIDetallesLibro;
import Presentacion.GUIEditarLibro;
import Presentacion.GUIEnvioDHL;
import Presentacion.GUIEnvioEstafeta;
import Presentacion.GUIHistorialEntradas;
import Presentacion.GUIINICIO;
import Presentacion.GUIPagInicioGestionLibros;
import Presentacion.GUIPaginaPagos;
import Presentacion.GUIPagoMastercard;
import Presentacion.GUIPagoPaypal;
import Presentacion.GUIPerfil;
import Presentacion.GUIRegistrarEntrada;
import Presentacion.GUIReseñas;
import Presentacion.GUISeleccionMetodoEnvio;
import Presentacion.InicioSesion;
import Presentacion.Registro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import DTOS.ReseñaUsuarioDTO;
import DTOS.usuarioDTO;
import Negocio.BoProductos;
import Presentacion.GUIClientesAgregar;
import Presentacion.GUIClientesModificar;
import Presentacion.GUIDetallesLibroEntradas;
import Presentacion.GUIPagInicioGestionClientes;
import expciones.PersistenciaException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author USER
 */
public class ControlNavegacion {

    private static ControlNavegacion instancia;
    private ManejoPagos manejoPagos;

    private Map<String, List<LibroDTO>> carritosPorUsuario;
    private List<EntradaHistorialDTO> historialDeEntradas;
    private String ultimaCategoriaSeleccionada = null;
    private List<ReseñaUsuarioDTO> reseñaUsuario;
    private ConsultarClienteDTO usuarioActual = null;

    private static final String NOMBRE_ARCHIVO_CARRITOS = "carritos_usuarios.dat"; // Archivo para persistir carritos

    private ControlNavegacion() {

        this.manejoPagos = new ManejoPagos();
        this.historialDeEntradas = Collections.synchronizedList(new ArrayList<>());
        this.reseñaUsuario = new ArrayList<>();

        cargarCarritosDesdeArchivo();
        if (this.carritosPorUsuario == null) {
            this.carritosPorUsuario = Collections.synchronizedMap(new HashMap<>());

        }
    }

    public void setUsuarioActual(ConsultarClienteDTO usuario) {
        this.usuarioActual = usuario;
        if (usuario != null) {
            System.out.println("Usuario actual establecido: " + usuario.getCorreoElectronico());
            carritosPorUsuario.putIfAbsent(usuario.getCorreoElectronico().toLowerCase(), new ArrayList<>());
        } else {
            System.out.println("Usuario actual establecido a null (cierre de sesión).");
        }

    }

    public ConsultarClienteDTO getUsuarioActual() {
        return this.usuarioActual;
    }

    public boolean esAdminLogueado() {

        boolean isAdmin = (usuarioActual != null && Negocio.GestionUsuarios.esAdmin(usuarioActual.getCorreoElectronico()));
        System.out.println("Verificando si usuario actual (" + (usuarioActual != null ? usuarioActual.getCorreoElectronico() : "null") + ") es admin: " + isAdmin);
        return isAdmin;
    }

    public void agregarResenaDelUsuario(ReseñaUsuarioDTO resena) {
        if (resena != null) {
            this.reseñaUsuario.add(resena);
            System.out.println("Reseña para '" + resena.getTituloLibro() + "' agregada a 'Mis Reseñas'.");
        }
    }

    public List<ReseñaUsuarioDTO> getMisResenasDelUsuario() {
        return new ArrayList<>(this.reseñaUsuario);
    }

    public static synchronized ControlNavegacion getInstase() {
        if (instancia == null) {
            instancia = new ControlNavegacion();
        }
        return instancia;
    }

    public List<LibroDTO> getCarrito() {
        if (usuarioActual != null && usuarioActual.getCorreoElectronico() != null) {
            String userKey = usuarioActual.getCorreoElectronico().toLowerCase();

            return new ArrayList<>(carritosPorUsuario.getOrDefault(userKey, new ArrayList<>()));
        }
        return new ArrayList<>();
    }

    public String getUltimaCategoriaSeleccionada() {
        return ultimaCategoriaSeleccionada;
    }

    public void setUltimaCategoriaSeleccionada(String categoria) {
        this.ultimaCategoriaSeleccionada = categoria;
    }

    // --- Métodos del Carrito ---
    public double getMontoTotalCarrito() {
        double total = 0.0;
        List<LibroDTO> carritoActualUsuario = getCarrito();
        for (LibroDTO libro : carritoActualUsuario) {
            if (libro != null && libro.getPrecio() >= 0) {
                total += libro.getPrecio();
            }
        }
        return total;
    }

    public int getCantidadTotalArticulos() {
        return getCarrito().size();
    }

    public void limpiarCarrito() {
        if (usuarioActual != null && usuarioActual.getCorreoElectronico() != null) {
            String UserKey = usuarioActual.getCorreoElectronico().toLowerCase();
            List<LibroDTO> carritoDelUsuario = carritosPorUsuario.get(UserKey);
            if (carritoDelUsuario != null) {
                carritoDelUsuario.clear();
                System.out.println("carrito limpiado para el usuario " + UserKey);
                guardarCarritosEnArchivo();
            }

        } else {
            System.out.println("No hay usuario logueado, no se puede limpiar ningún carrito específico.");

        }
    }

    public boolean agregarLibroCarrito(LibroDTO libro) {
        if (usuarioActual == null || usuarioActual.getCorreoElectronico() == null) {
            JOptionPane.showMessageDialog(null, "Debe iniciar sesión para agregar libros al carrito.", "Usuario no Logueado", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (libro == null || libro.getIsbn() == null || libro.getIsbn().trim().isEmpty()) {
            System.err.println("ControlNavegacion: Intento de agregar libro nulo o sin ISBN al carrito.");
            return false;
        }

        BoProductos boProductos = new BoProductos();
        LibroDTO libroDesdeFuenteDatos;

        try {
            libroDesdeFuenteDatos = boProductos.obtenerLibrosPorIsbn(libro.getIsbn());
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar stock: " + e.getMessage(), "Error de Stock", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (libroDesdeFuenteDatos == null) {
            JOptionPane.showMessageDialog(null, "El libro '" + libro.getTitulo() + "' no se encuentra en la base de datos.", "Error de Producto", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String userKey = usuarioActual.getCorreoElectronico().toLowerCase();
        List<LibroDTO> carritoDelUsuario = carritosPorUsuario.computeIfAbsent(userKey, k -> Collections.synchronizedList(new ArrayList<>()));

        long unidadesYaEnCarrito = carritoDelUsuario.stream()
                .filter(l -> libro.getIsbn().equals(l.getIsbn()))
                .count();

        if (libroDesdeFuenteDatos.getCantidad() <= unidadesYaEnCarrito) {
            JOptionPane.showMessageDialog(null,
                    "No hay más stock disponible para '" + libro.getTitulo() + "'.\nStock actual en sistema: "
                    + libroDesdeFuenteDatos.getCantidad() + ", Unidades en su carrito: " + unidadesYaEnCarrito,
                    "Stock Insuficiente", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean stockDecrementadoExitosamente = boProductos.decrementarStockLibro(libro.getIsbn(), 1);

        if (stockDecrementadoExitosamente) {

            carritoDelUsuario.add(new LibroDTO(
                    libro.getTitulo(), libro.getAutor(), libro.getIsbn(),
                    libro.getFechaLanzamiento(), libro.getCategoria(), libro.getPrecio(),
                    libro.getEditorial(), libro.getNumPaginas(),
                    1,
                    libro.getRutaImagen(), libro.getSinopsis(),
                    libro.getReseñas() != null ? new ArrayList<>(libro.getReseñas()) : new ArrayList<>()
            ));
            System.out.println("Libro '" + libro.getTitulo() + "' (ISBN: " + libro.getIsbn() + ") añadido al carrito de " + userKey + ". Stock global decrementado.");
            guardarCarritosEnArchivo();
            return true;
        } else {

            JOptionPane.showMessageDialog(null, "No se pudo agregar '" + libro.getTitulo() + "' al carrito. El stock podría haberse agotado o hubo un error al actualizar.", "Error al Actualizar Stock", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean eliminarLibroCarrito(LibroDTO libro) {

        if (usuarioActual == null || usuarioActual.getCorreoElectronico() == null) {
            System.err.println("ControlNavegacion: No hay usuario logueado para eliminar libro del carrito.");
            return false;
        }

        if (libro == null || libro.getIsbn() == null || libro.getIsbn().trim().isEmpty()) {
            System.err.println("ControlNavegacion: Intento de eliminar libro nulo o sin ISBN del carrito.");
            return false;
        }

        String userKey = usuarioActual.getCorreoElectronico().toLowerCase();
        List<LibroDTO> carritoDelUsuario = carritosPorUsuario.get(userKey);

        boolean removidoExitosamente = false;

        if (carritoDelUsuario != null && !carritoDelUsuario.isEmpty()) {

            LibroDTO libroARemover = null;
            for (LibroDTO libroEnCarrito : carritoDelUsuario) {
                if (libro.getIsbn().equals(libroEnCarrito.getIsbn())) {
                    libroARemover = libroEnCarrito;
                    break;
                }
            }

            if (libroARemover != null) {
                removidoExitosamente = carritoDelUsuario.remove(libroARemover);
            }

            if (removidoExitosamente) {
                System.out.println("Libro '" + libro.getTitulo() + "' (ISBN: " + libro.getIsbn() + ") eliminado del carrito de " + userKey + ".");

                BoProductos boProductos = new BoProductos();
                boolean stockIncrementado = boProductos.incrementarStockLibro(libro.getIsbn(), 1);

                if (stockIncrementado) {
                    System.out.println("Stock global para el libro '" + libro.getTitulo() + "' (ISBN: " + libro.getIsbn() + ") incrementado en 1.");
                } else {

                    System.err.println("ADVERTENCIA: Libro '" + libro.getTitulo() + "' (ISBN: " + libro.getIsbn()
                            + ") eliminado del carrito, PERO NO se pudo incrementar el stock global.");
                }

                guardarCarritosEnArchivo();
                return true;
            } else {
                System.out.println("Libro '" + libro.getTitulo() + "' (ISBN: " + libro.getIsbn() + ") no encontrado en el carrito de " + userKey + " para eliminar.");
                return false;
            }
        } else {
            System.out.println("El carrito del usuario " + userKey + " está vacío o no existe. No se puede eliminar el libro.");
            return false;
        }
    }

    private void guardarCarritosEnArchivo() {
        synchronized (carritosPorUsuario) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO_CARRITOS))) {
                oos.writeObject(new HashMap<>(this.carritosPorUsuario));
                System.out.println("Mapa de carritos de usuarios guardado en " + NOMBRE_ARCHIVO_CARRITOS);
            } catch (IOException e) {
                System.err.println("Error al guardar los carritos en archivo: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void cargarCarritosDesdeArchivo() {
        File archivoCarritos = new File(NOMBRE_ARCHIVO_CARRITOS);
        if (archivoCarritos.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoCarritos))) {
                this.carritosPorUsuario = (Map<String, List<LibroDTO>>) ois.readObject();
                if (this.carritosPorUsuario == null) {
                    this.carritosPorUsuario = new HashMap<>();
                }
                this.carritosPorUsuario.replaceAll((k, v) -> Collections.synchronizedList(new ArrayList<>(v)));

                System.out.println("Carritos de usuarios cargados desde " + NOMBRE_ARCHIVO_CARRITOS);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar los carritos desde archivo: " + e.getMessage() + ". Se creará un mapa nuevo.");
                this.carritosPorUsuario = Collections.synchronizedMap(new HashMap<>());
            }
        } else {
            System.out.println("Archivo de carritos " + NOMBRE_ARCHIVO_CARRITOS + " no encontrado. Se creará uno nuevo.");
            this.carritosPorUsuario = Collections.synchronizedMap(new HashMap<>());
        }
    }

    public ManejoPagos getManejoPagos() {
        return this.manejoPagos;
    }

    public void setManejoPagos(ManejoPagos manejoPagos) {
        this.manejoPagos = manejoPagos;
    }

    // --- Métodos de Navegación ---
    private void cerrarFrameActual(JFrame frameActual) {
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    public void navegarRegistro(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        Registro regi = new Registro();
        regi.setVisible(true);
    }

    public void navegarInicioSesion(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        InicioSesion ini = new InicioSesion();
        ini.setVisible(true);
    }

    public void navegarInicio(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIINICIO inicio = new GUIINICIO();
        inicio.setVisible(true);
    }

    public void navegarCategorias(JFrame frameActual, String categoriaARestaurar) {
        cerrarFrameActual(frameActual);
        GUICategorias categoriasScreen = new GUICategorias(getCarrito());
        if (categoriaARestaurar != null) {
            categoriasScreen.seleccionarCategoria(categoriaARestaurar);
        }
        categoriasScreen.setVisible(true);
    }

    public void navegarCategorias(JFrame frameActual) {
        navegarCategorias(frameActual, this.ultimaCategoriaSeleccionada);
    }

    public void navegarPerfil(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIPerfil perfil = new GUIPerfil();
        perfil.setVisible(true);
    }

    public void navegarReseñas(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIReseñas reseñas = new GUIReseñas();
        reseñas.setVisible(true);
    }

    public void navegarAReseñas(JFrame frameActual, LibroDTO libroConReseñas) {
        cerrarFrameActual(frameActual);
        if (libroConReseñas == null) {
            System.err.println("Error: No se puede mostrar reseñas sin un libro.");
            navegarCategorias(frameActual);
            return;
        }
        GUIReseñas reseñas = new GUIReseñas(libroConReseñas);
        reseñas.setVisible(true);
    }

    public void navegarCarrito(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUICarrito carritoGUI = new GUICarrito(getCarrito());
        carritoGUI.setVisible(true);
    }

    public void navegarCambioPasssword(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUICambioContraseña contra = new GUICambioContraseña();
        contra.setVisible(true);
    }

    public void navegarPaginaPagos(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIPaginaPagos paginaPagos = new GUIPaginaPagos();
        paginaPagos.setVisible(true);
    }

    public void navegarPagoConTarjeta(JFrame frameActual, double monto, List<LibroDTO> carritoPago) {
        cerrarFrameActual(frameActual);
        GUIPagoMastercard mastercard = new GUIPagoMastercard(monto, carritoPago);
        mastercard.setVisible(true);
    }

    public void navegarPaginaPagoTarjeta(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIPagoMastercard mastercard = new GUIPagoMastercard();
        mastercard.setVisible(true);
    }

    public void navegarPaypal(JFrame frameActual, double monto, List<LibroDTO> carritoPago) {
        cerrarFrameActual(frameActual);
        GUIPagoPaypal paypal = new GUIPagoPaypal(monto, carritoPago);
        paypal.setVisible(true);
    }

    public void navegarPaginaPagoPaypal(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIPagoPaypal paypal = new GUIPagoPaypal();
        paypal.setVisible(true);
    }

    public void navegarSeleccionEnvio(JFrame frameActual, List<LibroDTO> carritoRecibido) {
        cerrarFrameActual(frameActual);
        GUISeleccionMetodoEnvio metodoEnvio = new GUISeleccionMetodoEnvio(carritoRecibido);
        metodoEnvio.setVisible(true);
    }

    public void navegarPaginaSeleccionEnvio(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUISeleccionMetodoEnvio metodoEnvio = new GUISeleccionMetodoEnvio();
        metodoEnvio.setVisible(true);
    }

    public void navegarEnvioEstafeta(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIEnvioEstafeta estafeta = new GUIEnvioEstafeta();
        estafeta.setVisible(true);
    }

    public void navegarEnvioDHL(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIEnvioDHL dhl = new GUIEnvioDHL();
        dhl.setVisible(true);
    }

    public void navegarGestionLibro(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIPagInicioGestionLibros gestionLibro = new GUIPagInicioGestionLibros();
        gestionLibro.setVisible(true);
    }

    public void navegarAgregarLibro(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIAgregarLibro agregarLibro = new GUIAgregarLibro();
        agregarLibro.setVisible(true);
    }

    public void navegarEditarLibro(JFrame frameActual, LibroDTO libroAEditar) {
        cerrarFrameActual(frameActual);
        GUIEditarLibro guiEdicion = new GUIEditarLibro(libroAEditar);
        guiEdicion.setVisible(true);
    }

    public void navegarAdminGui(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIAdmin admin = new GUIAdmin();
        admin.setVisible(true);
    }

    public void navegarDetallesEntrada(JFrame frameActual, LibroDTO libroSeleccionado) {
        cerrarFrameActual(frameActual);

        if (libroSeleccionado == null) {
            System.err.println("Error Crítico: Se intento navegar a detalles de entrada SIN un libro seleccionado.");
            JOptionPane.showMessageDialog(null, "No se puede mostrar detalles, libro no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        GUIDetallesLibroEntradas detallesEntradaScreen = new GUIDetallesLibroEntradas(libroSeleccionado);
        detallesEntradaScreen.setVisible(true);
    }

    public void cerrarSesion(JFrame frameActual) {
        System.out.println("Cerrando sesion para: " + (usuarioActual != null ? usuarioActual.getCorreoElectronico() : "nadie"));

        if (usuarioActual != null && usuarioActual.getCorreoElectronico() != null) {
            String userKey = usuarioActual.getCorreoElectronico().toLowerCase();
            List<LibroDTO> carritoDelUsuario = carritosPorUsuario.get(userKey);
            if (carritoDelUsuario != null && !carritoDelUsuario.isEmpty()) {
                BoProductos boProductos = new BoProductos();
                for (LibroDTO libroEnCarrito : new ArrayList<>(carritoDelUsuario)) {

                }
                System.out.println("Stock restaurado para los libros en el carrito del usuario: " + userKey);
            }
        }

        setUsuarioActual(null);

        if (frameActual != null) {
            frameActual.dispose();
        }

        InicioSesion pantallaLogin = new InicioSesion();
        pantallaLogin.setVisible(true);

        JOptionPane.showMessageDialog(pantallaLogin, "Sesion cerrada exitosamente. Por favor, inicie sesion de nuevo.");
    }

    public void navegarRegistroEntrada(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIRegistrarEntrada registraEntrada = new GUIRegistrarEntrada();
        registraEntrada.setVisible(true);
    }

    public void navegarDetallesLibro(JFrame frameActual, LibroDTO libroSeleccionado, String categoriaDeOrigen) {
        if (categoriaDeOrigen != null) {
            setUltimaCategoriaSeleccionada(categoriaDeOrigen);
        }

        if (libroSeleccionado == null) {
            System.err.println("Error Crítico: Se intentó navegar a detalles SIN un libro seleccionado. Creando DTO de error.");
            libroSeleccionado = new LibroDTO(
                    "Información no Disponible", "N/A", "N/A", new Date(), "N/A",
                    0.0, "N/A", 0, 0, "",
                    "Los detalles para este libro no pudieron ser cargados."
            );
        }
        GUIDetallesLibro detallesLibro = new GUIDetallesLibro(libroSeleccionado);
        detallesLibro.setVisible(true);
        cerrarFrameActual(frameActual);
    }

    public void navegarDetallesLibro(JFrame frameActual, LibroDTO libroSeleccionado) {

        navegarDetallesLibro(frameActual, libroSeleccionado, this.ultimaCategoriaSeleccionada);
    }

    public void agregarEntradaAlHistorial(EntradaHistorialDTO entrada) {
        if (entrada != null) {
            this.historialDeEntradas.add(entrada);
            System.out.println("Entrada de stock para ISBN " + entrada.getIsbn() + " agregada al historial.");
        }
    }

    public List<EntradaHistorialDTO> obtenerHistorialDeEntradas() {
        return new ArrayList<>(this.historialDeEntradas);
    }

    public void navegarHistorialEntradas(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIHistorialEntradas entradas = new GUIHistorialEntradas();
        entradas.setVisible(true);
    }

    public void navegarAgregarCliente(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIClientesAgregar entrarAregarCliente = new GUIClientesAgregar();
        entrarAregarCliente.setVisible(true);
    }

    public void navegarEditarCliente(JFrame frameActual, ConsultarClienteDTO consultarCliente) {
        cerrarFrameActual(frameActual);
        GUIClientesModificar entrarAregarCliente = new GUIClientesModificar(consultarCliente);
        entrarAregarCliente.setVisible(true);
    }

    public void navegarInicioGestionClientes(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIPagInicioGestionClientes entrarGestionCliente = new GUIPagInicioGestionClientes();
        entrarGestionCliente.setVisible(true);
    }

}
