package Negocio;

import DTOS.LibroDTO;
import Persistencia.IlibroDAO; 
import Persistencia.LibroDAOImplMemoria; 
import expciones.PersistenciaException;

import java.util.ArrayList;
import java.util.List;

public class BoProductos {

  
    private IlibroDAO libroDAO;
    public BoProductos() {
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

    public LibroDTO obtenerLibrosPorIsbn(String isbn) throws PersistenciaException {
        if (isbn == null || isbn.trim().isEmpty()) {

            System.out.println("BoProductos: Intento de búsqueda con ISBN nulo o vacío.");
            return null;
        }
        try {
            return libroDAO.obtenerLibrosPorIsbn(isbn);
        } catch (PersistenciaException e) {

            System.err.println("BoProductos: Error de persistencia al buscar libro por ISBN '" + isbn + "': " + e.getMessage());
            throw e;
        }
    }

    public boolean decrementarStockLibro(String isbn, int cantidadADecrementar) {
       if (isbn == null || isbn.trim().isEmpty() || cantidadADecrementar <= 0) {
        System.err.println("BoProductos Error: ISBN inválido o cantidad a decrementar no positiva.");
        return false;
    }
    try {
        LibroDTO libro = libroDAO.obtenerLibrosPorIsbn(isbn); 
        if (libro != null) {
            if (libro.getCantidad() >= cantidadADecrementar) {
                libro.setCantidad(libro.getCantidad() - cantidadADecrementar);
                boolean actualizado = libroDAO.actualizarLibro(libro); 
                if (actualizado) {
                    System.out.println("Stock del libro con ISBN " + isbn + " decrementado en " + cantidadADecrementar + ". Nuevo stock: " + libro.getCantidad());
                } else {
                    System.err.println("BoProductos Error: No se pudo actualizar el libro en el DAO para decrementar stock del ISBN " + isbn);
                }
                return actualizado;
            } else {
                System.err.println("BoProductos Error: No hay suficiente stock para el libro con ISBN " + isbn + ". Stock actual: " + libro.getCantidad() + ", se intentó decrementar: " + cantidadADecrementar);
                return false;
            }
        } else {
            System.err.println("BoProductos Error: Libro con ISBN " + isbn + " no encontrado para decrementar stock.");
            return false;
        }
    } catch (PersistenciaException e) {
        System.err.println("BoProductos Error de persistencia al decrementar stock para ISBN " + isbn + ": " + e.getMessage());
        return false;
    }
    }

    public boolean incrementarStockLibro(String isbn, int cantidadAIncrementar) {
        if (isbn == null || isbn.trim().isEmpty() || cantidadAIncrementar <= 0) {
            System.err.println("BoProductos Error: ISBN inválido o cantidad a incrementar no positiva.");
            return false;
        }
        try {
            LibroDTO libro = libroDAO.obtenerLibrosPorIsbn(isbn);
            if (libro != null) {
                libro.setCantidad(libro.getCantidad() + cantidadAIncrementar);
                boolean actualizado = libroDAO.actualizarLibro(libro);
                if (actualizado) {
                    System.out.println("Stock del libro con ISBN " + isbn + " incrementado en " + cantidadAIncrementar + ". Nuevo stock: " + libro.getCantidad());
                } else {
                    System.err.println("BoProductos Error: No se pudo actualizar el libro en el DAO para incrementar stock del ISBN " + isbn);
                }
                return actualizado;
            } else {
                System.err.println("BoProductos Error: Libro con ISBN " + isbn + " no encontrado para incrementar stock.");
                return false;
            }
        } catch (PersistenciaException e) {
            System.err.println("BoProductos Error de persistencia al incrementar stock para ISBN " + isbn + ": " + e.getMessage());
            return false;
        }
    }

}
