package crudProyEmpMatInf;

/**
 * Representa un dispositivo de la empresa
 */

public class Dispositivo {

	/**
	 * Tamaño máximo permitido para el nombre del dispositivo
	 */
	
	private final int TAM_NOMBRE = 30;
    
	/**
	 * Inicio de la clase
	 */
	
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private String estado;

    /**
     * Constructor básico que solo establece el ID.
     * 
     * @param id Identificador del dispositivo
     */
    
    public Dispositivo(int id) {
        this.id = id;
    }
    
    /**
     * Constructor completo que inicializa todos los campos.
     * 
     * @param id
     * @param nombre
     * @param precio
     * @param stock
     * @param estado
     */
    
    public Dispositivo(int id, String nombre, double precio, int stock, String estado) {
        this.id = id;
        setNombre(nombre);
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.substring(0, Math.min(TAM_NOMBRE, nombre.length()));
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Devuelve una representación en formato String del dispositivo.
	 * 
	 * @return String con los datos del dispositivo
     */
    
	@Override
	public String toString() {
		return "Dispositivo [TAM_NOMBRE=" + TAM_NOMBRE + ", id=" + id + ", "
				+ (nombre != null ? "nombre=" + nombre + ", " : "") + "precio=" + precio + ", stock=" + stock + ", "
				+ (estado != null ? "estado=" + estado : "") + "]";
	}
}
