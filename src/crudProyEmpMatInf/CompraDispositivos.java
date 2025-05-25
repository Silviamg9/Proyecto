package crudProyEmpMatInf;

/**
 * Representa una transacción de compra de dispositivos
 */

public class CompraDispositivos {

	/**
	 * Inicio de la clase
	 */
	
	private int dispositivoId;
    private int clienteId;
    private int empleadoId;
    private String fecha;
    private int cantidad;

    /**
     * Constructor completo que inicializa todos los campos.
     * 
     * @param dispositivoId
     * @param clienteId
     * @param empleadoId
     * @param fecha
     * @param cantidad
     */
    
    public CompraDispositivos(int dispositivoId, int clienteId, int empleadoId, String fecha, int cantidad) {
        this.dispositivoId = dispositivoId;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(int dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Devuelve una representación en cadena de la compra de dispositivos.
     * 
     * @return String con todos los datos de la compra
     */
    
	@Override
	public String toString() {
		return "CompraDispositivos [dispositivoId=" + dispositivoId + ", clienteId=" + clienteId + ", empleadoId="
				+ empleadoId + ", " + (fecha != null ? "fecha=" + fecha + ", " : "") + "cantidad=" + cantidad + "]";
	}
}
