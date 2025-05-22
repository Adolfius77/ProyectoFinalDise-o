/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementaciones;

import Modelo.TarjetaMastercard;
import Persistencia.TarjetaMastercardDAO;
import expciones.ErrorDatosException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author emiim
 */
public class TarjetaMastercardDAOImpl implements TarjetaMastercardDAO{

    @Override
    public void guardar(TarjetaMastercard tarjeta) throws ErrorDatosException {
         if (tarjeta == null) {
            throw new ErrorDatosException("Tarjeta nula");
        }
        if (tarjeta.getUsuarioId() == null) {
            throw new ErrorDatosException("UsuarioId requerido");
        }
        if (tarjeta.getId() == null) {
            tarjeta.setId(System.currentTimeMillis() % 10000);
        }
        System.out.println("Guardando tarjeta (datos seguros): ID = " + tarjeta.getId() + ", UsuarioID=" + tarjeta.getUsuarioId() + ", Token=" + tarjeta.getTokenPago());
    }

    @Override
    public Optional<TarjetaMastercard> buscarPorId(Long id) throws ErrorDatosException {
        System.out.println("Buscando tarjeta por ID: " + id);
        return Optional.empty();
    }

    @Override
    public List<TarjetaMastercard> buscarPorUsuarioId(Long usuarioId) throws ErrorDatosException {
        System.out.println("Buscando tarjetas por Usuario ID: " + usuarioId);
        return Collections.emptyList();
    }

    @Override
    public void eliminar(Long id) throws ErrorDatosException {
        if (id == null) {
            throw new ErrorDatosException("ID nulo para eliminar");
        }
        System.out.println("Eliminando tarjeta: ID = " + id);
    }
    
}
