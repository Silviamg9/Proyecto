package crudProyEmpMatInf;

import java.sql.*;
import java.util.ArrayList;

/**
 * Proporciona métodos para interactuar con la tabla COMPRA_COMPONENTES en la base de datos
 */

public class CompraComponentesDAO {

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
    
    public CompraComponentesDAO() {
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
     * Recupera todas las compras de componentes registradas en la base de datos.
     * 
     * @return ArrayList de objetos CompraComponentes
     * @throws SQLException si ocurre un error en la consulta
     */
    
    public ArrayList<CompraComponentes> listarTodos() {
        ArrayList<CompraComponentes> compra = new ArrayList<>();
        String sql = "SELECT * FROM COMPRA_COMPONENTES";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
            	compra.add(new CompraComponentes(
                    rs.getInt("COMPONENTES_ID_Componente"),
                    rs.getInt("CLIENTE_ID_Cliente"),
                    rs.getInt("EMPLEADO_ID_Empleado"),
                    rs.getString("Fecha"),
                    rs.getInt("Cantidad")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ventas de componentes: " + e.getMessage());
        }
        return compra;
    }
    
    /**
     * Registra una nueva compra de componentes en la base de datos.
     * 
     * @param compra El objeto CompraComponentes con los datos de la transacción
     * @throws SQLException si ocurre un error en la inserción
     */

    public void insertar(CompraComponentes compra) {
        String sql = "INSERT INTO COMPRA_COMPONENTES VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, compra.getComponenteId());
            pstmt.setInt(2, compra.getClienteId());
            pstmt.setInt(3, compra.getEmpleadoId());
            pstmt.setString(4, compra.getFecha());
            pstmt.setInt(5, compra.getCantidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar venta de componente: " + e.getMessage());
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
