package crudProyEmpMatInf;

/**
 * Representa un componente de la empresa
 */

public class Componente {

	/**
	 * Tamaño máximo permitido para la descripcion del componente
	 */
	
	private final int TAM_DESCRIPCION = 100;
    
	/**
	 * Inicio de la clase
	 */
	
    private int id;
    private String descripcion;
    private double precio;
    private int stock;

    /**
     * Constructor básico que solo establece el ID.
     * 
     * @param id Identificador del componente
     */
    
    public Componente(int id) {
        this.id = id;
    }
    
    /**
     * Constructor completo que inicializa todos los campos.
     * 
     * @param id
     * @param descripcion
     * @param precio
     * @param stock
     */
    
    public Componente(int id, String descripcion, double precio, int stock) {
        this.id = id;
        setDescripcion(descripcion);
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion.substring(0, Math.min(TAM_DESCRIPCION, descripcion.length()));
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Devuelve una representación en formato String del componente.
	 * 
	 * @return String con los datos del componente
     */
    
	@Override
	public String toString() {
		return "Componente [TAM_DESCRIPCION=" + TAM_DESCRIPCION + ", id=" + id + ", "
				+ (descripcion != null ? "descripcion=" + descripcion + ", " : "") + "precio=" + precio + ", stock="
				+ stock + "]";
	}
}
