package crudProyEmpMatInf;

import java.sql.*;
import java.util.ArrayList;

/**
 * Proporciona métodos para interactuar con la tabla COMPONENTE en la base de datos
 */

public class ComponenteDAO {

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
    
    public ComponenteDAO() {
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
     * Obtiene todos los componentes registrados en la base de datos.
     * 
     * @return ArrayList<Componente> lista de todos los componentes encontrados
     * @throws SQLException si ocurre un error en la consulta
     */
    
    public ArrayList<Componente> listarTodos() {
        ArrayList<Componente> componentes = new ArrayList<>();
        String sql = "SELECT * FROM COMPONENTES";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                componentes.add(new Componente(
                    rs.getInt("ID_Componente"),
                    rs.getString("Descripcion"),
                    rs.getDouble("Precio"),
                    rs.getInt("Stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar componentes: " + e.getMessage());
        }
        return componentes;
    }

    /**
     * Inserta un nuevo componente en la base de datos.
     * 
     * @param dep Objeto componente con los datos a insertar
     * @throws SQLException si ocurre un error en la inserción
     */
    
    public void insertar(Componente componente) {
        String sql = "INSERT INTO COMPONENTES VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, componente.getId());
            pstmt.setString(2, componente.getDescripcion());
            pstmt.setDouble(3, componente.getPrecio());
            pstmt.setInt(4, componente.getStock());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar componente: " + e.getMessage());
        }
    }

    /**
     * Busca un componente por su ID en la base de datos.
     * 
     * @param id ID del componente a buscar
     * @return componente objeto encontrado o null si no existe
     * @throws SQLException si ocurre un error en la consulta
     */
    
    public Componente buscarPorId(int id) {
        String sql = "SELECT * FROM COMPONENTES WHERE ID_Componente = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Componente(
                    rs.getInt("ID_Componente"),
                    rs.getString("Descripcion"),
                    rs.getDouble("Precio"),
                    rs.getInt("Stock")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar componente: " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza los datos de un componente existente.
     * 
     * @param componente El objeto Componente con los datos actualizados
     * @throws SQLException si ocurre un error en la actualización
     */
    
    public void actualizar(Componente componente) {
        String sql = "UPDATE COMPONENTES SET Descripcion = ?, Precio = ?, Stock = ? WHERE ID_Componente = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, componente.getDescripcion());
            pstmt.setDouble(2, componente.getPrecio());
            pstmt.setInt(3, componente.getStock());
            pstmt.setInt(4, componente.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar componente: " + e.getMessage());
        }
    }

    /**
     * Cierra la conexión con la base de datos si está abierta.
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
