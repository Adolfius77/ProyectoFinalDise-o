/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOS.LibroDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author garfi
 */
public class BuscarPorTitulo implements IBuscadorLibro {

    @Override
    public List<LibroDTO> buscar(String criterio, List<LibroDTO> todosLosLibros) {
        if (criterio == null || criterio.trim().isEmpty() || todosLosLibros == null) {
            return new ArrayList<>(todosLosLibros);
        }
        String criterioLower = criterio.toLowerCase().trim();
        return todosLosLibros.stream()
                .filter(libro -> libro.getTitulo() != null && libro.getTitulo().toLowerCase().contains(criterioLower))
                .collect(Collectors.toList());
    }
}
