package Persistencia;

import DTOS.LibroDTO;
import expciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Collections;
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
        // Definición de fechas (meses son 0-indexados en GregorianCalendar: Enero=0, Diciembre=11)
        Date fecha1 = new GregorianCalendar(2023, 10, 15).getTime(); // Noviembre
        Date fecha2 = new GregorianCalendar(2022, 5, 20).getTime();  // Junio
        Date fecha3 = new GregorianCalendar(2021, 2, 10).getTime();  // Marzo
        Date fecha4 = new GregorianCalendar(2020, 9, 5).getTime();   // Octubre
        Date fecha5 = new GregorianCalendar(2019, 11, 1).getTime();  // Diciembre
        Date fecha6 = new GregorianCalendar(2018, 7, 25).getTime();  // Agosto

        // FANTASIA
        listaGlobalDeLibros.add(new LibroDTO("Las pruebas del sol", "Aiden Thomas", "978-6078828463", fecha1, "FANTASIA", 389.00, "VRYA", 400, 47, "/img/LasPruebasDelSol1.jpg",
                "Teo, un joven trans semidiós del sol, debe competir en una serie de pruebas mortales para demostrar su valía y salvar a su gente, mientras navega por el amor y la traición en un mundo de dioses y monstruos."));
        listaGlobalDeLibros.add(new LibroDTO("Los juegos del hambre", "Suzanne Collins", "978-6074001907", fecha2, "FANTASIA", 379.00, "Scholastic Press", 374, 67, "/img/losJuegosDelHambre1.jpg",
                "En las ruinas de lo que fue América del Norte, la nación de Panem obliga cada año a sus doce distritos a enviar un chico y una chica a competir en los Juegos del Hambre, un retorcido castigo por una rebelión pasada y una táctica de intimidación gubernamental televisada a nivel nacional."));
        listaGlobalDeLibros.add(new LibroDTO("Harry Potter y la piedra filosofal", "J. K. Rowling", "978-6073193009", fecha3, "FANTASIA", 229.00, "Salamandra", 256, 23, "/img/harryPotter.jpg",
                "Harry Potter descubre en su undécimo cumpleaños que es el hijo huérfano de dos poderosos hechiceros y que él mismo posee poderes mágicos únicos. Es invitado a asistir al Colegio Hogwarts de Magia y Hechicería, donde se embarca en la aventura de su vida."));
        listaGlobalDeLibros.add(new LibroDTO("Splatoon Ikasu Artbook", "FAMITSU", "978-4047336551", fecha4, "FANTASIA", 768.00, "Kadokawa", 320, 15, "/img/SplatoonArtbook1.jpg",
                "Sumérgete en el vibrante y caótico mundo de Splatoon con este artbook oficial. Descubre diseños de personajes, escenarios, armas y la cultura única de los Inklings y Octolings a través de ilustraciones detalladas y comentarios de los desarrolladores."));
        listaGlobalDeLibros.add(new LibroDTO("Divergente", "Veronica Roth", "978-6074009842", fecha5, "FANTASIA", 429.00, "RBA Molino", 487, 23, "/img/divergente1.jpg",
                "En una Chicago futurista dividida en cinco facciones, Tris Prior elige Osadía, una facción que valora la valentía por encima de todo. Pero Tris tiene un secreto: es Divergente, lo que significa que no encaja en ninguna facción y es considerada una amenaza para el sistema."));
        listaGlobalDeLibros.add(new LibroDTO("Amigo Imaginario", "Stephen Chbosky", "978-6070761515", fecha6, "FANTASIA", 503.00, "Planeta", 704, 2, "/img/amigoImaginario1.jpg",
                "Christopher, un niño con dislexia, se muda a un nuevo pueblo con su madre huyendo de una relación abusiva. Desaparece durante seis días en el bosque local y regresa ileso pero cambiado, convencido de que debe construir una casa en el árbol antes de Navidad, o algo terrible le sucederá a todos."));

        // TERROR
        listaGlobalDeLibros.add(new LibroDTO("IT", "Stephen King", "978-6073105521", fecha1, "TERROR", 439.00, "Debolsillo", 1504, 47, "/img/IT.jpg",
                "En el pequeño pueblo de Derry, Maine, siete niños se enfrentan a una entidad maligna que adopta la forma de sus peores miedos, principalmente un payaso llamado Pennywise. Años después, ya adultos, deben regresar para detenerlo de una vez por todas cuando la pesadilla resurge."));
        listaGlobalDeLibros.add(new LibroDTO("El resplandor", "Stephen King", "978-6073118392", fecha2, "TERROR", 379.00, "Debolsillo", 688, 67, "/img/elResplandor1.jpg",
                "Jack Torrance acepta un trabajo como cuidador de invierno en el aislado Hotel Overlook, con la esperanza de curar su bloqueo de escritor. Se muda con su esposa Wendy y su hijo Danny, quien posee habilidades psíquicas. Pronto, fuerzas siniestras en el hotel comienzan a influir en Jack, llevándolo a la locura."));
        listaGlobalDeLibros.add(new LibroDTO("La chica de gris", "Antonio Runa", "978-8445014752", fecha3, "TERROR", 428.00, "Minotauro", 432, 23, "/img/laChicaDeGris1.jpg",
                "Una entidad sombría conocida como la Chica de Gris acecha a los habitantes de un aislado pueblo costero, trayendo consigo pesadillas y desapariciones. Un grupo de extraños deberá desentrañar el misterio de su origen y encontrar una forma de detenerla antes de que sea demasiado tarde."));
        listaGlobalDeLibros.add(new LibroDTO("Casa de las sombras", "Adam Nevill", "978-8445014882", fecha4, "TERROR", 398.00, "Minotauro", 480, 18, "/img/casaSombra.jpg",
                "Tras heredar una casa aislada en el campo, un hombre descubre que la propiedad alberga oscuros secretos y presencias malévolas que amenazan su cordura y su vida."));
        // listaGlobalDeLibros.add(new LibroDTO("Amigo imaginario", "Stephen Chbosky", "978-6070761515", fecha5, "TERROR", 4229.00, "Editorial", 736, 23, "/img/amigoImaginario1.jpg", "Sinopsis duplicada/pendiente..."));
        listaGlobalDeLibros.add(new LibroDTO("El chico de piel de cerdo", "Raiza Revelles", "978-6070772245", fecha6, "TERROR", 228.00, "Planeta", 312, 10, "/img/ElChicoDeLaPielDeCerdo.jpg",
                "En un mundo donde algunas personas nacen con características animales, un joven con piel de cerdo lucha por encontrar su lugar mientras se enfrenta al prejuicio y a una conspiración que amenaza a los de su especie."));

        // COCINA
        listaGlobalDeLibros.add(new LibroDTO("Stardew Valley Cookbook", "ConcernedApe", "978-1984862051", fecha1, "COCINA", 616.00, "Random House Worlds", 240, 4, "/img/StardewCookbook.jpg",
                "Da vida a las deliciosas recetas del popular videojuego Stardew Valley. Desde platos de temporada hasta festines festivos, este libro de cocina te permite recrear los sabores de Pelican Town en tu propia cocina."));
        listaGlobalDeLibros.add(new LibroDTO("Oh, My Cookie!", "Noelia Toré", "978-8410442542", fecha2, "COCINA", 529.00, "Oberon", 224, 12, "/img/ohMyCookie.jpg",
                "Descubre el arte de crear galletas espectaculares con este libro lleno de recetas creativas, técnicas de decoración y consejos para hornear las galletas perfectas para cualquier ocasión."));
        listaGlobalDeLibros.add(new LibroDTO("Con las manos en la masa madre", "Bernardo Flores Alanís", "978-6072134058", fecha3, "COCINA", 569.00, "Larousse", 288, 43, "/img/masaMadre.jpg",
                "Aprende todos los secretos para cultivar tu propia masa madre y hornear panes artesanales deliciosos y saludables en casa, con instrucciones paso a paso y una variedad de recetas."));
        listaGlobalDeLibros.add(new LibroDTO("El gran libro de la reposteria", "Christian Teubner", "978-8424108229", fecha4, "COCINA", 616.00, "Everest", 648, 50, "/img/ElGranLibroDeLaReposteria.jpg",
                "Una enciclopedia completa de la repostería, con cientos de recetas clásicas y modernas, técnicas fundamentales y consejos de expertos para pasteleros de todos los niveles."));
        listaGlobalDeLibros.add(new LibroDTO("Cocinologia: la ciencia de la cocina", "Stuart Farrimond", "978-1465486844", fecha5, "COCINA", 515.00, "DK", 256, 37, "/img/cocinologia.jpg",
                "Explora los principios científicos detrás de la cocina. Este libro desmitifica técnicas culinarias y explica cómo los ingredientes interactúan para crear sabores y texturas perfectas."));
        listaGlobalDeLibros.add(new LibroDTO("La ciencia de la pasteleria", "Dario Bressanini", "978-8417127077", fecha6, "COCINA", 900.00, "Gribaudo", 432, 30, "/img/laCienciaDeLaReposteria.jpg",
                "Un viaje fascinante a través de la química y la física de la pastelería. Aprende por qué funcionan las recetas y cómo experimentar para crear tus propios postres innovadores."));

        // EDUCACION
        listaGlobalDeLibros.add(new LibroDTO("El valor de educar", "Fernando Savater", "978-8434433960", fecha1, "EDUCACION", 691.00, "Ariel", 256, 60, "/img/elValorDeEducar.jpg",
                "Una reflexión profunda sobre la importancia de la educación en la formación de ciudadanos críticos y libres, y el papel fundamental de los educadores en la sociedad contemporánea."));
        listaGlobalDeLibros.add(new LibroDTO("Enseñar a transgredir", "bell hooks", "978-8412281842", fecha2, "EDUCACION", 489.00, "Capitán Swing", 208, 14, "/img/enseñarTransgredir.jpg", // Corregido autor (originalmente Marta Malo)
                "bell hooks desafía las prácticas educativas tradicionales y propone una pedagogía comprometida con la libertad, la justicia social y la transformación personal y colectiva en el aula."));
        listaGlobalDeLibros.add(new LibroDTO("Educar en la naturaleza", "Katia Hueso", "978-8418285936", fecha3, "EDUCACION", 719.00, "Plataforma Editorial", 240, 19, "/img/educarNaturaleza.jpg",
                "Descubre los beneficios de la educación al aire libre y cómo conectar a los niños con el entorno natural para fomentar su desarrollo integral, creatividad y respeto por el medio ambiente."));
        listaGlobalDeLibros.add(new LibroDTO("EducaFakes", "Daniel Turienzo", "978-8412878714", fecha4, "EDUCACION", 429.00, "Agapea", 160, 28, "/img/educaFakes.jpg",
                "Una guía esencial para desarrollar el pensamiento crítico en la era digital, aprendiendo a identificar noticias falsas, desinformación y a navegar de forma segura y responsable en internet."));
        listaGlobalDeLibros.add(new LibroDTO("El arte de educar jugando", "Silvia Álava", "978-8412334296", fecha5, "EDUCACION", 706.00, "JdeJ Editores", 240, 28, "/img/elArteDeEducar.jpg",
                "Explora el poder del juego como herramienta educativa fundamental para el desarrollo cognitivo, emocional y social de los niños, con ideas prácticas y actividades lúdicas."));
        listaGlobalDeLibros.add(new LibroDTO("Aprendiendo a aprender", "Hector Ruiz Martin", "978-8418045301", fecha6, "EDUCACION", 547.00, "Ediciones B", 208, 4, "/img/aprendiendoAprender.jpg",
                "Un manual basado en la neurociencia y la psicología cognitiva que ofrece estrategias efectivas para mejorar el aprendizaje, la memoria y la comprensión en estudiantes de todas las edades."));
    }

    // ... (resto de tus métodos DAO)
    @Override
    public List<LibroDTO> obtenerTodosLosLibros() throws PersistenciaException {
        System.out.println("DAO memoria: devolviendo todos los " + listaGlobalDeLibros.size() + " libros de la lista global.");
        return new ArrayList<>(listaGlobalDeLibros); 
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
                    listaGlobalDeLibros.set(i, libroActualizado); // Actualiza el libro completo
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