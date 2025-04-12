/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fachada;

import DTOS.LibroDTO;
import Implementaciones.LibroDAOImpl;
import Modelo.Libro;
import Persistencia.LibroDAO;
import expciones.ErrorDatosException;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author riosr
 */
public class FachadaSistema {
    private LibroDAOImpl gestorLibros;
    //private LibroDAO gestorPersistencia;
    private UtilidadesSistema utilidades;

    public FachadaSistema() {
        this.gestorLibros = new LibroDAOImpl();
        this.utilidades = new UtilidadesSistema();
    }

    public void agregarLibro(Libro libro) throws ErrorDatosException {
        if (utilidades.validarDatos(libro)) {
            gestorLibros.guardar(libro);
            utilidades.logTransaccion("Libro agregado: " + libro.getTitulo());
        }
    }

    public Optional<Libro> buscarLibroID(Long id) throws ErrorDatosException {
        return gestorLibros.buscarPorId(id);
    }
    
    public List<Libro> buscarPorTitulo(String titulo) throws ErrorDatosException {
        return gestorLibros.buscarPorTitulo(titulo);
    }
    
    public List<Libro> buscarTodos() throws ErrorDatosException {
        return gestorLibros.buscarTodos();
    }

    public void actualizar(Libro libro) throws ErrorDatosException {
        if (utilidades.validarDatos(libro)) {
            gestorLibros.actualizar(libro);
            utilidades.logTransaccion("Libro agregado: " + libro.getTitulo());
        }
    }
    
    
    //public void guardarDatos(LibroDTO Libro) {
    //    gestorPersistencia.guardar(Libro.get);
    //    utilidades.logTransaccion("Datos de libros guardados");
    //}

    //public void cargarDatos() {
    //    gestorLibros.setListaLibros(gestorPersistencia.cargarDatos());
    //    utilidades.logTransaccion("Datos de libros cargados");
    //}
}
