
package DTOS.servicios; 

import DTOS.ResultadoPago;

public interface IMetodoPago {
    ResultadoPago procesarPago(double monto, Object detallesPago);
}