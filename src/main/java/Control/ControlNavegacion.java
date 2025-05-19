/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

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
import Presentacion.GUISeleccionMetodoEnvio;
import Presentacion.InicioSesion;
import Presentacion.Registro;
// import java.awt.event.ActionEvent; // No se usan directamente aquí
// import java.awt.event.ActionListener; // No se usan directamente aquí
// import java.lang.reflect.Array; // No se usa
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class ControlNavegacion {

    private static ControlNavegacion instancia;
    private ManejoPagos manejoPagos;
    private List<LibroDTO> carrito;
    private List<EntradaHistorialDTO> historialDeEntradas;
    private String ultimaCategoriaSeleccionada = null; // Para recordar la última categoría

    private ControlNavegacion() {
        this.carrito = new ArrayList<>();
        this.manejoPagos = new ManejoPagos();
        this.historialDeEntradas = Collections.synchronizedList(new ArrayList<>());
    }

    public static synchronized ControlNavegacion getInstase() {
        if (instancia == null) {
            instancia = new ControlNavegacion();
        }
        return instancia;
    }

    // --- Getters y Setters ---
    public List<LibroDTO> getCarrito() {
        return this.carrito;
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
        if (this.carrito != null) {
            for (LibroDTO libro : this.carrito) {
                if (libro != null && libro.getPrecio() >= 0) {
                    total += libro.getPrecio();
                }
            }
        }
        return total;
    }

    public int getCantidadTotalArticulos() {
        return (this.carrito != null) ? this.carrito.size() : 0;
    }

    public void limpiarCarrito() {
        if (this.carrito != null) {
            this.carrito.clear();
        }
        System.out.println("Todos los artículos del carrito han sido eliminados.");
    }

    public void agregarLibroCarrito(LibroDTO libro) {
        if (libro != null && this.carrito != null) {
            this.carrito.add(libro);
            System.out.println("Se añadió al carrito el libro: " + libro.getTitulo() + ". Libros en el carrito: " + this.carrito.size());
        } else {
            System.err.println("Error al intentar añadir el libro al carrito (libro o carrito nulos).");
        }
    }

    public void eliminarLibroCarrito(LibroDTO libro) {
        if (libro != null && this.carrito != null) {
            boolean removido = this.carrito.remove(libro); // remove() usa equals() de LibroDTO
            if (removido) {
                System.out.println("Se eliminó del carrito el libro: " + libro.getTitulo() + ". Total en la lista: " + this.carrito.size());
            } else {
                System.out.println("No se encontró el libro '" + libro.getTitulo() + "' para eliminar del carrito (podría ser una instancia diferente con mismo ISBN).");
                // Si equals se basa solo en ISBN, esto debería funcionar.
                // Si necesitas remover por referencia exacta o un ID único, ajusta la lógica.
            }
        } else {
            System.err.println("Error al intentar eliminar el libro del carrito (libro o carrito nulos).");
        }
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

    // Sobrecarga para navegar a categorías recordando la última seleccionada
    public void navegarCategorias(JFrame frameActual, String categoriaARestaurar) {
        cerrarFrameActual(frameActual);
        GUICategorias categoriasScreen = new GUICategorias(this.getCarrito());
        if (categoriaARestaurar != null) {
            categoriasScreen.seleccionarCategoria(categoriaARestaurar);
        }
        categoriasScreen.setVisible(true);
    }

    // Versión original de navegarCategorias (ahora llama a la sobrecargada)
    public void navegarCategorias(JFrame frameActual) {
        navegarCategorias(frameActual, this.ultimaCategoriaSeleccionada);
    }

    public void navegarPerfil(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIPerfil perfil = new GUIPerfil();
        perfil.setVisible(true);
    }

    public void navegarCarrito(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUICarrito carritoGUI = new GUICarrito(this.carrito); // Usa this.carrito
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

    public void navegarPaginaPagoTarjeta(JFrame frameActual) { // Usado si se navega sin monto/carrito previo
        cerrarFrameActual(frameActual);
        GUIPagoMastercard mastercard = new GUIPagoMastercard();
        mastercard.setVisible(true);
    }

    public void navegarPaypal(JFrame frameActual, double monto, List<LibroDTO> carritoPago) {
        cerrarFrameActual(frameActual);
        GUIPagoPaypal paypal = new GUIPagoPaypal(monto, carritoPago);
        paypal.setVisible(true);
    }

    public void navegarPaginaPagoPaypal(JFrame frameActual) { // Usado si se navega sin monto/carrito previo
        cerrarFrameActual(frameActual);
        GUIPagoPaypal paypal = new GUIPagoPaypal();
        paypal.setVisible(true);
    }

    public void navegarSeleccionEnvio(JFrame frameActual, List<LibroDTO> carritoRecibido) {
        cerrarFrameActual(frameActual);
        GUISeleccionMetodoEnvio metodoEnvio = new GUISeleccionMetodoEnvio(carritoRecibido);
        metodoEnvio.setVisible(true);
    }

    public void navegarPaginaSeleccionEnvio(JFrame frameActual) { // Usado si se navega sin carrito previo
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

    public void cerrarSesion(JFrame frameActual) {
        limpiarCarrito();
        // Aquí deberías navegar a la pantalla de InicioSesion
        JOptionPane.showMessageDialog(frameActual, "Sesión cerrada. Volviendo al inicio de sesión.");
        navegarInicioSesion(frameActual); // Llama al método para ir a la pantalla de login
    }

    public void navegarRegistroEntrada(JFrame frameActual) {
        cerrarFrameActual(frameActual);
        GUIRegistrarEntrada registraEntrada = new GUIRegistrarEntrada();
        registraEntrada.setVisible(true);
    }

    // --- Métodos para Detalles del Libro ---
    // Sobrecarga para navegar a detalles del libro guardando la categoría de origen
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

    // Versión original de navegarDetallesLibro (ahora llama a la sobrecargada)
    public void navegarDetallesLibro(JFrame frameActual, LibroDTO libroSeleccionado) {
        // Si se llama esta versión, se usa la ultimaCategoriaSeleccionada si existe, o null
        navegarDetallesLibro(frameActual, libroSeleccionado, this.ultimaCategoriaSeleccionada);
    }

    // --- Métodos para Historial de Entradas ---
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

    // --- Manejo de Pagos ---
    public ManejoPagos getManejoPagos() {
        return this.manejoPagos;
    }

    public void setManejoPagos(ManejoPagos manejoPagos) {
        this.manejoPagos = manejoPagos;
    }
}
