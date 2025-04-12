/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementaciones;

import Modelo.Disponibilidad;
import Persistencia.DisponibilidadDAO;
import expciones.ErrorDatosException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author emiim
 */
public class DisponibilidadDAOImpl implements DisponibilidadDAO{

    @Override
    public void guardar(Disponibilidad disponibilidad) throws ErrorDatosException {
        if (disponibilidad == null || disponibilidad.getItemId() == null) {
             throw new ErrorDatosException("Disponibilidad o ItemID nulo");
        }
        if (disponibilidad.getId() == null) {
            disponibilidad.setId(System.currentTimeMillis() % 10000); // ID simulado
        }
        System.out.println("Guardando/Actualizando disponibilidad: ID = " + disponibilidad.getId() + ", ItemID=" + disponibilidad.getItemId() + ", Stock=" + disponibilidad.getStock());
    }

    @Override
    public Optional<Disponibilidad> buscarPorId(Long id) throws ErrorDatosException {
        System.out.println("Buscando disponibilidad por Item ID: " + id);
        return Optional.empty();
    }

    @Override
    public List<Disponibilidad> buscarPorItemId(String itemId) throws ErrorDatosException {
        System.out.println("Buscando disponibilidad por Item ID: " + itemId);
        return Collections.emptyList();
    }

    @Override
    public Optional<Disponibilidad> buscarPorItemIdYFecha(String itemId, LocalDate fecha) throws ErrorDatosException {
        System.out.println("Buscando disponibilidad por Item ID: " + itemId + " y Fecha: " + fecha);
        return Optional.empty();
    }

    @Override
    public List<Disponibilidad> buscarTodas() throws ErrorDatosException {
        System.out.println("Buscando todas las disponibilidades");
        return Collections.emptyList();
    }

    @Override
    public void actualizar(Disponibilidad disponibilidad) throws ErrorDatosException {
       if (disponibilidad == null || disponibilidad.getId() == null) {
            throw new ErrorDatosException("Disponibilidad o ID nulo para actualizar");
        }
        System.out.println("Actualizando disponibilidad: ID = " + disponibilidad.getId());
    }

    @Override
    public void eliminar(Long id) throws ErrorDatosException {
        if (id == null) {
            throw new ErrorDatosException("ID nulo para eliminar");
        }
        System.out.println("Eliminando disponibilidad: ID = " + id);
    }
    
}
