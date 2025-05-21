
package Persistencia;

import DTOS.EntradaHistorialDTO;
import DTOS.LibroDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarHistorialPorISBN implements IBuscadorHistorial {

    @Override
    public List<EntradaHistorialDTO> buscar(String criterio, List<EntradaHistorialDTO> historialCompleto, List<LibroDTO> todosLosLibros) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return new ArrayList<>(historialCompleto);
        }
        String criterioLimpio = criterio.toLowerCase().trim().replaceAll("[\\s-]+", "");
        return historialCompleto.stream()
                .filter(entrada -> entrada.getIsbn() != null && entrada.getIsbn().replaceAll("[\\s-]+", "").equalsIgnoreCase(criterioLimpio))
                .collect(Collectors.toList());
    }
}
