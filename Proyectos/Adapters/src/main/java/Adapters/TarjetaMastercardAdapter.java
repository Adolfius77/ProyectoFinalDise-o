/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapters;

import DTOS.DTOTarjetaMastercard;
import Modelo.TarjetaMastercard;
import java.time.LocalDate;
import java.util.Collections;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author emiim
 */
public class TarjetaMastercardAdapter {
    public TarjetaMastercard toEntity(DTOTarjetaMastercard dto) {
        if (dto == null) {
            return null;
        }
        TarjetaMastercard entidad = new TarjetaMastercard();

        // Extraer últimos 4 dígitos (ejemplo seguro)
        if (dto.getNumeroTarjeta() != null && dto.getNumeroTarjeta().length() >= 4) {
           String numero = dto.getNumeroTarjeta().replaceAll("\\s+", ""); // Quitar espacios
            if(numero.length() >= 4) {
                 entidad.setUltimos4Digitos(numero.substring(numero.length() - 4));
            } else {
                 entidad.setUltimos4Digitos(numero); // O manejar error
            }
        } else {
             entidad.setUltimos4Digitos(null); // O manejar error
        }

        entidad.setNombreTitular(dto.getNombreTitular());
        if (dto.getFechaVencimiento() != null) {
            entidad.setFechaVencimiento(Date.valueOf(dto.getFechaVencimiento()));
        } else {
            entidad.setFechaVencimiento(null);
        }

        entidad.setCorreoElectronicoTitular(dto.getCorreoElectronico());
        return entidad;
    }

    public DTOTarjetaMastercard toDto(TarjetaMastercard entidad) {
        if (entidad == null) {
            return null;
        }

        LocalDate fechaVenc = null;
        if (entidad.getFechaVencimiento() != null) {
            fechaVenc = new java.sql.Timestamp(entidad.getFechaVencimiento().getTime())
                       .toLocalDateTime()
                       .toLocalDate();
        }

        // Número ofuscado para mostrar al usuario
        String numeroOfuscado = "**** **** **** " + (entidad.getUltimos4Digitos() != null ? entidad.getUltimos4Digitos() : "");

        return new DTOTarjetaMastercard(
            numeroOfuscado,
            entidad.getNombreTitular(),
            "***", // CVV siempre ofuscado
            fechaVenc,
            entidad.getCorreoElectronicoTitular()
        );
    }

    public List<TarjetaMastercard> toEntityList(List<DTOTarjetaMastercard> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<DTOTarjetaMastercard> toDtoList(List<TarjetaMastercard> entidades) {
        if (entidades == null) return Collections.emptyList();
        return entidades.stream().map(this::toDto).collect(Collectors.toList());
    }
}
