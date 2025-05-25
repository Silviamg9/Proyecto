package crudProyEmpMatInf;

/**
 * Representa la cantidad de compras realizadas por departamento en la empresa.
 * Almacena información sobre el departamento y el total acumulado de ventas asociadas.
 */

public class CantidadComprasPorDepartamento {

	/**
	 * Inicio de la clase
	 */
	private int departamentoId;
    private String nombreDepartamento;
    private int totalVentas;

    /**
     * Constructor que inicializa una instancia con los datos del departamento y sus ventas.
     * 
     * @param departamentoId
     * @param nombreDepartamento
     * @param totalVentas
     */
    
    public CantidadComprasPorDepartamento(int departamentoId, String nombreDepartamento, int totalVentas) {
        this.departamentoId = departamentoId;
        this.nombreDepartamento = nombreDepartamento;
        this.totalVentas = totalVentas;
    }

    public int getDepartamentoId() {
        return departamentoId;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    /**
     * Incrementa el contador de ventas del departamento.
     * 
     * @param cantidad Valor positivo a sumar al total actual de ventas
     * @throws IllegalArgumentException si la cantidad es negativa
     */
    
    public void incrementarVentas(int cantidad) {
        this.totalVentas += cantidad;
    }

    /**
	 * Devuelve una representación en cadena del objeto.
	 * 
	 * @return String con los datos del departamento y su total de ventas
	 */
    
	@Override
	public String toString() {
		return "CantidadComprasPorDepartamento [departamentoId=" + departamentoId + ", "
				+ (nombreDepartamento != null ? "nombreDepartamento=" + nombreDepartamento + ", " : "") + "totalVentas="
				+ totalVentas + "]";
	}
}
