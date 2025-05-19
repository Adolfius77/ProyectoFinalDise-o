
package DTOS.servicios; 

import DTOS.DTODireccion;
import DTOS.ResultadoEnvio; 

public interface IMetodoEnvio {
  

    ResultadoEnvio procesarDireccion(DTODireccion direccion);
}