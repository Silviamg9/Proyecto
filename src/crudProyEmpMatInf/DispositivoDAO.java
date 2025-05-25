package crudProyEmpMatInf;

import java.sql.*;
import java.util.ArrayList;

/**
 * Proporciona métodos para interactuar con la tabla DISPOSITIVO en la base de datos
 */

public class DispositivoDAO {

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
	    
	    public DispositivoDAO() {
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
	     * Obtiene todos los dispositivos registrados en la base de datos.
	     * 
	     * @return ArrayList<Dispositivo> lista completa de dispositivos
	     * @throws SQLException si ocurre un error en la consulta
	     */
	    
	    public ArrayList<Dispositivo> listarTodos() {
	        ArrayList<Dispositivo> dispositivos = new ArrayList<>();
	        String sql = "SELECT * FROM DISPOSITIVO";
	        
	        try (Statement stmt = conexion.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            
	            while (rs.next()) {
	                dispositivos.add(new Dispositivo(
	                    rs.getInt("ID_Dispositivo"),
	                    rs.getString("Nombre"),
	                    rs.getDouble("Precio"),
	                    rs.getInt("Stock"),
	                    rs.getString("Estado")
	                ));
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al listar dispositivos: " + e.getMessage());
	        }
	        return dispositivos;
	    }

	    /**
	     * Inserta un nuevo dispositivo en la base de datos.
	     * 
	     * @param dispositivo Objeto Dispositivo con los datos a insertar
	     * @throws SQLException Si ocurre un error al ejecutar la inserción
	     */
	    
	    public void insertar(Dispositivo dispositivo) {
	        String sql = "INSERT INTO DISPOSITIVO VALUES (?, ?, ?, ?, ?)";
	        
	        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
	            pstmt.setInt(1, dispositivo.getId());
	            pstmt.setString(2, dispositivo.getNombre());
	            pstmt.setDouble(3, dispositivo.getPrecio());
	            pstmt.setInt(4, dispositivo.getStock());
	            pstmt.setString(5, dispositivo.getEstado());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println("Error al insertar dispositivo: " + e.getMessage());
	        }
	    }
	    
	    /**
	     * Busca un dispositivo por su ID único.
	     * 
	     * @param id ID del dispositivo a buscar
	     * @return Objeto Dispositivo si se encuentra, null si no existe
	     */

	    public Dispositivo buscarPorId(int id) {
	        String sql = "SELECT * FROM DISPOSITIVO WHERE ID_Dispositivo = ?";
	        
	        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
	            pstmt.setInt(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	                return new Dispositivo(
	                    rs.getInt("ID_Dispositivo"),
	                    rs.getString("Nombre"),
	                    rs.getDouble("Precio"),
	                    rs.getInt("Stock"),
	                    rs.getString("Estado")
	                );
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al buscar dispositivo: " + e.getMessage());
	        }
	        return null;
	    }

	    /**
	     * Actualiza los datos de un dispositivo existente en la base de datos.
	     * 
	     * @param dispositivo Objeto Dispositivo con los datos actualizados
	     * @throws SQLException Si ocurre un error al ejecutar la actualización
	     */
	    
	    public void actualizar(Dispositivo dispositivo) {
	        String sql = "UPDATE DISPOSITIVO SET Nombre = ?, Precio = ?, Stock = ?, Estado = ? WHERE ID_Dispositivo = ?";
	        
	        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
	            pstmt.setString(1, dispositivo.getNombre());
	            pstmt.setDouble(2, dispositivo.getPrecio());
	            pstmt.setInt(3, dispositivo.getStock());
	            pstmt.setString(4, dispositivo.getEstado());
	            pstmt.setInt(5, dispositivo.getId());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println("Error al actualizar dispositivo: " + e.getMessage());
	        }
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
