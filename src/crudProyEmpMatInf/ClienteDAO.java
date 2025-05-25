package crudProyEmpMatInf;

import java.sql.*;
import java.util.ArrayList;

/**
 * Proporciona métodos para interactuar con la tabla CLIENTE en la base de datos
 */
public class ClienteDAO {

		/**
		 * Conexión con la base de datos
		 */
		private Connection conexion;
		/**
		 * Usuario para la conexión a la base de datos
		 */
	    private final String USUARIO = "root";
	    /**
	     * Contraseña para la conexión a la base de datos
	     */
	    private final String PASSWORD = "crisil1921";
	    /**
	     * Dirección y puerto del servidor de base de datos
	     */
	    private final String MAQUINA = "localhost:3306";
	    /**
	     * Nombre de la base de datos a la que conectarse
	     */
	    private final String BD = "empresaMaterialInformatico";
	    
	    /**
	     * Constructor que inicializa la conexión con la base de datos.
	     */
	    
	    public ClienteDAO() {
	        conexion = conectar();
	    }

	    /**
	     * Establece la conexión con la base de datos MySQL.
	     * 
	     * @return Connection objeto de conexión a la base de datos
	     */
	    
	    private Connection conectar() {
	        Connection con = null;
	        String url = "jdbc:mysql://" + MAQUINA + "/" + BD;
	        try {
	            con = DriverManager.getConnection(url, USUARIO, PASSWORD);
	        } catch (SQLException ex) {
	            System.out.println("Error al conectar: " + ex.getMessage());
	        }
	        return con;
	    }

	    /**
	     * Obtiene todos los clientes registrados en la base de datos.
	     * 
	     * @return ArrayList<Cliente> lista completa de clientes
	     */
	    
	    public ArrayList<Cliente> listarTodos() {
	        ArrayList<Cliente> clientes = new ArrayList<>();
	        String sql = "SELECT * FROM CLIENTE";
	        
	        try (Statement stmt = conexion.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            
	            while (rs.next()) {
	                clientes.add(new Cliente(
	                    rs.getInt("ID_Cliente"),
	                    rs.getString("DNI"),
	                    rs.getString("Nombre"),
	                    rs.getString("Apellido1"),
	                    rs.getString("Apellido2"),
	                    rs.getString("Telefono")
	                ));
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al listar clientes: " + e.getMessage());
	        }
	        return clientes;
	    }

	    /**
	     * Inserta un nuevo cliente en la base de datos.
	     * 
	     * @param cliente Objeto Cliente con los datos a insertar
	     * @throws SQLException Si ocurre un error al ejecutar la inserción
	     */
	    
	    public void insertar(Cliente cliente) {
	        String sql = "INSERT INTO CLIENTE VALUES (?, ?, ?, ?, ?, ?)";
	        
	        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
	            pstmt.setInt(1, cliente.getId());
	            pstmt.setString(2, cliente.getDni());
	            pstmt.setString(3, cliente.getNombre());
	            pstmt.setString(4, cliente.getApellido1());
	            pstmt.setString(5, cliente.getApellido2());
	            pstmt.setString(6, cliente.getTelefono());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println("Error al insertar cliente: " + e.getMessage());
	        }
	    }

	    /**
	     * Busca un cliente por su ID único.
	     * 
	     * @param id ID del cliente a buscar
	     * @return Objeto Cliente si se encuentra, null si no existe
	     */
	    
	    public Cliente buscarPorId(int id) {
	        String sql = "SELECT * FROM CLIENTE WHERE ID_Cliente = ?";
	        
	        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
	            pstmt.setInt(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	                return new Cliente(
	                    rs.getInt("ID_Cliente"),
	                    rs.getString("DNI"),
	                    rs.getString("Nombre"),
	                    rs.getString("Apellido1"),
	                    rs.getString("Apellido2"),
	                    rs.getString("Telefono")
	                );
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al buscar cliente: " + e.getMessage());
	        }
	        return null;
	    }

	    /**
	     * Cierra la conexión con la base de datos.
	     * 
	     * @throws SQLException si ocurre un error al cerrar la conexión
	     */
	    
	    public void cerrarConexion() {
	        try {
	            if (conexion != null && !conexion.isClosed()) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar conexión: " + e.getMessage());
	        }
	    }
}
