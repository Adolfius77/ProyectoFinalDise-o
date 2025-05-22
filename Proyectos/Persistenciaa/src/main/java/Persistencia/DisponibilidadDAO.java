/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Disponibilidad;
import expciones.ErrorDatosException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author emiim
 */
public interface DisponibilidadDAO {
    void guardar(Disponibilidad disponibilidad) throws ErrorDatosException;
    Optional<Disponibilidad> buscarPorId(Long id) throws ErrorDatosException;
    List<Disponibilidad> buscarPorItemId(String itemId) throws ErrorDatosException;
    Optional<Disponibilidad> buscarPorItemIdYFecha(String itemId, LocalDate fecha) throws ErrorDatosException;
    List<Disponibilidad> buscarTodas() throws ErrorDatosException;
    void actualizar(Disponibilidad disponibilidad) throws ErrorDatosException;
    void eliminar(Long id) throws ErrorDatosException;
}
