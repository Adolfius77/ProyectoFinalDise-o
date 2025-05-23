package Persistencia;

import DTOS.ConsultarClienteDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarClienteId implements IBuscarCliente {

    @Override
    public List<ConsultarClienteDTO> buscar(String criterio, List<ConsultarClienteDTO> listaDeClientes) {
        if (criterio == null || criterio.trim().isEmpty()) {

            return new ArrayList<>(listaDeClientes);
        }

        long idBuscado;
        try {

            idBuscado = Long.parseLong(criterio.trim());
        } catch (NumberFormatException e) {

            System.err.println("Criterio de búsqueda de ID no es un número válido: " + criterio);
            return new ArrayList<>();
        }

        return listaDeClientes.stream()
                .filter(cliente -> cliente.getIdCliente() == idBuscado)
                .collect(Collectors.toList());
    }
}
