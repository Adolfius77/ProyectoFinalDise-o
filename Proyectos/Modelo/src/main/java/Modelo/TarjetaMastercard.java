/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author emiim
 */
public class TarjetaMastercard {
    private Long id;
    private String ultimos4Digitos;
    private String nombreTitular;
    private Date fechaVencimiento;
    private String correoElectronicoTitular;
    private Long usuarioId;
    private String tokenPago;

    public TarjetaMastercard() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUltimos4Digitos() {
        return ultimos4Digitos;
    }

    public void setUltimos4Digitos(String ultimos4Digitos) {
        this.ultimos4Digitos = ultimos4Digitos;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCorreoElectronicoTitular() {
        return correoElectronicoTitular;
    }

    public void setCorreoElectronicoTitular(String correoElectronicoTitular) {
        this.correoElectronicoTitular = correoElectronicoTitular;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTokenPago() {
        return tokenPago;
    }

    public void setTokenPago(String tokenPago) {
        this.tokenPago = tokenPago;
    }

     @Override
    public boolean equals(Object o) { /* Basado en id */
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TarjetaMastercard that = (TarjetaMastercard) o;
        return Objects.equals(id, that.id);
     }
    @Override
    public int hashCode() { return Objects.hash(id); }
    @Override
    public String toString() { /* ... */ return "TarjetaMastercard{id=" + id + ", ultimos4Digitos='" + ultimos4Digitos + '\'' + '}'; }
}
