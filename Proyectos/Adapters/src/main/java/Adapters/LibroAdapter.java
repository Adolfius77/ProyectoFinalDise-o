/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapters;

import DTOS.LibroDTO;
import Modelo.Libro;
import Modelo.Libro;
import Modelo.Libro;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author emiim
 */
public class LibroAdapter {

    public Libro toEntity(LibroDTO dto) {
        if (dto == null) {
            return null;
        }
        Libro entidad = new Libro();
        entidad.setTitulo(dto.getTitulo());
        entidad.setAutor(dto.getAutor());
        entidad.setIsbn(dto.getIsbn());

        if (dto.getFechaLanzamiento() != null) {
            entidad.setFechaLanzamiento(new Date(dto.getFechaLanzamiento().getTime()));
        } else {
            entidad.setFechaLanzamiento(null);
        }

        entidad.setCategoria(dto.getCategoria());
        entidad.setPrecio(dto.getPrecio());
        entidad.setCantidadStock(dto.getCantidad()); // Mapeo de cantidad a cantidadStock
        entidad.setRutaImagen(dto.getRutaImagen());
        return entidad;
    }

    public LibroDTO toDto(Libro entidad) {
        if (entidad == null) {
            return null;
        }
        java.util.Date fechaLanzUtil = null;
        if (entidad.getFechaLanzamiento() != null) {
            fechaLanzUtil = new java.util.Date(entidad.getFechaLanzamiento().getTime());
        }

        return new LibroDTO(
            entidad.getTitulo(),
            entidad.getAutor(),
            entidad.getIsbn(),
            fechaLanzUtil,
            entidad.getCategoria(),
            entidad.getPrecio(),
            entidad.getCantidadStock(),
            entidad.getRutaImagen()
        );
    }

    public List<Libro> toEntityList(List<LibroDTO> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<LibroDTO> toDtoList(List<Libro> entidades) {
        if (entidades == null) return Collections.emptyList();
        return entidades.stream().map(this::toDto).collect(Collectors.toList());
    }
}
