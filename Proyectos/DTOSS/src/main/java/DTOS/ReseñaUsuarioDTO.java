/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author emiim
 */
public class ReseñaUsuarioDTO {
    private String tituloLibro;
    private String textoResena;

    public ReseñaUsuarioDTO(String tituloLibro, String textoResena) {
        this.tituloLibro = tituloLibro;
        this.textoResena = textoResena;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getTextoResena() {
        return textoResena;
    }

    public void setTextoResena(String textoResena) {
        this.textoResena = textoResena;
    }

    @Override
    public String toString() {
        return "Libro: " + tituloLibro + "\nReseña: " + textoResena;
    }
}
