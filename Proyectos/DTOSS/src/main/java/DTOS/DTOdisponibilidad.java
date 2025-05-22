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
public class DTOdisponibilidad {
   private int stock;
   private LocalDate fecha;

    public DTOdisponibilidad(int stock, LocalDate fecha) {
        this.stock = stock;
        this.fecha = fecha;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "DTOdisponibilidad{" + "stock=" + stock + ", fecha=" + fecha + '}';
    }
   
   
}
