package Adapters;

import DTOS.LibroDTO;
import Modelo.Libro;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

            entidad.setFechaLanzamiento(dto.getFechaLanzamiento());
        } else {
            entidad.setFechaLanzamiento(null);
        }

        entidad.setCategoria(dto.getCategoria());
        entidad.setPrecio(dto.getPrecio());
        entidad.setCantidadStock(dto.getCantidad());
        entidad.setRutaImagen(dto.getRutaImagen());

        entidad.setEditorial(dto.getEditorial());
        entidad.setNumPaginas(dto.getNumPaginas());

        return entidad;
    }

    public LibroDTO toDto(Libro entidad) {
        if (entidad == null) {
            return null;
        }

        java.util.Date fechaLanzUtil = null;
        if (entidad.getFechaLanzamiento() != null) {

            fechaLanzUtil = entidad.getFechaLanzamiento();
        }

        return new LibroDTO(
                entidad.getTitulo(),
                entidad.getAutor(),
                entidad.getIsbn(),
                fechaLanzUtil,
                entidad.getCategoria(),
                entidad.getPrecio(),
                entidad.getEditorial(),
                entidad.getNumPaginas(),
                entidad.getCantidadStock(), 
                 entidad.getRutaImagen()
        );
    }

    public List<Libro> toEntityList(List<LibroDTO> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<LibroDTO> toDtoList(List<Libro> entidades) {
        if (entidades == null) {
            return Collections.emptyList();
        }
        return entidades.stream().map(this::toDto).collect(Collectors.toList());
    }
}
