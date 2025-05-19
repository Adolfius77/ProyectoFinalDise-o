/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOS.DTOTarjetaMastercard;

import DTOS.servicios.IMetodoPago;

import DTOS.ResultadoPago;

import java.time.LocalDate;

/**
 *
 * @author garfi
 */
public class PagoMastercard implements IMetodoPago { // Asegúrate que implemente la interfaz correcta

    @Override

    public ResultadoPago procesarPago(double monto, Object detallesPago) {
        if (!(detallesPago instanceof DTOTarjetaMastercard)) {

            return new ResultadoPago(false, "Detalles de pago inválidos para Mastercard.");
        }

        DTOTarjetaMastercard dto = (DTOTarjetaMastercard) detallesPago;

        String numeroTarjeta = dto.getNumeroTarjeta();
        LocalDate fechaVencimiento = dto.getFechaVencimiento(); // DTOTarjetaMastercard usa LocalDate
        String cvv = dto.getCvv();
        String nombreTitular = dto.getNombreTitular();

        System.out.println("--- Procesando pago Mastercard ---");
        System.out.println("Monto: " + monto);
        System.out.println("Tarjeta: ****" + (numeroTarjeta != null && numeroTarjeta.length() > 4 ? numeroTarjeta.substring(numeroTarjeta.length() - 4) : ""));

        if (numeroTarjeta == null || numeroTarjeta.length() < 15
                || fechaVencimiento == null
                || cvv == null || cvv.length() < 3
                || nombreTitular == null || nombreTitular.trim().isEmpty()) {
            System.err.println("Error: Datos incompletos o inválidos en DTO Tarjeta.");

            return new ResultadoPago(false, "Datos de tarjeta incompletos o inválidos.");
        }

        if (fechaVencimiento.isBefore(LocalDate.now().withDayOfMonth(1))) {
            System.err.println("Error: La tarjeta ha expirado.");
            return new ResultadoPago(false, "La tarjeta de crédito ha expirado.");
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
