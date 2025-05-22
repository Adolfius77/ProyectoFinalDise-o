/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author riosr
 */
import DTOS.ConsultarClienteDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarClienteId implements IBuscarCliente{
    
    @Override
    public List<ConsultarClienteDTO> buscar (String criterio, List<ConsultarClienteDTO> listaDeClientes) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return new ArrayList<>(listaDeClientes);
        }
        String criterioLimpio = criterio.toLowerCase().trim().replaceAll("[\\s-]+", "");
        return listaDeClientes.stream()
                .filter(entrada -> entrada.getIdCliente() !=  null && entrada.getIdCliente().replaceAll("[\\s-]+", "").equalsIgnoreCase(criterioLimpio))
                .collect(Collectors.toList());
    }
}
