/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOS.LibroDTO;
import expciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author USER
 */
public class LibroDAOImplMemoria implements IlibroDAO {

    private List<LibroDTO> listaDelibros;

    public LibroDAOImplMemoria() {
        this.listaDelibros = cargarLibrosIniciales();
        System.out.println("DAO memoria: inicializando con: " + this.listaDelibros.size() + "libros");
    }

    private List<LibroDTO> cargarLibrosIniciales() {
        List<LibroDTO> libros = new ArrayList<>();
        Date fecha1 = new GregorianCalendar(2023, 10, 15).getTime();
        Date fecha2 = new GregorianCalendar(2022, 5, 20).getTime();
        Date fecha3 = new GregorianCalendar(2021, 2, 10).getTime();
        Date fecha4 = new GregorianCalendar(2020, 9, 5).getTime();
        Date fecha5 = new GregorianCalendar(2019, 11, 1).getTime();
        Date fecha6 = new GregorianCalendar(2018, 7, 25).getTime();

        libros.add(new LibroDTO("Las pruebas del sol", "Aiden Thomas", "978-6078828463", fecha1, "FANTASIA", 389.00, 47, "/img/LasPruebasDelSol1.jpg"));
        libros.add(new LibroDTO("Los juegos del hambre", "Suzanne Collins", "978-6074001907", fecha2, "FANTASIA", 379.00, 67, "/img/losJuegosDelHambre1.jpg"));
        libros.add(new LibroDTO("Harry Potter y la piedra filosofal", "J. K. Rowling", "978-6073193009", fecha3, "FANTASIA", 3229.00, 23, "/img/harryPotter.jpg"));

        libros.add(new LibroDTO("Divergente", "Veronica Roth", "978-6074009842", fecha5, "FANTASIA", 4229.00, 23, "/img/divergente1.jpg"));
        libros.add(new LibroDTO("Amigo Imaginario", "Stephen Chbosky", "978-6070761515", fecha6, "FANTASIA", 429.00, 2, "/img/amigoImaginario1.jpg"));

        libros.add(new LibroDTO("IT", "Stephen King", "978-6073105521", fecha1, "TERROR", 439.00, 47, "/img/IT.jpg"));
        libros.add(new LibroDTO("El resplandor", "Stephen King", "978-6073118392", fecha2, "TERROR", 379.00, 67, "/img/elResplandor1.jpg"));
        libros.add(new LibroDTO("La chicha de gris", "Antonio Runa", "978-8445014752", fecha3, "TERROR", 3229.00, 23, "/img/laChicaDeGris1.jpg"));
        libros.add(new LibroDTO("Casa de las sombras", "Adam Nevill", "978-8445014882", fecha4, "TERROR", 229.00, 23, "/img/casaSombra.jpg"));

        libros.add(new LibroDTO("El chico de piel de cerdo", "Raiza Revelles", "978-6070772245", fecha6, "TERROR", 429.00, 2, "/img/ElChicoDeLaPielDeCerdo.jpg"));

        return libros;
    }

    @Override
    public List<LibroDTO> obtenerTodosLosLibros() throws PersistenciaException {
        System.out.println("DAO memoria: devolviendo todo los " + this.listaDelibros + "libros");
        return new ArrayList<>(this.listaDelibros);
    }

    @Override
    public List<LibroDTO> obtenerLibrosPorCategoria(String categoria) throws PersistenciaException {
        System.out.println("DAO memoria: filtrando por categoria: " + categoria);
        if (categoria == null) {
            throw new PersistenciaException("La categoria no puede ser nula");
        }
        return this.listaDelibros.stream()
                .filter(libro -> categoria.equalsIgnoreCase(libro.getCategoria()))
                .collect(Collectors.toList());
    }

    @Override
    public LibroDTO obtenerLibrosPorIsbn(String isbn) throws PersistenciaException {
        System.out.println("DAO memoria: buscando por isbn: " + isbn);
        if (isbn == null && isbn.trim().isEmpty()) {
            throw new PersistenciaException("el isbn no puede estar vacio o nulo:)");
        }
        return this.listaDelibros.stream()
                .filter(libro -> isbn.equalsIgnoreCase(libro.getIsbn()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean agregarLibro(LibroDTO libroNuevo) throws PersistenciaException {
        if (libroNuevo == null || libroNuevo.getIsbn() == null || libroNuevo.getIsbn().trim().isEmpty()) {
            throw new PersistenciaException("el libro no se puede agregar no puede ser nulo");
        }
        boolean yaExiste = this.listaDelibros.stream()
                .anyMatch(libro -> libroNuevo.getIsbn().equals(libro.getIsbn()));
        if (yaExiste) {
            System.out.println("DAO memoria: no se agrego: ya existe un libro con isbn: " + libroNuevo.getIsbn());
            return false;
        } else {
            this.listaDelibros.add(libroNuevo);
            System.out.println("DAO memoria: Libro agregado:" + libroNuevo.getTitulo() + " (isbn: " + libroNuevo.getIsbn() + ")");
            return true;
        }
    }

    @Override
    public boolean actualizarLibro(LibroDTO libroActualizado) throws PersistenciaException {
        if (libroActualizado == null || libroActualizado.getIsbn() == null || libroActualizado.getIsbn().trim().isEmpty()) {
            throw new PersistenciaException("datos del libro invalidos para actualizar");
        }
        for (int i = 0; i < listaDelibros.size(); i++) {
            if (Objects.equals(libroActualizado.getIsbn(), listaDelibros.get(i).getIsbn())) {
                listaDelibros.set(i, libroActualizado);
                System.out.println("DAO memoria: libro actualizado -isbn "+ libroActualizado.getIsbn());
                return true;
            }
        }
        System.out.println("DAO memoria: libro no encontrado para actualizar -isbn " + libroActualizado.getIsbn());
        return  false;
    }
    @Override
    public boolean eliminarLibro(String isbn)throws PersistenciaException{
        if(isbn == null || isbn.trim().isEmpty()){
            throw new PersistenciaException(" el isbn a eliminar no puede ser nulo");
        }
        boolean eliminado = this.listaDelibros.removeIf(libro -> isbn.equals(libro.getIsbn()));
        
        if(eliminado){
            System.out.println("DAO memoria: libro eliminado -isbn " + isbn);
        }else{
            System.out.println("DAO memoria: libro no econtrado para eliminar -isbn "+ isbn);
        }
        return  eliminado;
    }
}
