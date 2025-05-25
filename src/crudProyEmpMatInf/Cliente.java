package crudProyEmpMatInf;

/**
 * Representa un cliente de la empresa
 */

public class Cliente {

	/**
	 * Tamaño máximo permitido para el nombre del cliente
	 */
	
	private final int TAM_NOMBRE = 30;
	
	/**
	 * Tamaño máximo permitido para el apellido del cliente
	 */
	
    private final int TAM_APELLIDO = 30;
    
    /**
     * Inicio de la clase
     */
    
    private int id;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;

    /**
     * Constructor básico que solo establece el ID.
     * 
     * @param id Identificador del cliente
     */
    
    public Cliente(int id) {
        this.id = id;
    }
    
    /**
     * Constructor completo que inicializa todos los campos.
     * 
     * @param id
     * @param dni
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param telefono
     */
    
    public Cliente(int id, String dni, String nombre, String apellido1, String apellido2, String telefono) {
        this.id = id;
        this.dni = dni;
        setNombre(nombre);
        setApellido1(apellido1);
        setApellido2(apellido2);
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.substring(0, Math.min(TAM_NOMBRE, nombre.length()));
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1.substring(0, Math.min(TAM_APELLIDO, apellido1.length()));
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        if (apellido2 != null) {
            this.apellido2 = apellido2.substring(0, Math.min(TAM_APELLIDO, apellido2.length()));
        } else {
            this.apellido2 = null;
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuelve una representación en formato String del cliente.
     * 
     * @return String con los datos del cliente
     */
    
	@Override
	public String toString() {
		return "Cliente [TAM_NOMBRE=" + TAM_NOMBRE + ", TAM_APELLIDO=" + TAM_APELLIDO + ", id=" + id + ", "
				+ (dni != null ? "dni=" + dni + ", " : "") + (nombre != null ? "nombre=" + nombre + ", " : "")
				+ (apellido1 != null ? "apellido1=" + apellido1 + ", " : "")
				+ (apellido2 != null ? "apellido2=" + apellido2 + ", " : "")
				+ (telefono != null ? "telefono=" + telefono : "") + "]";
	}
}
