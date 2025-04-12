/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Infraestructura;

import Negocio.ResultadoPago;

/**
 *
 * @author garfi
 */
public interface IMetodoPago {

    ResultadoPago procesarPago(double monto, Object detallesPago);

}
