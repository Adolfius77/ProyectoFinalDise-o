/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Infraestructura;

import DTOS.DTODireccion;
import Negocio.ResultadoEnvio;

/**
 *
 * @author emiim
 */
public interface IMetodoEnvio {
    public static final double COSTO_ENVIO_ESTANDAR = 20.0;

    ResultadoEnvio procesarDireccion(DTODireccion direccion);
}
