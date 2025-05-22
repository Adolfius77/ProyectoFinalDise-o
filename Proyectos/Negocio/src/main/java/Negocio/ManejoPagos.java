/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;


import DTOS.servicios.IMetodoPago; 

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

   
    public ResultadoPago ejecutarPago(double monto, Object detalles) { 
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