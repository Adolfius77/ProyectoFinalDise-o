package Persistencia;

import DTOS.LibroDTO;
import expciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Collections; // Para synchronizedList si es necesario
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LibroDAOImplMemoria implements IlibroDAO {

   
    private static List<LibroDTO> listaGlobalDeLibros;

    
    static {
        listaGlobalDeLibros = Collections.synchronizedList(new ArrayList<>()); 
        cargarLibrosInicialesStatic(); 
        System.out.println("DAO memoria (static init): Lista global inicializada con: " + listaGlobalDeLibros.size() + " libros.");
    }

    public LibroDAOImplMemoria() {
       
        System.out.println("DAO memoria: Nueva instancia creada. Usando lista global con " + listaGlobalDeLibros.size() + " libros.");
    }


    private static void cargarLibrosInicialesStatic() {

        Date fecha1 = new GregorianCalendar(2023, 10, 15).getTime(); // Nota: Meses son 0-indexados, 10 es Noviembre
        Date fecha2 = new GregorianCalendar(2022, 5, 20).getTime();  // 5 es Junio
        Date fecha3 = new GregorianCalendar(2021, 2, 10).getTime();  // 2 es Marzo
        Date fecha4 = new GregorianCalendar(2020, 9, 5).getTime();   // 9 es Octubre
        Date fecha5 = new GregorianCalendar(2019, 11, 1).getTime(); // 11 es Diciembre
        Date fecha6 = new GregorianCalendar(2018, 7, 25).getTime();  // 7 es Agosto
        
     

        listaGlobalDeLibros.add(new LibroDTO("Las pruebas del sol", "Aiden Thomas", "978-6078828463", fecha1, "FANTASIA", 389.00, "Editorial", 47, 47, "/img/LasPruebasDelSol1.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Los juegos del hambre", "Suzanne Collins", "978-6074001907", fecha2, "FANTASIA", 379.00, "Editorial", 67, 67, "/img/losJuegosDelHambre1.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Harry Potter y la piedra filosofal", "J. K. Rowling", "978-6073193009", fecha3, "FANTASIA", 3229.00, "Editorial", 223, 23, "/img/harryPotter.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Splatoon Ikasu Artbook", "FAMITSU", "978-4047336551", fecha4, "FANTASIA", 229.00, "Editorial", 128, 23, "/img/SplatoonArtbook1.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Divergente", "Veronica Roth", "978-6074009842", fecha5, "FANTASIA", 4229.00, "Editorial", 525, 23, "/img/divergente1.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Amigo Imaginario", "Stephen Chbosky", "978-6070761515", fecha6, "FANTASIA", 429.00, "Editorial", 736, 2, "/img/amigoImaginario1.jpg"));
//
        listaGlobalDeLibros.add(new LibroDTO("IT", "Stephen King", "978-6073105521", fecha1, "TERROR", 439.00, "Editorial", 1138, 47, "/img/IT.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("El resplandor", "Stephen King", "978-6073118392", fecha2, "TERROR", 379.00, "Editorial", 447, 67, "/img/elResplandor1.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("La chicha de gris", "Antonio Runa", "978-8445014752", fecha3, "TERROR", 3229.00, "Editorial", 432, 23, "/img/laChicaDeGris1.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Casa de las sombras", "Adam Nevill", "978-8445014882", fecha4, "TERROR", 229.00, "Editorial", 480, 23, "/img/casaSombra.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Amigo imaginario", "Stephen Chbosky", "978-6070761515", fecha5, "TERROR", 4229.00, "Editorial", 736, 23, "/img/amigoImaginario1.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("El chico de piel de cerdo", "Raiza Revelles", "978-6070772245", fecha6, "TERROR", 429.00, "Editorial", 312, 2, "/img/ElChicoDeLaPielDeCerdo.jpg"));

//Libros Cocina
        listaGlobalDeLibros.add(new LibroDTO("Stardew Valley Cookbook", "ConcernedApe", "978-1984862051", fecha1, "COCINA", 616.00, "Editorial", 240, 4, "/img/StardewCookbook.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Oh, My Cookie!", "Noelia Toré", "978-8410442542", fecha2, "COCINA", 529.00, "Editorial", 224, 12, "/img/ohMyCookie.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Con las manos en la masa madre", "Bernardo Flores Alanís", "978-6072134058", fecha3, "COCINA", 569.00, "Editorial", 288, 43, "/img/masaMadre.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("El gran libro de la reposteria", "Christian Teubner", "978-8424108229", fecha4, "COCINA", 616.00, "Editorial", 648, 50, "/img/ElGranLibroDeLaReposteria.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Cocinologia: la ciencia de la cocina", "Stuart Farrimond", "978-1465486844", fecha5, "COCINA", 515.00, "Editorial", 256, 37, "/img/cocinologia.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("La ciencia de la pasteleria", "Dario Bressanini", "978-8417127077", fecha6, "COCINA", 900.00, "Editorial", 432, 30, "/img/laCienciaDeLaReposteria.jpg"));

//Libros Educacion
        listaGlobalDeLibros.add(new LibroDTO("El valor de educar", "Fernando Savater", "978-8434433960", fecha1, "EDUCACION", 691.00, "Editorial", 256, 60, "/img/elValorDeEducar.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Enseñar a transgredir", "Marta Malo", "978-8412281842", fecha2, "EDUCACION", 489.00, "Editorial", 208, 14, "/img/enseñarTransgredir.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Educar en la naturaleza", "Katia Hueso", "978-8418285936", fecha3, "EDUCACION", 719.00, "Editorial", 240, 19, "/img/educarNaturaleza.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("EducaFakes", "Daniel Turienzo", "978-8412878714", fecha4, "EDUCACION", 429.00, "Editorial", 160, 28, "/img/educaFakes.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("El arte de educar jugando", "Silvia Álava", "978-8412334296", fecha5, "EDUCACION", 706.00, "Editorial", 240, 28, "/img/elArteDeEducar.jpg"));
        listaGlobalDeLibros.add(new LibroDTO("Aprendiendo a aprender", "Hector Ruiz Martin", "978-8418045301", fecha6, "EDUCACION", 547.00, "Editorial", 208, 4, "/img/aprendiendoAprender.jpg"));
    }
    
    @Override
    public List<LibroDTO> obtenerTodosLosLibros() throws PersistenciaException {
        System.out.println("DAO memoria: devolviendo todos los " + listaGlobalDeLibros.size() + " libros de la lista global.");
        return new ArrayList<>(listaGlobalDeLibros); // Devuelve una copia para evitar modificaciones externas directas
    }

    @Override
    public List<LibroDTO> obtenerLibrosPorCategoria(String categoria) throws PersistenciaException {
        System.out.println("DAO memoria: filtrando por categoria '" + categoria + "' desde lista global.");
        if (categoria == null) {
            throw new PersistenciaException("La categoria no puede ser nula.");
        }
        return listaGlobalDeLibros.stream()
                .filter(libro -> categoria.equalsIgnoreCase(libro.getCategoria()))
                .collect(Collectors.toList());
    }

    @Override
    public LibroDTO obtenerLibrosPorIsbn(String isbn) throws PersistenciaException {
        System.out.println("DAO memoria: buscando por isbn '" + isbn + "' en lista global.");
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new PersistenciaException("El ISBN no puede estar vacío o nulo.");
        }
        return listaGlobalDeLibros.stream()
                .filter(libro -> isbn.equalsIgnoreCase(libro.getIsbn()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean agregarLibro(LibroDTO libroNuevo) throws PersistenciaException {
        if (libroNuevo == null || libroNuevo.getIsbn() == null || libroNuevo.getIsbn().trim().isEmpty()) {
            throw new PersistenciaException("El libro a agregar o su ISBN no pueden ser nulos/vacíos.");
        }
        // Sincronizar el acceso si vas a tener múltiples hilos (aunque en Swing suele ser uno para UI)
        synchronized (listaGlobalDeLibros) {
            boolean yaExiste = listaGlobalDeLibros.stream()
                    .anyMatch(libro -> libroNuevo.getIsbn().equals(libro.getIsbn()));
            if (yaExiste) {
                System.out.println("DAO memoria: No se agregó. Ya existe un libro con ISBN: " + libroNuevo.getIsbn());
                return false;
            } else {
                listaGlobalDeLibros.add(libroNuevo);
                System.out.println("DAO memoria: Libro agregado a lista global: " + libroNuevo.getTitulo() + " (ISBN: " + libroNuevo.getIsbn() + "). Total: " + listaGlobalDeLibros.size());
                return true;
            }
        }
    }

    @Override
    public boolean actualizarLibro(LibroDTO libroActualizado) throws PersistenciaException {
        if (libroActualizado == null || libroActualizado.getIsbn() == null || libroActualizado.getIsbn().trim().isEmpty()) {
            throw new PersistenciaException("Datos del libro inválidos para actualizar.");
        }
        synchronized (listaGlobalDeLibros) {
            for (int i = 0; i < listaGlobalDeLibros.size(); i++) {
                if (Objects.equals(libroActualizado.getIsbn(), listaGlobalDeLibros.get(i).getIsbn())) {
                    listaGlobalDeLibros.set(i, libroActualizado);
                    System.out.println("DAO memoria: Libro actualizado en lista global - ISBN " + libroActualizado.getIsbn());
                    return true;
                }
            }
        }
        System.out.println("DAO memoria: Libro no encontrado para actualizar en lista global - ISBN " + libroActualizado.getIsbn());
        return false;
    }

    @Override
    public boolean eliminarLibro(String isbn) throws PersistenciaException {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new PersistenciaException("El ISBN a eliminar no puede ser nulo o vacío.");
        }
        boolean eliminado;
        synchronized (listaGlobalDeLibros) {
            eliminado = listaGlobalDeLibros.removeIf(libro -> isbn.equals(libro.getIsbn()));
        }
        if (eliminado) {
            System.out.println("DAO memoria: Libro eliminado de lista global - ISBN " + isbn);
        } else {
            System.out.println("DAO memoria: Libro no encontrado para eliminar en lista global - ISBN " + isbn);
        }
        return eliminado;
    }
}