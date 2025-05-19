/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;


import DTOS.DTOPayPal;

import DTOS.servicios.IMetodoPago;

import DTOS.ResultadoPago;

/**
 *
 * @author garfi
 */
public class PagoPaypal implements IMetodoPago {

    @Override
 
    public ResultadoPago procesarPago(double monto, Object detallesPago) {
        if (!(detallesPago instanceof DTOPayPal)) {
           
            return new ResultadoPago(false, "Detalles de pago inválidos para PayPal.");
        }

        DTOPayPal dto = (DTOPayPal) detallesPago;

        String correo = dto.getCorreoElectronico();
        String contrasena = dto.getContraseña(); 

        System.out.println("--- Procesando pago PayPal ---");
        System.out.println("Monto: " + monto);
        System.out.println("Usuario PayPal: " + correo);

        // Validaciones
        if (correo == null || contrasena == null || correo.trim().isEmpty() || contrasena.trim().isEmpty() || !correo.contains("@")) {
            System.err.println("Error: Datos incompletos o inválidos en DTO PayPal.");
            // 6. Aquí también 'new ResultadoPago(...)' se referirá a DTOS.ResultadoPago
            return new ResultadoPago(false, "Credenciales PayPal inválidas o incompletas.");
        }

        boolean exitoSimulado = true;

        if (exitoSimulado) {
            System.out.println("--- Pago PayPal APROBADO ---");
            return new ResultadoPago(true, "Pago con PayPal exitoso.");
        } else {
            System.err.println("--- Pago PayPal RECHAZADO ---");
            return new ResultadoPago(false, "Pago con PayPal rechazado.");
        }
    }
}