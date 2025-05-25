package crudProyEmpMatInf;

import java.sql.*;
import java.util.ArrayList;

/**
 * Proporciona métodos para interactuar con la tabla COMPRA_DISPOSITIVOS en la base de datos
 */

public class CompraDispositivosDAO {

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
     *  Constructor que inicializa la conexión con la base de datos.
     */
    
    public CompraDispositivosDAO() {
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
     * Obtiene todas las compras de dispositivos registradas en la base de datos.
     * 
     * @return ArrayList<CompraDispositivos> lista completa de compras
     * @throws SQLException si ocurre un error en la consulta
     */
    
    public ArrayList<CompraDispositivos> listarTodos() {
        ArrayList<CompraDispositivos> compra = new ArrayList<>();
        String sql = "SELECT * FROM COMPRA_DISPOSITIVOS";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
            	compra.add(new CompraDispositivos(
                    rs.getInt("DISPOSITIVO_ID_Dispositivo"),
                    rs.getInt("CLIENTE_ID_Cliente"),
                    rs.getInt("EMPLEADO_ID_Empleado"),
                    rs.getString("Fecha"),
                    rs.getInt("Cantidad")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ventas de dispositivos: " + e.getMessage());
        }
        return compra;
    }

    /**
     * Inserta una nueva compra de dispositivo en la base de datos.
     * 
     * @param compra venta Objeto CompraDispositivos con los datos a insertar
     * @throws SQLException Si ocurre un error al ejecutar la inserción
     */
    
    public void insertar(CompraDispositivos compra) {
        String sql = "INSERT INTO COMPRA_DISPOSITIVOS VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, compra.getDispositivoId());
            pstmt.setInt(2, compra.getClienteId());
            pstmt.setInt(3, compra.getEmpleadoId());
            pstmt.setString(4, compra.getFecha());
            pstmt.setInt(5, compra.getCantidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar venta de dispositivo: " + e.getMessage());
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
