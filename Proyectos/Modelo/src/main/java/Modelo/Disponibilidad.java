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
public class Disponibilidad {
    private Long id;
    private int stock;
    private Date fecha;
    private String itemId; // Identificador del item (ej. ISBN libro)

    public Disponibilidad() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) { /* Basado en id */
       if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disponibilidad that = (Disponibilidad) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
    @Override
    public String toString() { /* ... */ return "Disponibilidad{id=" + id + ", itemId='" + itemId + '\'' + ", stock=" + stock + '}';}
}
