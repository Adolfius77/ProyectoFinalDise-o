
package DTOS; 

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