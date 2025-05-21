
package Persistencia;

import DTOS.EntradaHistorialDTO;
import DTOS.LibroDTO; 
import java.util.List;

public interface IBuscadorHistorial {

    List<EntradaHistorialDTO> buscar(String criterio, List<EntradaHistorialDTO> historialCompleto, List<LibroDTO> todosLosLibros);
}