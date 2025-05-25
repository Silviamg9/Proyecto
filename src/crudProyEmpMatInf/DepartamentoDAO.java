package crudProyEmpMatInf;

import java.sql.*;
import java.util.ArrayList;

/**
 * Proporciona métodos para interactuar con la tabla DEPARTAMENTO en la base de datos
 */

public class DepartamentoDAO {
	
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
    
    public DepartamentoDAO() {
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
     * Obtiene todos los departamentos registrados en la base de datos.
     * 
     * @return ArrayList<Departamento> lista de todos los departamentos encontrados
     * @throws SQLException si ocurre un error en la consulta
     */
    
    public ArrayList<Departamento> listarTodos() {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT * FROM DEPARTAMENTO";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                departamentos.add(new Departamento(
                    rs.getInt("ID_Departamento"),
                    rs.getString("Nombre")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar departamentos: " + e.getMessage());
        }
        return departamentos;
    }

    /**
     * Inserta un nuevo departamento en la base de datos.
     * 
     * @param dep Objeto Departamento con los datos a insertar
     * @throws SQLException si ocurre un error en la inserción
     */
    
    public void insertar(Departamento dep) {
        String sql = "INSERT INTO DEPARTAMENTO VALUES (?, ?)";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, dep.getId());
            pstmt.setString(2, dep.getNombre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar departamento: " + e.getMessage());
        }
    }
    
    /**
     * Busca un departamento por su ID en la base de datos.
     * 
     * @param id ID del departamento a buscar
     * @return Departamento objeto encontrado o null si no existe
     * @throws SQLException si ocurre un error en la consulta
     */

    public Departamento buscarPorId(int id) {
        String sql = "SELECT * FROM DEPARTAMENTO WHERE ID_Departamento = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Departamento(
                    rs.getInt("ID_Departamento"),
                    rs.getString("Nombre")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar departamento: " + e.getMessage());
        }
        return null;
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
