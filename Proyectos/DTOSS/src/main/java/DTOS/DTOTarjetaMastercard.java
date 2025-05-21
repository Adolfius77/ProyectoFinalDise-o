/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class DTOTarjetaMastercard {

    private String numeroTarjeta;
    private String nombreTitular;
    private String cvv;
    private LocalDate fechaVencimiento;
    private String correoElectronico;

    public DTOTarjetaMastercard(String numeroTarjeta, String nombreTitular, String cvv, LocalDate fechaVencimiento, String correoElectronico) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;
        this.cvv = cvv;
        this.fechaVencimiento = fechaVencimiento;
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "DTOTarjetaMastercard{" + "numeroTarjeta=" + numeroTarjeta + ", nombreTitular=" + nombreTitular + ", cvv=" + cvv + ", fechaVencimiento=" + fechaVencimiento + ", correoElectronico=" + correoElectronico + '}';
    }

}
