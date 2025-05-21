// MUEVE ESTA CLASE A: Proyectos/DTOSS/src/main/java/DTOS/ResultadoPago.java
package DTOS; // Asegúrate que el package sea DTOS

public class ResultadoPago {
    private boolean exito;
    private String mensaje;
   

    public ResultadoPago(boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }

    public boolean isExito() {
        return exito;
    }

    public String getMensaje() {
        return mensaje;
    }
}