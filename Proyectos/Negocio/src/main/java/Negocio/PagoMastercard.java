/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOS.DTOTarjetaMastercard;
import Infraestructura.IMetodoPago;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author garfi
 */
public class PagoMastercard implements IMetodoPago {

    /**
     *
     * @param monto
     * @param detallesPago
     * @return
     */
    @Override
    public ResultadoPago procesarPago(double monto, Object detallesPago) {
        if (!(detallesPago instanceof DTOTarjetaMastercard)) {
            return new ResultadoPago(false, "Detalles de pago inválidos para Mastercard.");
        }

        DTOTarjetaMastercard dto = (DTOTarjetaMastercard) detallesPago;

        // Extraer datos del DTO
        String numeroTarjeta = dto.getNumeroTarjeta();
        LocalDate fechaVencimiento = dto.getFechaVencimiento();
        String cvv = dto.getCvv();
        String nombreTitular = dto.getNombreTitular();

        System.out.println("--- Procesando pago Mastercard ---");
        System.out.println("Monto: " + monto);
        System.out.println("Tarjeta: ****" + (numeroTarjeta != null && numeroTarjeta.length() > 4 ? numeroTarjeta.substring(numeroTarjeta.length() - 4) : ""));

        // Validaciones
        if (numeroTarjeta == null || numeroTarjeta.length() < 15 || fechaVencimiento == null || cvv == null || nombreTitular == null) {
            System.err.println("Error: Datos incompletos en DTO Tarjeta.");
            return new ResultadoPago(false, "Datos de tarjeta incompletos.");
        }
        
        boolean exitoSimulado = true; 

        if (exitoSimulado) {
            System.out.println("--- Pago Mastercard APROBADO (Simulado) ---");
            return new ResultadoPago(true, "Pago con Mastercard exitoso.");
        } else {
            System.err.println("--- Pago Mastercard RECHAZADO (Simulado) ---");
            return new ResultadoPago(false, "Pago con Mastercard rechazado por el banco (simulado).");
        }
    }

}
