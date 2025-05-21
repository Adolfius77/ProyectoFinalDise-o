/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author riosr
 */
import java.util.List;
import DTOS.ConsultarClienteDTO;

public interface IBuscarCliente {
    List<ConsultarClienteDTO> buscar(String criterio, List<ConsultarClienteDTO> listaDeClientes);
}
