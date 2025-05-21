/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementaciones;

import Modelo.Direccion;
import Persistencia.DireccionDAO;
import expciones.ErrorDatosException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author emiim
 */
public class DireccionDAOImpl implements DireccionDAO{
    @Override
    public void guardar(Direccion direccion) throws ErrorDatosException {
        if (direccion == null) {
            throw new ErrorDatosException("La dirección no puede ser nula");
        }
        if (direccion.getId() == null) {
            direccion.setId(System.currentTimeMillis() % 10000);
        }
        System.out.println("Guardando/Actualizando dirección: ID = " + direccion.getId() + ", Calle=" + direccion.getCalle());
    }

    @Override
    public Optional<Direccion> buscarPorId(Long id) throws ErrorDatosException {
        System.out.println("Buscando dirección por ID: " + id);
        return Optional.empty();
    }

    @Override
    public List<Direccion> buscarPorUsuarioId(Long usuarioId) throws ErrorDatosException {
        System.out.println("Buscando direcciones por Usuario ID: " + usuarioId);
        return Collections.emptyList(); // Devuelve lista vacía 
    }

    @Override
    public List<Direccion> buscarTodas() throws ErrorDatosException {
        System.out.println("[IMPL STUB] Buscando todas las direcciones");
        return Collections.emptyList(); // Devuelve lista vacía
    }

    @Override
    public void actualizar(Direccion direccion) throws ErrorDatosException {
        if (direccion == null || direccion.getId() == null) {
            throw new ErrorDatosException("Dirección o ID nulo para actualizar");
        }
        System.out.println("Actualizando dirección: ID = " + direccion.getId());
    }

    @Override
    public void eliminar(Long id) throws ErrorDatosException {
        if (id == null) {
            throw new ErrorDatosException("ID nulo para eliminar");
        }
        System.out.println("Eliminando dirección: ID = " + id);
    }
}
