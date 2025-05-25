package crudProyEmpMatInf;

/**
 * Representa un departamento de la empresa
 */

public class Departamento {

	/**
	 * Tamaño máximo permitido para el nombre del departamento
	 */
	
	private final int TAM_NOMBRE = 70;
    
	/**
	 * Inicio de la clase
	 */
	
    private int id;
    private String nombre;

    /**
     * Constructor básico que solo establece el ID.
     * 
     * @param id Identificador del departamento
     */
    
    public Departamento(int id) {
        this.id = id;
    }
    
    /**
     * Constructor completo que inicializa todos los campos.
     * 
     * @param id
     * @param nombre
     */
    
    public Departamento(int id, String nombre) {
        this.id = id;
        setNombre(nombre);
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

	public int getTAM_NOMBRE() {
		return TAM_NOMBRE;
	}
	
	/**
	 * Devuelve una representación en formato String del departamento.
	 * 
	 * @return String con los datos del departamento
	 */
	
	@Override
	public String toString() {
		return "Departamento [TAM_NOMBRE=" + TAM_NOMBRE + ", id=" + id + ", "
				+ (nombre != null ? "nombre=" + nombre : "") + "]";
	}
}
