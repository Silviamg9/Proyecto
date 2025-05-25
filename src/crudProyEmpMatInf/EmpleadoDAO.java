package crudProyEmpMatInf;

import java.sql.*;
import java.util.ArrayList;

/**
 * Proporciona métodos para interactuar con la tabla EMPLEADO en la base de datos
 */

public class EmpleadoDAO {

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

    public EmpleadoDAO() {
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
     * Obtiene todos los empleados registrados en la base de datos.
     * 
     * @return ArrayList<Empleado> lista completa de empleados
     * @throws SQLException si ocurre un error en la consulta
     */
    
    public ArrayList<Empleado> listarTodos() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM EMPLEADO";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                empleados.add(new Empleado(
                    rs.getInt("ID_Empleado"),
                    rs.getString("DNI"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido1"),
                    rs.getString("Apellido2"),
                    rs.getString("Telefono"),
                    rs.getInt("DEPARTAMENTO_ID_Departamento")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
        }
        return empleados;
    }

    /**
     * Inserta un nuevo empleado en la base de datos.
     * 
     * @param emp Objeto Empleado con los datos a insertar
     * @throws SQLException Si ocurre un error al ejecutar la inserción
     */
    
    public void insertar(Empleado emp) {
        String sql = "INSERT INTO EMPLEADO VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, emp.getId());
            pstmt.setString(2, emp.getDni());
            pstmt.setString(3, emp.getNombre());
            pstmt.setString(4, emp.getApellido1());
            pstmt.setString(5, emp.getApellido2());
            pstmt.setString(6, emp.getTelefono());
            pstmt.setInt(7, emp.getDepartamentoId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar empleado: " + e.getMessage());
        }
    }

    /**
     * Busca un empleado por su ID único.
     * 
     * @param id ID del empleado a buscar
     * @return Objeto Empleado si se encuentra, null si no existe
     * @throws SQLException si ocurre un error en la consulta
     */
    
    public Empleado buscarPorId(int id) {
        String sql = "SELECT * FROM EMPLEADO WHERE ID_Empleado = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Empleado(
                    rs.getInt("ID_Empleado"),
                    rs.getString("DNI"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido1"),
                    rs.getString("Apellido2"),
                    rs.getString("Telefono"),
                    rs.getInt("DEPARTAMENTO_ID_Departamento")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar empleado: " + e.getMessage());
        }
        return null;
    }

    /**
     * Busca todos los empleados que pertenecen a un departamento específico.
     * 
     * @param departamentoId ID del departamento para filtrar empleados
     * @return ArrayList<Empleado> lista de empleados del departamento
     */
    
    public ArrayList<Empleado> buscarPorDepartamento(int departamentoId) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM EMPLEADO WHERE DEPARTAMENTO_ID_Departamento = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, departamentoId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                empleados.add(new Empleado(
                    rs.getInt("ID_Empleado"),
                    rs.getString("DNI"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido1"),
                    rs.getString("Apellido2"),
                    rs.getString("Telefono"),
                    rs.getInt("DEPARTAMENTO_ID_Departamento")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar empleados por departamento: " + e.getMessage());
        }
        return empleados;
    }

    /**
     * Obtiene el departamento al que pertenece un empleado.
     * 
     * @param departamentoId ID del departamento a buscar
     * @return Objeto Departamento correspondiente al ID
     */
    
    public Departamento obtenerDepartamento(int departamentoId) {
        DepartamentoDAO dao = new DepartamentoDAO();
        return dao.buscarPorId(departamentoId);
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
