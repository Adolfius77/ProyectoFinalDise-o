/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Libro;
import expciones.ErrorDatosException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author emiim
 */
public interface LibroDAO {
    void guardar(Libro libro) throws ErrorDatosException;
    Optional<Libro> buscarPorId(Long id) throws ErrorDatosException;
    Optional<Libro> buscarPorIsbn(String isbn) throws ErrorDatosException;
    List<Libro> buscarPorTitulo(String titulo) throws ErrorDatosException;
    List<Libro> buscarTodos() throws ErrorDatosException;
    void actualizar(Libro libro) throws ErrorDatosException;
    void eliminar(Long id) throws ErrorDatosException;
    void actualizarStock(String isbn, int nuevoStock) throws ErrorDatosException;
}

