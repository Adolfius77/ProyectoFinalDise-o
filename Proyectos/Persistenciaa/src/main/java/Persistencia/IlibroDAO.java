/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOS.LibroDTO;
import expciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author USER
 */

public interface IlibroDAO {
    List<LibroDTO> obtenerTodosLosLibros()throws  PersistenciaException;
    LibroDTO obtenerLibrosPorIsbn(String isbn) throws PersistenciaException;
    List<LibroDTO> obtenerLibrosPorCategoria(String categoria)throws PersistenciaException;
    boolean agregarLibro(LibroDTO libro)throws PersistenciaException;
    boolean actualizarLibro(LibroDTO libroActualizado)throws PersistenciaException;
    boolean eliminarLibro(String isbn)throws PersistenciaException;
}
