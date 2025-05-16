/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTOS.LibroDTO;
import java.util.List;

/**
 *
 * @author garfi
 */
public interface IBuscadorLibro {
    List<LibroDTO> buscar(String criterioDeBusqueda, List<LibroDTO> libros);
}
