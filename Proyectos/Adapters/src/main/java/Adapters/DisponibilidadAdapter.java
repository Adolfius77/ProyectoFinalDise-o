/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapters;

import DTOS.DTOdisponibilidad;
import Modelo.Disponibilidad;
import java.time.LocalDate;
import java.util.Collections;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author emiim
 */
public class DisponibilidadAdapter {
   public Disponibilidad toEntity(DTOdisponibilidad dto) {
        if (dto == null) {
            return null;
        }
        Disponibilidad entidad = new Disponibilidad();
        entidad.setStock(dto.getStock());
        if (dto.getFecha() != null) {
            entidad.setFecha(Date.valueOf(dto.getFecha()));
        } else {
            entidad.setFecha(null);
        }
        // entidad.setItemId(...); // Asignar desde contexto
        return entidad;
    }
   
    public DTOdisponibilidad toDto(Disponibilidad entidad) {
        if (entidad == null) {
            return null;
        }
         LocalDate fecha = null;
        if (entidad.getFecha() != null) {
        fecha = new java.sql.Timestamp(entidad.getFecha().getTime())
                       .toLocalDateTime()
                       .toLocalDate();
        }   
        return new DTOdisponibilidad(
            entidad.getStock(),
            fecha
        );
    }

     public List<Disponibilidad> toEntityList(List<DTOdisponibilidad> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<DTOdisponibilidad> toDtoList(List<Disponibilidad> entidades) {
        if (entidades == null) return Collections.emptyList();
        return entidades.stream().map(this::toDto).collect(Collectors.toList());
    }
}
