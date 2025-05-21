/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

// Importa la INTERFAZ desde su nueva ubicación
import DTOS.servicios.IMetodoPago; 
// Importa la CLASE ResultadoPago desde DTOS
import DTOS.ResultadoPago; 

/**
 *
 * @author garfi
 */
public class ManejoPagos {

    private IMetodoPago metodoPagoActual;

    public ManejoPagos() {
        this.metodoPagoActual = null;
    }

    public ManejoPagos(IMetodoPago metodoInicial) {
        this.metodoPagoActual = metodoInicial;
    }

    public void setMetodoPago(IMetodoPago metodoPago) {
        this.metodoPagoActual = metodoPago;
        System.out.println("Metodo de pago establecido: " + (metodoPago != null ? metodoPago.getClass().getSimpleName() : "ninguno"));
    }

   
    public ResultadoPago ejecutarPago(double monto, Object detalles) { // El tipo de retorno ahora es DTOS.ResultadoPago
        if (metodoPagoActual == null) {
            System.err.println("No se ha establecido un metodo de pago.");
           
            return new ResultadoPago(false, "No se selecciono un metodo de pago.");
        }
        try {
         
            return metodoPagoActual.procesarPago(monto, detalles);
        } catch (Exception e) {
            System.err.println("Error al procesar el pago en ManejoPagos: " + e.getMessage());
            e.printStackTrace();
            
            return new ResultadoPago(false, "Error al procesar el pago: " + e.getMessage());
        }
    }
}