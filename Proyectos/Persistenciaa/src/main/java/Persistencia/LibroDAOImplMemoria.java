package Persistencia;

import DTOS.LibroDTO;
import expciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Arrays;
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

        // --- FANTASIA ---
        listaGlobalDeLibros.add(new LibroDTO("Las pruebas del sol", "Aiden Thomas", "978-6078828463", fecha1, "FANTASIA", 389.00, "VRYA", 400, 47, "/img/LasPruebasDelSol1.jpg",
                "Teo, un joven trans semidiós del sol, debe competir en una serie de pruebas mortales para demostrar su valía y salvar a su gente, mientras navega por el amor y la traición en un mundo de dioses y monstruos.",
                Arrays.asList(
                        "¡Increíble! Una historia que te atrapa. - Lector123", "Me encantó la representación. - FanDeFantasia", "Un poco lento al principio, pero el final vale la pena. - CriticoAmateur",
                        "Teo es un personaje admirable. ¡Quiero más! - Sol brillante", "La mitología es fascinante. Muy recomendado. - MitologoX", "Una aventura épica llena de giros inesperados. - AventureroLector",
                        "El desarrollo de personajes es profundo y conmovedor. - Emi", "Lloré, reí y me emocioné. Una joya. - CorazónDeLibro", "No pude dejar de leerlo. Perfecto para jóvenes adultos. - Bookworm99",
                        "Este libro se quedará conmigo por mucho tiempo. - LectorNocturno"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Los juegos del hambre", "Suzanne Collins", "978-6074001907", fecha2, "FANTASIA", 379.00, "Scholastic Press", 374, 67, "/img/losJuegosDelHambre1.jpg",
                "En las ruinas de Panem, cada distrito debe enviar tributos a un evento televisado mortal. Katniss Everdeen se ofrece voluntaria para salvar a su hermana y se convierte en un símbolo de esperanza y rebelión.",
                Arrays.asList(
                        "Un clásico distópico que sigue siendo relevante. - DistritoFan", "Katniss es una heroína inolvidable. - ChicaEnLlamas", "Te mantiene en vilo hasta la última página. - TributoFiel",
                        "Una crítica social muy inteligente. - Pensador22", "Me hizo reflexionar mucho sobre nuestra sociedad. - LectorConsciente", "La tensión es palpable en cada capítulo. - AdictoAlSuspense",
                        "Una lectura obligada para los amantes del género. - DistopiaHoy", "Personajes complejos y una trama adictiva. - FanDeCollins", "El mundo de Panem es aterrador y fascinante. - ViajeroLiterario",
                        "Simplemente brillante. Lo releo cada año. - EternoAdmirador"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Harry Potter y la piedra filosofal", "J. K. Rowling", "978-6073193009", fecha3, "FANTASIA", 229.00, "Salamandra", 256, 23, "/img/harryPotter.jpg",
                "Harry Potter descubre en su undécimo cumpleaños que es un mago y está destinado a asistir al Colegio Hogwarts de Magia y Hechicería, donde desentraña el misterio de sus padres y su conexión con el temido Lord Voldemort.",
                Arrays.asList(
                        "El inicio de una saga mágica inolvidable. - PotterheadDesdeSiempre", "Hogwarts se siente como un hogar. - Gryffindor orgulloso", "Un mundo lleno de maravillas y amistad. - MagiaPura",
                        "J.K. Rowling creó un universo increíble. - FanNumero1", "Perfecto para leer a cualquier edad. - JovenDeCorazon", "Los personajes son entrañables. - AmigoDeHarry",
                        "Una aventura que te transporta a otro mundo. - EscapistaLiterario", "La magia de este libro es real. - CreyenteEnLaMagia", "Un libro que define una generación. - LectorX",
                        "Siempre volveré a Hogwarts. - LealtadMágica"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Splatoon Ikasu Artbook", "FAMITSU", "978-4047336551", fecha4, "FANTASIA", 768.00, "Kadokawa", 320, 15, "/img/SplatoonArtbook1.jpg",
                "Sumérgete en el vibrante y caótico mundo de Splatoon con este artbook oficial. Descubre diseños de personajes, escenarios, armas y la cultura única de los Inklings y Octolings a través de ilustraciones detalladas y comentarios de los desarrolladores.",
                Arrays.asList(
                        "¡Imprescindible para fans de Splatoon! - InklingFan", "El arte es espectacular, lleno de color. - ArtistaUrbano", "Muchos detalles y bocetos interesantes. - Coleccionista",
                        "Me encanta ver el proceso creativo. - GamerCreativo", "Perfecto para inspirarse. - DiseñadorGráfico", "Una joya visual, cada página es una obra de arte. - OjoAvizor",
                        "Si amas el juego, amarás este libro. - SplatFanatic", "Horas de diversión explorando los diseños. - CuriosoNivel100", "Excelente calidad de impresión y contenido. - BibliofiloGamer",
                        "¡Woomy! El mejor artbook. - CalamarEntusiasta"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Divergente", "Veronica Roth", "978-6074009842", fecha5, "FANTASIA", 429.00, "RBA Molino", 487, 23, "/img/divergente1.jpg",
                "En una Chicago futurista dividida en cinco facciones, Tris Prior elige Osadía, una facción que valora la valentía por encima de todo. Pero Tris tiene un secreto: es Divergente, lo que significa que no encaja en ninguna facción y es considerada una amenaza para el sistema.",
                Arrays.asList(
                        "Adictivo y emocionante. - LectorAudaz", "Tris es un personaje fuerte e inspirador. - FanDeTris", "El concepto de las facciones es muy original. - SociologoAmateur",
                        "Un romance que te acelera el corazón. - CorazonValiente", "Lleno de acción y sorpresas. - AdictoALaAdrenalina", "Me identifiqué mucho con la lucha de Tris. - AlmaLibre",
                        "Una distopía juvenil que destaca. - ExpertoEnYA", "El mundo está muy bien construido. - ArquitectoDeSueños", "No puedes parar de leer hasta saber qué pasa. - NocheEnVela",
                        "Recomendado para una lectura rápida y emocionante. - VelozLector"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Amigo Imaginario", "Stephen Chbosky", "978-6070761515", fecha6, "FANTASIA", 503.00, "Planeta", 704, 2, "/img/amigoImaginario1.jpg",
                "Christopher, un niño con dislexia, se muda a un nuevo pueblo con su madre huyendo de una relación abusiva. Desaparece durante seis días en el bosque local y regresa ileso pero cambiado, convencido de que debe construir una casa en el árbol antes de Navidad, o algo terrible le sucederá a todos.",
                Arrays.asList(
                        "Intrigante y con un toque oscuro. - MisterioFan", "Chbosky sabe cómo tocar fibras sensibles. - LectorEmocional", "Una mezcla de fantasía y thriller psicológico. - MenteCuriosa",
                        "Los personajes infantiles son muy realistas. - ObservadorDetallista", "Te hace cuestionar la realidad. - FilósofoEnCiernes", "El bosque es casi un personaje más. - AmanteDeLaNaturaleza",
                        "Un libro extenso pero que engancha. - DevoradorDePáginas", "El final me dejó pensando días. - ReflexivoTotal", "Una narrativa poderosa y evocadora. - EscritorAdmirador",
                        "No es lo que esperaba, ¡pero me sorprendió gratamente! - SorprendidoLector"
                )));

        // --- TERROR ---
        listaGlobalDeLibros.add(new LibroDTO("IT", "Stephen King", "978-6073105521", fecha1, "TERROR", 439.00, "Debolsillo", 1504, 47, "/img/IT.jpg",
                "En el pequeño pueblo de Derry, Maine, siete niños se enfrentan a una entidad maligna que adopta la forma de sus peores miedos, principalmente un payaso llamado Pennywise. Años después, ya adultos, deben regresar para detenerlo de una vez por todas cuando la pesadilla resurge.",
                Arrays.asList(
                        "El maestro del terror en su máxima expresión. - KingFanático", "Pennywise es aterrador. No dormí en días. - PesadillasGarantizadas", "Una historia épica sobre la amistad y el miedo. - ClubDeLosPerdedores",
                        "Extenso pero cada página vale la pena. - LectorValiente", "Los horrores de la infancia y la adultez. - PsicólogoAficionado", "Derry es un pueblo maldito inolvidable. - TuristaDeLoMacabro",
                        "Me hizo saltar varias veces. - SustosReales", "Un clásico que hay que leer con la luz encendida. - MiedosoPeroLeal", "La complejidad de la criatura es fascinante. - InvestigadorDeMonstruos",
                        "Stephen King nunca decepciona. - HorrorAdicto"
                )));
        listaGlobalDeLibros.add(new LibroDTO("El resplandor", "Stephen King", "978-6073118392", fecha2, "TERROR", 379.00, "Debolsillo", 688, 67, "/img/elResplandor1.jpg",
                "Jack Torrance acepta un trabajo como cuidador de invierno en el aislado Hotel Overlook, con la esperanza de curar su bloqueo de escritor. Se muda con su esposa Wendy y su hijo Danny, quien posee habilidades psíquicas. Pronto, fuerzas siniestras en el hotel comienzan a influir en Jack, llevándolo a la locura.",
                Arrays.asList(
                        "Terror psicológico de alta calidad. - MenteAtormentada", "El Hotel Overlook es un personaje en sí mismo. - ArquitectoDePesadillas", "La transformación de Jack es aterradora. - ObservadorDeLaLocura",
                        "Danny y su 'resplandor' son clave. - FanDelMisterio", "Un ambiente claustrofóbico y opresivo. - AgorafóbicoLiterario", "Mucho mejor que la película (aunque la película es buena). - PuristaDelLibro",
                        "Te hiela la sangre lentamente. - LectorPacienteDelHorror", "Una obra maestra del horror moderno. - AcadémicoDelTerror", "Sentí el aislamiento y la desesperación. - EmpáticoTotal",
                        "REDRUM. Suficiente dicho. - EscribaAlRevés"
                )));
        listaGlobalDeLibros.add(new LibroDTO("La chica de gris", "Antonio Runa", "978-8445014752", fecha3, "TERROR", 428.00, "Minotauro", 432, 23, "/img/laChicaDeGris1.jpg",
                "Una entidad sombría conocida como la Chica de Gris acecha a los habitantes de un aislado pueblo costero, trayendo consigo pesadillas y desapariciones. Un grupo de extraños deberá desentrañar el misterio de su origen y encontrar una forma de detenerla antes de que sea demasiado tarde.",
                Arrays.asList(
                        "Terror lovecraftiano con sabor español. - TentáculosFan", "Atmósfera increíblemente lograda. - CazadorDeNiebla", "Un misterio que te atrapa. - DetectiveDeLoOculto",
                        "Personajes bien construidos y creíbles. - AnalistaDePsiques", "Final impactante y desolador. - AmanteDeLosFinalesFuertes", "Me recordó a los clásicos del género. - NostálgicoDelHorror",
                        "La descripción del pueblo es muy vívida. - ViajeroImaginario", "Un miedo sutil pero persistente. - ExpertoEnEscalofríos", "Runa sabe cómo crear tensión. - MaestroDelSuspenseLector",
                        "Recomendado para los que buscan algo diferente. - LectorExplorador"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Casa de las sombras", "Adam Nevill", "978-8445014882", fecha4, "TERROR", 398.00, "Minotauro", 480, 18, "/img/casaSombra.jpg",
                "Tras heredar una casa aislada en el campo, un hombre descubre que la propiedad alberga oscuros secretos y presencias malévolas que amenazan su cordura y su vida, obligándolo a confrontar un pasado terrorífico.",
                Arrays.asList(
                        "Terror atmosférico en su máxima expresión. - FanDelFolkHorror", "Te sientes atrapado en la casa con el protagonista. - ClaustroLector", "Descripciones muy visuales y perturbadoras. - ArtistaMacabro",
                        "Un horror que se cuece a fuego lento. - GourmetDelMiedo", "Final que te deja sin aliento. - ImpactadoTotal", "Nevill es un maestro creando atmósferas opresivas. - SeguidorDeNevill",
                        "La sensación de aislamiento es palpable. - ErmitañoLiterario", "Criaturas y horrores muy originales. - CriptozoólogoAficionado", "No apto para leer de noche y solo. - ConsejoAmigo",
                        "Una experiencia de lectura visceral. - LectorIntenso"
                )));
        listaGlobalDeLibros.add(new LibroDTO("El chico de piel de cerdo", "Raiza Revelles", "978-6070772245", fecha6, "TERROR", 228.00, "Planeta", 312, 10, "/img/ElChicoDeLaPielDeCerdo.jpg",
                "En un mundo donde algunas personas nacen con características animales, un joven con piel de cerdo lucha por encontrar su lugar mientras se enfrenta al prejuicio y a una conspiración que amenaza a los de su especie, mezclando elementos de body horror y crítica social.",
                Arrays.asList(
                        "Original y perturbador. - LectorAtrevido", "Una metáfora social muy potente. - CríticoSocial", "El 'body horror' está muy bien logrado. - FanDeCronenberg",
                        "Personajes marginados que buscan aceptación. - AlmaIncomprendida", "Te hace pensar sobre la 'normalidad'. - CuestionadorDeNormas", "Una historia oscura con un mensaje profundo. - BuscadorDeSignificados",
                        "Raiza no teme explorar temas difíciles. - SeguidorDeRaiza", "Visualmente muy potente, aunque a veces gráfico. - EstetaDeLoExtraño", "Un terror diferente y necesario. - ProDiversidadLiteraria",
                        "Me dejó con la piel de gallina (sin juego de palabras). - ConmovidoYAsustado"
                )));

        // --- COCINA ---
        listaGlobalDeLibros.add(new LibroDTO("Stardew Valley Cookbook", "ConcernedApe", "978-1984862051", fecha1, "COCINA", 616.00, "Random House Worlds", 240, 4, "/img/StardewCookbook.jpg",
                "Da vida a las deliciosas recetas del popular videojuego Stardew Valley. Desde platos de temporada hasta festines festivos, este libro de cocina te permite recrear los sabores de Pelican Town en tu propia cocina.",
                Arrays.asList(
                        "¡Perfecto para fans del juego! - GranjeroGourmet", "Las recetas son fáciles de seguir y deliciosas. - CocineroNovato", "Las ilustraciones son encantadoras. - FoodieConEstilo",
                        "Me encanta poder cocinar lo que cultivo en el juego. - VidaDeGranjaReal", "Un regalo ideal para cualquier jugador de Stardew. - RegaladorEstrella", "Trae la magia del Valle a tu mesa. - ChefDePelicanTown",
                        "Muy bien organizado y con fotos apetitosas. - OrganizadorCulinario", "Recetas para todas las estaciones y ocasiones. - PlanificadorDeComidas", "¡Mis hijos ahora quieren ayudar en la cocina! - PadreFeliz",
                        "Una forma deliciosa de conectar más con el juego. - GamerSibarita"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Oh, My Cookie!", "Noelia Toré", "978-8410442542", fecha2, "COCINA", 529.00, "Oberon", 224, 12, "/img/ohMyCookie.jpg",
                "Descubre el arte de crear galletas espectaculares con este libro lleno de recetas creativas, técnicas de decoración y consejos para hornear las galletas perfectas para cualquier ocasión.",
                Arrays.asList(
                        "¡Inspiración pura para los amantes de las galletas! - CookieMonster", "Recetas originales y muy bien explicadas. - ReposteroCasero", "Las fotos son una tentación. - DulceAdicción",
                        "Aprendí muchos trucos de decoración. - ArtistaDeLaManga", "Mis galletas nunca se vieron tan bien. - OrgullosoHondero", "Un libro precioso y muy útil. - RegaloPerfectoParaReposteros",
                        "Desde galletas sencillas hasta obras de arte. - VersatilidadEnGalletas", "Noelia es una maestra galletera. - FanDeNoelia", "Ahora hago galletas para todas las fiestas. - FiesteroConGalletas",
                        "¡Una delicia de libro! - CatadorExigente"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Con las manos en la masa madre", "Bernardo Flores Alanís", "978-6072134058", fecha3, "COCINA", 569.00, "Larousse", 288, 43, "/img/masaMadre.jpg",
                "Aprende todos los secretos para cultivar tu propia masa madre y hornear panes artesanales deliciosos y saludables en casa, con instrucciones paso a paso y una variedad de recetas.",
                Arrays.asList(
                        "La guía definitiva para la masa madre. - PanaderoArtesanal", "Instrucciones claras y precisas. - CientíficoDelPan", "Mis panes han mejorado muchísimo. - OrgullosoDeMiHorno",
                        "Un libro que te acompaña en todo el proceso. - PacienteConLaLevadura", "Recetas variadas y deliciosas. - ExploradorDeSabores", "Entender la masa madre nunca fue tan fácil. - MaestroPanaderoEnPotencia",
                        "La satisfacción de hornear tu propio pan es inigualable. - FilosofíaDelPan", "Imprescindible si te inicias en este mundo. - NovatoEntusiasta", "Bernardo explica con pasión y conocimiento. - DiscípuloDeBernardo",
                        "¡Adiós al pan industrial! - SaludYTradición"
                )));
        listaGlobalDeLibros.add(new LibroDTO("El gran libro de la reposteria", "Christian Teubner", "978-8424108229", fecha4, "COCINA", 616.00, "Everest", 648, 50, "/img/ElGranLibroDeLaReposteria.jpg",
                "Una enciclopedia completa de la repostería, con cientos de recetas clásicas y modernas, técnicas fundamentales y consejos de expertos para pasteleros de todos los niveles.",
                Arrays.asList(
                        "Una verdadera biblia para los reposteros. - ChefPatissier", "Técnicas explicadas al detalle. - PerfeccionistaDulce", "Desde lo básico hasta lo más elaborado. - AprendizEterno",
                        "Recetas de todo el mundo. - ViajeroGastronómico", "Un libro de consulta indispensable. - ReposteroProfesional", "Las fotografías son de alta calidad. - EstetaCulinario",
                        "Perfecto para aprender y para inspirarse. - CreativoEnLaCocina", "Un clásico que no pasa de moda. - GuardiánDeLasRecetas", "Ideal para regalar a cualquier amante de los postres. - DetallistaGeneroso",
                        "Con este libro, ¡cualquiera puede ser un gran repostero! - OptimistaConRodillo"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Cocinologia: la ciencia de la cocina", "Stuart Farrimond", "978-1465486844", fecha5, "COCINA", 515.00, "DK", 256, 37, "/img/cocinologia.jpg",
                "Explora los principios científicos detrás de la cocina. Este libro desmitifica técnicas culinarias y explica cómo los ingredientes interactúan para crear sabores y texturas perfectas.",
                Arrays.asList(
                        "¡Entender el porqué de la cocina! - CientíficoCulinario", "Muy didáctico e interesante. - CuriosoPorNaturaleza", "Explica conceptos complejos de forma sencilla. - ProfesorGastronómico",
                        "Ideal para los que les gusta experimentar. - AlquimistaDeSabores", "Mejoró mi intuición en la cocina. - ChefIntuitivo", "Un enfoque diferente y muy útil. - InnovadorEnLosFogones",
                        "Lleno de gráficos e ilustraciones claras. - VisualmenteAtractivo", "Para cocineros que quieren ir más allá de la receta. - ChefInvestigador", "Aprendí muchísimo sobre los alimentos. - NutricionistaAficionado",
                        "Un libro fascinante que cambia tu forma de ver la cocina. - MenteAbierta"
                )));
        listaGlobalDeLibros.add(new LibroDTO("La ciencia de la pasteleria", "Dario Bressanini", "978-8417127077", fecha6, "COCINA", 900.00, "Gribaudo", 432, 30, "/img/laCienciaDeLaReposteria.jpg",
                "Un viaje fascinante a través de la química y la física de la pastelería. Aprende por qué funcionan las recetas y cómo experimentar para crear tus propios postres innovadores.",
                Arrays.asList(
                        "Para los nerds de la repostería, ¡genial! - PasteleroCientífico", "Bressanini es un genio explicando la ciencia. - DivulgadorAdmirado", "Entendí por fin muchos procesos. - IluminadoRepostero",
                        "Te da las herramientas para crear tus propias recetas. - InventorDePostres", "Riguroso pero accesible. - AcadémicoDulce", "Un libro que todo pastelero serio debería tener. - ProfesionalExigente",
                        "Abre un mundo de posibilidades creativas. - ArtistaDelAzúcar", "La química nunca fue tan deliciosa. - QuímicoGourmet", "Transforma tu forma de entender la pastelería. - RevolucionarioDelHorno",
                        "Imprescindible para los que buscan la perfección. - DetallistaRepostero"
                )));

        // --- EDUCACION ---
        listaGlobalDeLibros.add(new LibroDTO("El valor de educar", "Fernando Savater", "978-8434433960", fecha1, "EDUCACION", 691.00, "Ariel", 256, 60, "/img/elValorDeEducar.jpg",
                "Una reflexión profunda sobre la importancia de la educación en la formación de ciudadanos críticos y libres, y el papel fundamental de los educadores en la sociedad contemporánea.",
                Arrays.asList(
                        "Un ensayo fundamental sobre la educación. - FilósofoDeCabecera", "Savater siempre lúcido y provocador. - PensadorCrítico", "Para reflexionar sobre nuestro rol como educadores. - DocenteComprometido",
                        "Un llamado a humanizar la enseñanza. - HumanistaConvencido", "Lectura esencial para padres y maestros. - GuíaParaEducar", "Claro, conciso y muy necesario. - LectorPragmático",
                        "Te hace repensar el propósito de la educación. - ReformadorEducativo", "Un clásico contemporáneo sobre pedagogía. - HistoriadorDeLaEducación", "Inspirador y lleno de sabiduría. - AlmaSabia",
                        "Debería ser lectura obligatoria en magisterio. - FuturoProfesor"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Enseñar a transgredir", "bell hooks", "978-8412281842", fecha2, "EDUCACION", 489.00, "Capitán Swing", 208, 14, "/img/enseñarTransgredir.jpg",
                "bell hooks desafía las prácticas educativas tradicionales y propone una pedagogía comprometida con la libertad, la justicia social y la transformación personal y colectiva en el aula.",
                Arrays.asList(
                        "Una obra revolucionaria sobre la pedagogía crítica. - EducadorProgresista", "Para descolonizar la mente y el aula. - ActivistaAcadémico", "Inspirador para crear espacios de aprendizaje liberadores. - FacilitadorDelCambio",
                        "Un llamado a la enseñanza como práctica de la libertad. - SeguidorDeFreire", "Lectura fundamental para entender la interseccionalidad en educación. - TeóricoSocial", "Desafía y motiva a ser un mejor educador. - DocenteEnConstrucción",
                        "Una voz poderosa y necesaria. - FeministaAcadémica", "Te invita a cuestionar el status quo educativo. - RebeldeConCausa", "Para construir aulas más justas y equitativas. - ConstructorDePuentes",
                        "Un libro que transforma tu visión de la enseñanza. - MenteExpandida"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Educar en la naturaleza", "Katia Hueso", "978-8418285936", fecha3, "EDUCACION", 719.00, "Plataforma Editorial", 240, 19, "/img/educarNaturaleza.jpg",
                "Descubre los beneficios de la educación al aire libre y cómo conectar a los niños con el entorno natural para fomentar su desarrollo integral, creatividad y respeto por el medio ambiente.",
                Arrays.asList(
                        "¡Más naturaleza y menos pantallas! - MadreConectada", "Ideas prácticas para llevar el aprendizaje al exterior. - GuíaDeAireLibre", "Fundamental para el desarrollo saludable de los niños. - PediatraConsciente",
                        "Un respiro necesario en la era digital. - EcologistaEducador", "La naturaleza como la mejor aula. - MaestroExplorador", "Inspirador para reconectar con nuestro entorno. - AmanteDelPlaneta",
                        "Beneficios probados para el cuerpo y la mente. - CientíficoNaturalista", "Para padres y educadores que buscan alternativas. - InnovadorPedagógico", "Un libro lleno de respeto por la infancia y la Tierra. - AlmaVerde",
                        "¡A jugar afuera se ha dicho! - NiñoInteriorFeliz"
                )));
        listaGlobalDeLibros.add(new LibroDTO("EducaFakes", "Daniel Turienzo", "978-8412878714", fecha4, "EDUCACION", 429.00, "Agapea", 160, 28, "/img/educaFakes.jpg",
                "Una guía esencial para desarrollar el pensamiento crítico en la era digital, aprendiendo a identificar noticias falsas, desinformación y a navegar de forma segura y responsable en internet.",
                Arrays.asList(
                        "Imprescindible en tiempos de desinformación. - VerificadorDeHechos", "Herramientas prácticas para no caer en engaños. - InternautaPrecavido", "Para educar ciudadanos digitales responsables. - CiberGuía",
                        "Fomenta el pensamiento crítico desde temprana edad. - MentorJuvenil", "Claro y directo al grano. - LectorEficiente", "Muy útil para profesores y padres. - EscudoAntiFakes",
                        "Aprende a contrastar fuentes y a dudar sanamente. - InvestigadorDigital", "La alfabetización mediática es clave hoy en día. - ComunicadorConsciente", "Un libro para estar alerta y bien informado. - CiudadanoActivo",
                        "¡Que no te la cuelen! - DetectiveDeLaVerdad"
                )));
        listaGlobalDeLibros.add(new LibroDTO("El arte de educar jugando", "Silvia Álava", "978-8412334296", fecha5, "EDUCACION", 706.00, "JdeJ Editores", 240, 28, "/img/elArteDeEducar.jpg",
                "Explora el poder del juego como herramienta educativa fundamental para el desarrollo cognitivo, emocional y social de los niños, con ideas prácticas y actividades lúdicas.",
                Arrays.asList(
                        "El juego es el trabajo de la infancia. - DefensorDelJuego", "Ideas geniales para aprender divirtiéndose. - PadreCreativo", "Una perspectiva muy valiosa sobre el desarrollo infantil. - PsicólogoInfantil",
                        "Recuperar el valor del juego libre y estructurado. - LudoEducador", "Para conectar con nuestros hijos a través del juego. - FamiliaJugona", "Un libro lleno de ternura y sabiduría práctica. - GuíaAmable",
                        "Actividades para todas las edades y momentos. - PlanificadorLúdico", "Fomenta la creatividad y la inteligencia emocional. - CoachDeVidaInfantil", "Silvia Álava es una referente en crianza. - SeguidorDeSilvia",
                        "¡Jugar es aprender, aprender es jugar! - FilosofíaLúdica"
                )));
        listaGlobalDeLibros.add(new LibroDTO("Aprendiendo a aprender", "Hector Ruiz Martin", "978-8418045301", fecha6, "EDUCACION", 547.00, "Ediciones B", 208, 4, "/img/aprendiendoAprender.jpg",
                "Un manual basado en la neurociencia y la psicología cognitiva que ofrece estrategias efectivas para mejorar el aprendizaje, la memoria y la comprensión en estudiantes de todas las edades.",
                Arrays.asList(
                        "¡La ciencia detrás del aprendizaje efectivo! - NeuroEducador", "Estrategias prácticas y basadas en evidencia. - EstudianteEficaz", "Para optimizar tu tiempo de estudio. - ProductividadMáxima",
                        "Desmonta mitos sobre el aprendizaje. - CazadorDeMitosEducativos", "Un libro que todo estudiante debería leer. - GuíaDelEstudianteExitoso", "Mejorar tus técnicas de estudio es posible. - CoachAcadémico",
                        "Explicaciones claras sobre cómo funciona nuestro cerebro al aprender. - DivulgadorCientífico", "Aplicable a cualquier área del conocimiento. - AprendizUniversal", "Invierte en tu capacidad de aprender. - FuturoBrillante",
                        "¡Estudiar de forma inteligente, no solo dura! - EstrategaDelSaber"
                )));
    }

    // ... (resto de tus métodos DAO: obtenerTodosLosLibros, etc.)
    @Override
    public List<LibroDTO> obtenerTodosLosLibros() throws PersistenciaException {
        // Devuelve una copia para evitar modificaciones externas directas si es necesario,
        // aunque al ser una lista de DTOs, los DTOs mismos podrían ser modificables.
        return new ArrayList<>(listaGlobalDeLibros);
    }

    @Override
    public List<LibroDTO> obtenerLibrosPorCategoria(String categoria) throws PersistenciaException {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new PersistenciaException("La categoría no puede ser nula o vacía.");
        }
        return listaGlobalDeLibros.stream()
                .filter(libro -> categoria.equalsIgnoreCase(libro.getCategoria()))
                .collect(Collectors.toList());
    }

    @Override
    public LibroDTO obtenerLibrosPorIsbn(String isbn) throws PersistenciaException {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new PersistenciaException("El ISBN no puede ser nulo o vacío.");
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
        synchronized (listaGlobalDeLibros) { // Sincronización por si fuera usada en entorno multihilo
            boolean yaExiste = listaGlobalDeLibros.stream()
                    .anyMatch(libro -> libroNuevo.getIsbn().equalsIgnoreCase(libro.getIsbn()));
            if (yaExiste) {
                System.out.println("DAO memoria: No se agregó. Ya existe un libro con ISBN: " + libroNuevo.getIsbn());
                return false; // Indica que no se agregó porque ya existía
            }
            listaGlobalDeLibros.add(libroNuevo);
            System.out.println("DAO memoria: Libro agregado a lista global: " + libroNuevo.getTitulo() + " (ISBN: " + libroNuevo.getIsbn() + "). Total: " + listaGlobalDeLibros.size());
            return true;
        }
    }

    @Override
    public boolean actualizarLibro(LibroDTO libroActualizado) throws PersistenciaException {
        if (libroActualizado == null || libroActualizado.getIsbn() == null || libroActualizado.getIsbn().trim().isEmpty()) {
            throw new PersistenciaException("Datos del libro inválidos para actualizar (libro o ISBN nulo/vacío).");
        }
        synchronized (listaGlobalDeLibros) {
            for (int i = 0; i < listaGlobalDeLibros.size(); i++) {
                if (libroActualizado.getIsbn().equalsIgnoreCase(listaGlobalDeLibros.get(i).getIsbn())) {
                    listaGlobalDeLibros.set(i, libroActualizado); // Reemplaza el libro existente
                    System.out.println("DAO memoria: Libro actualizado en lista global - ISBN " + libroActualizado.getIsbn());
                    return true;
                }
            }
        }
        System.out.println("DAO memoria: Libro no encontrado para actualizar en lista global - ISBN " + libroActualizado.getIsbn());
        return false; // Indica que no se encontró para actualizar
    }

    @Override
    public boolean eliminarLibro(String isbn) throws PersistenciaException {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new PersistenciaException("El ISBN a eliminar no puede ser nulo o vacío.");
        }
        boolean eliminado;
        synchronized (listaGlobalDeLibros) {
            eliminado = listaGlobalDeLibros.removeIf(libro -> isbn.equalsIgnoreCase(libro.getIsbn()));
        }
        if (eliminado) {
            System.out.println("DAO memoria: Libro eliminado de lista global - ISBN " + isbn + ". Total ahora: " + listaGlobalDeLibros.size());
        } else {
            System.out.println("DAO memoria: Libro no encontrado para eliminar en lista global - ISBN " + isbn);
        }
        return eliminado;
    }
}
