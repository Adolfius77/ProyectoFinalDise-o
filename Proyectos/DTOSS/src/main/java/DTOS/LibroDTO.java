package DTOS;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LibroDTO implements Serializable {

    private static final long serialVersionUID = 123456789L;
    private String titulo;
    private String autor;
    private String isbn;
    private Date fechaLanzamiento;
    private String categoria;
    private double precio;
    private String editorial;
    private int numPaginas;
    private int cantidad; // Stock disponible
    private String rutaImagen;
    private String sinopsis;
    private List<String> reseñas;

   

    
    public LibroDTO(String titulo, String autor, String isbn, Date fechaLanzamiento, String categoria, double precio, String editorial, int numPaginas, int cantidad, String rutaImagen, String sinopsis, List<String> reseñas) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.fechaLanzamiento = fechaLanzamiento;
        this.categoria = categoria;
        this.precio = precio;
        this.editorial = editorial;
        this.numPaginas = numPaginas;
        this.cantidad = cantidad;
        this.rutaImagen = rutaImagen;
        this.sinopsis = sinopsis;
        this.reseñas = (reseñas != null) ? new ArrayList<>(reseñas) : new ArrayList<>();
    }

    public LibroDTO(String titulo, String autor, String isbn, Date fechaLanzamiento, String categoria, double precio, String editorial, int numPaginas, int cantidad, String rutaImagen, String sinopsis) {
        this(titulo, autor, isbn, fechaLanzamiento, categoria, precio, editorial, numPaginas, cantidad, rutaImagen, sinopsis, new ArrayList<>());
    }

    public LibroDTO(String titulo, String autor, String isbn, Date fechaLanzamiento, String categoria, double precio, String editorial, int numPaginas, int cantidad, String rutaImagen) {
        this(titulo, autor, isbn, fechaLanzamiento, categoria, precio, editorial, numPaginas, cantidad, rutaImagen, "Sinopsis no disponible.", new ArrayList<>());
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    } // Stock

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public List<String> getReseñas() {
        return reseñas;
    }

    public void setReseñas(List<String> reseñas) {
        this.reseñas = reseñas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void agregarReseña(String reseña) {
        if (this.reseñas == null) {
            this.reseñas = new ArrayList<>();
        }
        this.reseñas.add(reseña);
    }

  
    public String getFechaLanzamientoFormateada(String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(fechaLanzamiento);
    }

    @Override
    public String toString() {
        return "LibroDTO{"
                + "titulo='" + titulo + '\''
                + ", isbn='" + isbn + '\''
                + ", cantidad=" + cantidad
                + '}';
    }

    // --- equals y hashCode basados en ISBN ---
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LibroDTO libroDTO = (LibroDTO) o;

        return Objects.equals(isbn, libroDTO.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn); 
    }
}
