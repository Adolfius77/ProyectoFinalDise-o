// Archivo: Proyectos/DTOSS/src/main/java/DTOS/servicios/IMetodoEnvio.java
package DTOS.servicios; // O el paquete que elijas dentro de DTOSS

import DTOS.DTODireccion;
import DTOS.ResultadoEnvio; 

public interface IMetodoEnvio {
    ResultadoEnvio procesarDireccion(DTODireccion direccion);
}