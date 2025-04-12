/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DTOS.LibroDTO;
import Negocio.ManejoPagos;
import Presentacion.GUICambioContraseña;
import Presentacion.GUICarrito;
import Presentacion.GUICategorias;
import Presentacion.GUIEnvioDHL;
import Presentacion.GUIEnvioEstafeta;
import Presentacion.GUIINICIO;
import Presentacion.GUIPaginaPagos;
import Presentacion.GUIPagoMastercard;
import Presentacion.GUIPagoPaypal;
import Presentacion.GUIPerfil;
import Presentacion.GUISeleccionMetodoEnvio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    private ControlNavegacion() {
        this.carrito = new ArrayList<>();
        this.manejoPagos = new ManejoPagos();
    }

    public static synchronized ControlNavegacion getInstase() {
        if (instancia == null) {
            instancia = new ControlNavegacion();
        }
        return instancia;
    }
    private List<LibroDTO> carrito;

    public List<LibroDTO> getCarrito() {
        return this.carrito;
    }

    public double getMontoTotalCarrito() {
        double total = 0.0;
        if (this.carrito != null) {
            for (LibroDTO libro : this.carrito) {
                // Asegurarse que el libro y su precio no son nulos
                if (libro != null && libro.getPrecio() >= 0) { // Asumiendo que precio no puede ser negativo
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

    public void navegarInicio(JFrame frameActual) {
        GUIINICIO inicio = new GUIINICIO();
        inicio.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    public void navegarCategorias(JFrame frameActual) {
        GUICategorias categorias = new GUICategorias();
        categorias.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    public void navegarPerfil(JFrame frameActual) {
        GUIPerfil perfil = new GUIPerfil();
        perfil.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    public void navegarCarrito(JFrame frameActual) {
        GUICarrito carrito = new GUICarrito(this.carrito);
        carrito.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    public void navegarCambioPasssword(JFrame frameActual) {
        GUICambioContraseña contra = new GUICambioContraseña();
        contra.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    public void navegarPaginaPagos(JFrame frameActual) {
        GUIPaginaPagos paginaPagos = new GUIPaginaPagos();
        paginaPagos.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    public void navegarPagoConTarjeta(JFrame frameActual, double monto, List<LibroDTO> carrito) {
        GUIPagoMastercard Mastercard = new GUIPagoMastercard(monto, carrito);
        Mastercard.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    // Constructor para acceder al GUI Pago con Mastercard
    public void navegarPaginaPagoTarjeta(JFrame frameActual) {
        GUIPagoMastercard mastercard = new GUIPagoMastercard();
        mastercard.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    public void navegarPaypal(JFrame frameActual, double monto, List<LibroDTO> carrito) {
        GUIPagoPaypal paypal = new GUIPagoPaypal(monto, carrito);
        paypal.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    // Constructor para acceder al GUI Pago con Paypal
    public void navegarPaginaPagoPaypal(JFrame frameActual) {
        GUIPagoPaypal paypal = new GUIPagoPaypal();
        paypal.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    public void navegarSeleccionEnvio(JFrame frameActual, List<LibroDTO> carritoRecibido) {
        GUISeleccionMetodoEnvio MetodoEnvio = new GUISeleccionMetodoEnvio(carritoRecibido);
        MetodoEnvio.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    // Constructor para acceder al GUI de Seleccion de envio
    public void navegarPaginaSeleccionEnvio(JFrame frameActual) {
        GUISeleccionMetodoEnvio MetodoEnvio = new GUISeleccionMetodoEnvio();
        MetodoEnvio.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    public void navegarEnvioEstafeta(JFrame frameActual) {
        GUIEnvioEstafeta estafeta = new GUIEnvioEstafeta();
        estafeta.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();
        }
    }

    public void navegarEnvioDHL(JFrame frameActual) {
        GUIEnvioDHL dhl = new GUIEnvioDHL();
        dhl.setVisible(true);
        if (frameActual != null) {
            frameActual.dispose();

        }
    }

    public void cerrarSesion(JFrame frameActual) {
        limpiarCarrito();

        JOptionPane.showMessageDialog(frameActual, "todavia no agrego esta parte xd");
        if (frameActual != null) {
            frameActual.dispose();

        }
    }

    public void agregarLibroCarrito(LibroDTO libro) {
        if (libro != null && this.carrito != null) {
            this.carrito.add(libro);
            System.out.println("Se añadio al el libro al carrito: " + libro.getTitulo() + ", Libros en el carrito: " + this.carrito.size());
        } else {
            System.err.println("Error al intentar añadir el libro");
        }
    }

    public void eliminarLibroCarrito(LibroDTO libro) {
        if (libro != null && this.carrito != null) {
            boolean remover = this.carrito.remove(libro);
            if (remover) {
                System.out.println("Se elimino del carrito el libro: " + libro.getTitulo() + ", total en la lista: " + this.carrito.size());
            } else {
                System.out.println("Error al intentar encontrar el libro: " + libro.getTitulo());
            }
        } else {
            System.err.println("Error al intentar eliminar el libro");
        }
    }

    public ManejoPagos getManejoPagos() {
        return this.manejoPagos;
    }

    public void setManejoPagos(ManejoPagos manejoPagos) {
        this.manejoPagos = manejoPagos;
    }

}
