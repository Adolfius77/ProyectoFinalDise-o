/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.TarjetaMastercard;
import expciones.ErrorDatosException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author emiim
 */
public interface TarjetaMastercardDAO {
    void guardar(TarjetaMastercard tarjeta) throws ErrorDatosException;
    Optional<TarjetaMastercard> buscarPorId(Long id) throws ErrorDatosException;
    List<TarjetaMastercard> buscarPorUsuarioId(Long usuarioId) throws ErrorDatosException;
    void eliminar(Long id) throws ErrorDatosException;
}
