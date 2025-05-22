/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapters;

import DTOS.DTODireccion;
import Modelo.Direccion;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author emiim
 */
public class DireccionAdapter {

    public Direccion toEntity(DTODireccion dto) {
        if (dto == null) {
            return null;
        }
        Direccion entidad = new Direccion();
        entidad.setCalle(dto.getCalle());
        entidad.setColonia(dto.getColonia());
        entidad.setCodigoPostal(dto.getCodigoPostal());
        entidad.setEstado(dto.getEstado());
        entidad.setCiudad(dto.getCiudad());
        entidad.setNumCasa(dto.getNumCasa());
        entidad.setPais(dto.getPais());
        return entidad;
    }

    public DTODireccion toDto(Direccion entidad) {
        if (entidad == null) {
            return null;
        }
        return new DTODireccion(
            entidad.getCalle(),
            entidad.getColonia(),
            entidad.getCodigoPostal(),
            entidad.getEstado(),
            entidad.getCiudad(),
            entidad.getNumCasa(),
            entidad.getPais()
        );
    }

    public List<Direccion> toEntityList(List<DTODireccion> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<DTODireccion> toDtoList(List<Direccion> entidades) {
         if (entidades == null) return Collections.emptyList();
        return entidades.stream().map(this::toDto).collect(Collectors.toList());
    }
}
