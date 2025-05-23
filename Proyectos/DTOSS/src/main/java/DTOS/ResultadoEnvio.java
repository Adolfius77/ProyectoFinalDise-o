
package DTOS; 

public class ResultadoEnvio {
    private final boolean direccionValida;
    private final double costoEnvio;
    private final String mensaje;

    public ResultadoEnvio(boolean direccionValida, double costoEnvio, String mensaje) {
        this.direccionValida = direccionValida;
        this.costoEnvio = direccionValida ? costoEnvio : 0.0;
        this.mensaje = mensaje;
    }

    public boolean isDireccionValida() { return direccionValida; }
    public double getCostoEnvio() { return costoEnvio; }
    public String getMensaje() { return mensaje; }

    @Override
    public String toString() {
        return "ResultadoEnvio{" + "direccionValida=" + direccionValida + ", costoEnvio=" + costoEnvio + ", mensaje=" + mensaje + '}';
    } 
}