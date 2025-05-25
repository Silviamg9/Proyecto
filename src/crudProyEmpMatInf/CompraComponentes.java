package crudProyEmpMatInf;

/**
 * Representa una transacción de compra de componentes informáticos
 */

public class CompraComponentes {

	/**
	 * Inicio de la clase
	 */
	
	private int componenteId;
    private int clienteId;
    private int empleadoId;
    private String fecha;
    private int cantidad;

    /**
     * Constructor completo que inicializa todos los campos.
     * 
     * @param componenteId
     * @param clienteId
     * @param empleadoId
     * @param fecha
     * @param cantidad
     */
    
    public CompraComponentes(int componenteId, int clienteId, int empleadoId, String fecha, int cantidad) {
        this.componenteId = componenteId;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getComponenteId() {
        return componenteId;
    }

    public void setComponenteId(int componenteId) {
        this.componenteId = componenteId;
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
     * Devuelve una representación en formato String de compra de componentes informáticos
	 * 
	 * @return String con los datos de compra de componentes informáticos
     */
    
	@Override
	public String toString() {
		return "CompraComponentes [componenteId=" + componenteId + ", clienteId=" + clienteId + ", empleadoId="
				+ empleadoId + ", " + (fecha != null ? "fecha=" + fecha + ", " : "") + "cantidad=" + cantidad + "]";
	}
}
