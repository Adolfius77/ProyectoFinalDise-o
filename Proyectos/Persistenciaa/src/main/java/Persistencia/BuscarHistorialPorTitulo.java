
package Persistencia;

import DTOS.EntradaHistorialDTO;
import DTOS.LibroDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarHistorialPorTitulo implements IBuscadorHistorial {

    @Override
    public List<EntradaHistorialDTO> buscar(String criterio, List<EntradaHistorialDTO> historialCompleto, List<LibroDTO> todosLosLibros) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return new ArrayList<>(historialCompleto);
        }
        String criterioLower = criterio.toLowerCase().trim();
        return historialCompleto.stream()
                .filter(entrada -> entrada.getTituloLibro() != null && entrada.getTituloLibro().toLowerCase().contains(criterioLower))
                .collect(Collectors.toList());
    }
}
