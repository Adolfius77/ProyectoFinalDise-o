/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import DTOS.ConsultarClienteDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author riosr
 */
public class BuscarClienteNombre implements IBuscarCliente {
    
    @Override
    public List<ConsultarClienteDTO> buscar (String criterio, List<ConsultarClienteDTO> listaDeClientes) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return new ArrayList<>(listaDeClientes);
        }
        String criterioLower = criterio.toLowerCase().trim();
        return listaDeClientes.stream()
                .filter(entrada -> entrada.getNombreCliente()!= null && entrada.getNombreCliente().toLowerCase().contains(criterioLower))
                .collect(Collectors.toList());
    }
}
