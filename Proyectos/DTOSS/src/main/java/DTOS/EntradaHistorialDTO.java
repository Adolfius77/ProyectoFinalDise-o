/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author USER
 */
public class EntradaHistorialDTO {
    private String isbn;
    private String tituloLibro;
    private int cantidadEntrada;
    private Date fechaHoraEntrada;

    public EntradaHistorialDTO(String isbn, String tituloLibro, int cantidadEntrada, Date fechaHoraEntrada) {
        this.isbn = isbn;
        this.tituloLibro = tituloLibro;
        this.cantidadEntrada = cantidadEntrada;
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public int getCantidadEntrada() {
        return cantidadEntrada;
    }

    public void setCantidadEntrada(int cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }

    public Date getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(Date fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }
    public String getFechaFormateada() {
        if (fechaHoraEntrada == null) return "N/A";
        return new SimpleDateFormat("dd/MM/yyyy").format(fechaHoraEntrada);
    }

    public String getHoraFormateada() {
        if (fechaHoraEntrada == null) return "N/A";
        return new SimpleDateFormat("HH:mm:ss").format(fechaHoraEntrada);
    }
}
