package Negocio;

import DTOS.LibroDTO;
import Persistencia.IlibroDAO; // Asegúrate que esta sea la interfaz correcta
import Persistencia.LibroDAOImplMemoria; // Implementación en memoria
import expciones.PersistenciaException; // Si aplica para los métodos DAO

import java.util.ArrayList;
import java.util.List;

public class BoProductos {

    // Usar la interfaz DAO para la persistencia de libros
    private IlibroDAO libroDAO;

    public BoProductos() {
        // Instanciar la implementación en memoria del DAO
        // En una aplicación real, esto podría ser inyectado o gestionado por un framework.
        this.libroDAO = new LibroDAOImplMemoria();
    }

    public boolean agregarLibro(LibroDTO libro) {
        if (libro != null && !libro.getTitulo().isEmpty() && !libro.getIsbn().isEmpty()) {
            try {
                boolean agregado = libroDAO.agregarLibro(libro);
                if (agregado) {
                    System.out.println("Libro agregado correctamente a través de BoProductos -> DAO: " + libro.getTitulo());
                } else {
                    System.out.println("El libro con ISBN " + libro.getIsbn() + " ya existe (BoProductos -> DAO).");
                }
                return agregado;
            } catch (PersistenciaException e) {
                System.err.println("Error de persistencia al agregar libro (BoProductos): " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Error: el libro no puede ser nulo o debe tener un título o ISBN.");
            return false;
        }
    }

    public boolean actualizarLibro(LibroDTO libro) {
        if (libro != null && libro.getIsbn() != null && !libro.getIsbn().trim().isEmpty()
                && libro.getTitulo() != null && !libro.getTitulo().isEmpty()) {
            try {
                boolean actualizado = libroDAO.actualizarLibro(libro);
                if (actualizado) {
                    System.out.println("Libro actualizado correctamente a través de BoProductos -> DAO: " + libro.getTitulo());
                } else {
                    System.out.println("BoProductos: No se encontró el libro con ISBN " + libro.getIsbn() + " para actualizar, o error en DAO.");
                }
                return actualizado;
            } catch (PersistenciaException e) {
                System.err.println("Error de persistencia al actualizar libro (BoProductos): " + e.getMessage());
                return false;
            }
        } else {
            System.err.println("BoProductos Error: El libro a actualizar no puede ser nulo o le faltan datos esenciales (ISBN, Título).");
            return false;
        }
    }

    public List<LibroDTO> obtenerTodosLosLibros() {
        try {
            return libroDAO.obtenerTodosLosLibros();
        } catch (PersistenciaException e) {
            System.err.println("Error de persistencia al obtener todos los libros (BoProductos): " + e.getMessage());
            return new ArrayList<>(); // Devolver lista vacía en caso de error
        }
    }
}
