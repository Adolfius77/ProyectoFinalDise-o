/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Direccion;
import expciones.ErrorDatosException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author emiim
 */
public interface DireccionDAO {
    void guardar(Direccion direccion) throws ErrorDatosException;
    Optional<Direccion> buscarPorId(Long id) throws ErrorDatosException; // Optional es buena práctica
    List<Direccion> buscarPorUsuarioId(Long usuarioId) throws ErrorDatosException; // Ejemplo de buscador específico
    List<Direccion> buscarTodas() throws ErrorDatosException;
    void actualizar(Direccion direccion) throws ErrorDatosException;
    void eliminar(Long id) throws ErrorDatosException;
}
