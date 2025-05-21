/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementaciones;

import Modelo.Libro;
import Persistencia.LibroDAO;
import expciones.ErrorDatosException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author emiim
 */
public class LibroDAOImpl implements LibroDAO{

    @Override
    public void guardar(Libro libro) throws ErrorDatosException {
        System.out.println("Guardando libro: " + libro.getIsbn());
         if (libro.getIsbn() == null || libro.getIsbn().isEmpty()) {
             throw new ErrorDatosException("ISBN no puede ser nulo o vacío");
         }
         if(libro.getId() == null) libro.setId(System.currentTimeMillis() % 10000);
    }

    @Override
    public Optional<Libro> buscarPorId(Long id) throws ErrorDatosException {
        System.out.println("Buscando libro por ID: " + id);
        if (id == 1L) {
             Libro libro = new Libro();
             libro.setId(1L);
             libro.setIsbn("12345");
             libro.setTitulo("Libro de Ejemplo");
             return Optional.of(libro);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Libro> buscarPorIsbn(String isbn) throws ErrorDatosException {
         System.out.println("Buscando libro por ISBN: " + isbn);
         return Optional.empty();
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) throws ErrorDatosException {
        System.out.println("Buscando libros por título: " + titulo);
        return new ArrayList<>();
    }

    @Override
    public List<Libro> buscarTodos() throws ErrorDatosException {
       System.out.println("Buscando todos los libros:");
        return new ArrayList<>();
    }

    @Override
    public void actualizar(Libro libro) throws ErrorDatosException {
       System.out.println("Actualizando libro: " + libro.getIsbn());
         if (libro.getId() == null) {
             throw new ErrorDatosException("No se puede actualizar libro sin ID");
         }
    }

    @Override
    public void eliminar(Long id) throws ErrorDatosException {
       System.out.println("Eliminando libro por ID: " + id);
    }

    @Override
    public void actualizarStock(String isbn, int nuevoStock) throws ErrorDatosException {
        System.out.println("Actualizando stock para ISBN: " + isbn + " a " + nuevoStock);
    }
    
}
