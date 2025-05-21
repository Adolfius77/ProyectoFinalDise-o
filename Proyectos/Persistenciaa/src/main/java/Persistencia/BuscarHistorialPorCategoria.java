package Persistencia;

import DTOS.EntradaHistorialDTO;
import DTOS.LibroDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BuscarHistorialPorCategoria implements IBuscadorHistorial {

    @Override
    public List<EntradaHistorialDTO> buscar(String criterio, List<EntradaHistorialDTO> historialCompleto, List<LibroDTO> todosLosLibros) {
        if (criterio == null || criterio.trim().isEmpty() || todosLosLibros == null || todosLosLibros.isEmpty()) {

            return new ArrayList<>(historialCompleto);
        }
        String criterioLower = criterio.toLowerCase().trim();

        Set<String> isbnsEnCategoria = todosLosLibros.stream()
                .filter(libro -> libro.getCategoria() != null && libro.getCategoria().toLowerCase().contains(criterioLower))
                .map(LibroDTO::getIsbn)
                .collect(Collectors.toSet());

        if (isbnsEnCategoria.isEmpty()) {
            return new ArrayList<>();
        }

        return historialCompleto.stream()
                .filter(entrada -> entrada.getIsbn() != null && isbnsEnCategoria.contains(entrada.getIsbn()))
                .collect(Collectors.toList());
    }
}
