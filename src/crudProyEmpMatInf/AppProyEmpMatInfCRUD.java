package crudProyEmpMatInf;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Aplicación principal para la gestión de una empresa de material informático.
 * 
 * @author silvia
 */

public class AppProyEmpMatInfCRUD {

	/**
	 * Método principal que inicia la aplicación.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        DispositivoDAO dispositivoDAO = new DispositivoDAO();
        ComponenteDAO componenteDAO = new ComponenteDAO();
        CompraDispositivosDAO compraDispositivoDAO = new CompraDispositivosDAO();
        CompraComponentesDAO compraComponenteDAO = new CompraComponentesDAO();
        
        
        int opcion;

        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    listarDepartamentos(departamentoDAO);
                    break;
                case 2:
                    insertarDepartamento(departamentoDAO, sc);
                    break;
                case 3:
                    insertarEmpleado(empleadoDAO, sc);
                    break;
                case 4:
                    listarEmpleados(empleadoDAO);
                    break;
                case 5:
                    buscarEmpleadosPorDepartamento(empleadoDAO, sc);
                    break;
                case 6:
                    listarClientes(clienteDAO);
                    break;
                case 7:
                    insertarCliente(clienteDAO, sc);
                    break;
                case 8:
                    listarDispositivos(dispositivoDAO);
                    break;
                case 9:
                    insertarDispositivo(dispositivoDAO, sc);
                    break;
                case 10:
                    listarComponentes(componenteDAO);
                    break;
                case 11:
                    insertarComponente(componenteDAO, sc);
                    break;
                case 12:
                    realizarCompraDispositivo(empleadoDAO, clienteDAO, dispositivoDAO, compraDispositivoDAO, sc);
                    break;
                case 13:
                    realizarCompraComponente(empleadoDAO, clienteDAO, componenteDAO, compraComponenteDAO, sc);
                    break;
                case 14:
                    listarComprasDispositivos(compraDispositivoDAO);
                    break;
                case 15:
                    listarComprasComponentes(compraComponenteDAO);
                    break;
                case 16:
                    generarInformeComprasPorDepartamento(empleadoDAO, compraDispositivoDAO, compraComponenteDAO);
                    break;
                case 17:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);

        sc.close();
    }

	/**
	 * Muestra el menú de la aplicación con todas las opciones disponibles.
	 */
    static void mostrarMenu() {
        System.out.println("\n--------------------- MENÚ ---------------------");
        System.out.println("1. Listar departamentos");
        System.out.println("2. Insertar departamento");
        System.out.println("3. Insertar empleado");
        System.out.println("4. Listar empleados");
        System.out.println("5. Buscar empleados por departamento");
        System.out.println("6. Listar clientes");
        System.out.println("7. Insertar cliente");
        System.out.println("8. Listar dispositivos");
        System.out.println("9. Insertar dispositivo");
        System.out.println("10. Listar componentes");
        System.out.println("11. Insertar componente");
        System.out.println("12. Realizar compra de dispositivo");
        System.out.println("13. Realizar compra de componente");
        System.out.println("14. Listar compras de dispositivos");
        System.out.println("15. Listar compras de componentes");
        System.out.println("16. Informe de compras por departamento");
        System.out.println("17. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Lista todos los departamentos registrados.
     * 
     * @param dao Objeto DepartamentoDAO para acceder a los datos de departamentos
     */
    private static void listarDepartamentos(DepartamentoDAO dao) {
    	
        ArrayList<Departamento> departamentos = dao.listarTodos();
        System.out.println("\n---------------- LISTADO DE DEPARTAMENTOS ----------------");
        departamentos.forEach(System.out::println);
    }
    
    /**
     * Permite insertar un nuevo departamento.
     * 
     * @param dao Objeto DepartamentoDAO para operaciones de departamento
     * @param sc Scanner para leer la entrada del usuario
     */

    static void insertarDepartamento(DepartamentoDAO dao, Scanner sc) {
    	
        System.out.println("\n---------------- INSERTAR DEPARTAMENTO ----------------");
        System.out.print("Introduzca el ID del departamento: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Introduzca el nombre del departamento: ");
        String nombre = sc.nextLine();

        Departamento dep = new Departamento(id, nombre);
        dao.insertar(dep);
        System.out.println("Departamento insertado correctamente");
    }

    /**
     * Permite insertar un nuevo empleado.
     * 
     * @param dao Objeto EmpleadoDAO para operaciones de empleado
     * @param sc Scanner para leer la entrada del usuario
     */
    
    private static void insertarEmpleado(EmpleadoDAO dao, Scanner sc) {
    	
        System.out.println("\n---------------- INSERTAR EMPLEADO ----------------");
        System.out.print("Introduzca el ID del empleado: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Introduzca el DNI: ");
        String dni = sc.nextLine();
        System.out.print("Introduzca el Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca el primer apellido: ");
        String apellido1 = sc.nextLine();
        System.out.print("Introduzca el segundo apellido: ");
        String apellido2 = sc.nextLine();
        
        if (apellido2.isEmpty()) {
            apellido2 = null;
        }
        
        System.out.print("Introduzca el teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Introduzca el ID del departamento: ");
        int departamentoId = sc.nextInt();

        Empleado emp = new Empleado(id, dni, nombre, apellido1, apellido2, telefono, departamentoId);
        dao.insertar(emp);
        System.out.println("Empleado insertado correctamente");
    }

    /**
     * Lista todos los empleados registrados.
     * 
     * @param dao Objeto EmpleadoDAO para acceder a los datos
     */
    
    static void listarEmpleados(EmpleadoDAO dao) {
    	
        ArrayList<Empleado> empleados = dao.listarTodos();
        System.out.println("\n---------------- LISTADO DE EMPLEADOS ----------------");
        empleados.forEach(System.out::println);
    }

    /**
     * Busca empleados por departamento.
     * 
     * @param dao Objeto EmpleadoDAO para acceder a los datos
     * @param sc Scanner para leer el ID de departamento
     */
    
    private static void buscarEmpleadosPorDepartamento(EmpleadoDAO dao, Scanner sc) {
    	
        System.out.println("\n---------------- BUSCAR EMPLEADOS POR DEPARTAMENTO ----------------");
        System.out.print("Introduzca el ID del departamento: ");
        int departamentoId = sc.nextInt();
        
        ArrayList<Empleado> empleados = dao.buscarPorDepartamento(departamentoId);
        System.out.println("\n---------------- RESULTADOS ----------------");
        empleados.forEach(System.out::println);
    }

    /**
     * Lista todos los clientes registrados.
     * 
     * @param dao Objeto ClienteDAO para acceder a los datos
     */
    
    static void listarClientes(ClienteDAO dao) {
    	
        ArrayList<Cliente> clientes = dao.listarTodos();
        System.out.println("\n---------------- LISTADO DE CLIENTES ----------------");
        clientes.forEach(System.out::println);
    }

    /**
     * Permite insertar un nuevo cliente.
     * 
     * @param dao Objeto ClienteDAO para operaciones para operaciones de cliente
     * @param sc Scanner para leer la entrada del usuario
     */
    
    private static void insertarCliente(ClienteDAO dao, Scanner sc) {
    	
        System.out.println("\n---------------- INSERTAR CLIENTE ----------------");
        System.out.print("Introduzca el ID del cliente: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Introduzca el DNI: ");
        String dni = sc.nextLine();
        System.out.print("Introduzca el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca el primer apellido: ");
        String apellido1 = sc.nextLine();
        System.out.print("Introduzca el segundo apellido: ");
        String apellido2 = sc.nextLine();
        
        if (apellido2.isEmpty()) {
            apellido2 = null;
        }
        
        System.out.print("Introduzca el teléfono: ");
        String telefono = sc.nextLine();

        Cliente cliente = new Cliente(id, dni, nombre, apellido1, apellido2, telefono);
        dao.insertar(cliente);
        System.out.println("Cliente insertado correctamente");
    }

    /**
     * Lista todos los dispositivos registrados.
     * 
     * @param dao Objeto DispositivoDAO para acceder a los datos
     */
    
    static void listarDispositivos(DispositivoDAO dao) {
    	
        ArrayList<Dispositivo> dispositivos = dao.listarTodos();
        System.out.println("\n---------------- LISTADO DE DISPOSITIVOS ----------------");
        dispositivos.forEach(System.out::println);
    }

    /**
     * Permite insertar un nuevo dispositivo.
     * 
     * @param dao Objeto DispositivoDAO para operaciones de dispositivo
     * @param sc Scanner para leer la entrada del usuario
     */
    
    private static void insertarDispositivo(DispositivoDAO dao, Scanner sc) {
    	
        System.out.println("\n---------------- INSERTAR DISPOSITIVO ----------------");
        System.out.print("Introduzca el ID del dispositivo: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Introduzca el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca el precio: ");
        double precio = sc.nextDouble();
        System.out.print("Introduzca el stock: ");
        int stock = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Introduzca el estado (Nuevo/Segunda Mano): ");
        String estado = sc.nextLine();

        Dispositivo dispositivo = new Dispositivo(id, nombre, precio, stock, estado);
        dao.insertar(dispositivo);
        System.out.println("Dispositivo insertado correctamente");
    }
    
    /**
     * Lista todos los componentes registrados.
     * 
     * @param dao Objeto ComponenteDAO para acceder a los datos
     */

    private static void listarComponentes(ComponenteDAO dao) {
    	
        ArrayList<Componente> componentes = dao.listarTodos();
        System.out.println("\n---------------- LISTADO DE COMPONENTES ----------------");
        componentes.forEach(System.out::println);
    }

    /**
     * Permite insertar un nuevo componente.
     * 
     * @param dao Objeto ComponenteDAO para operaciones de componente
     * @param sc Scanner para leer la entrada del usuario
     */
    private static void insertarComponente(ComponenteDAO dao, Scanner sc) {
    	
        System.out.println("\n---------------- INSERTAR COMPONENTE ----------------");
        System.out.print("Introduzca el ID del componente: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Introduzca la descripción: ");
        String descripcion = sc.nextLine();
        System.out.print("Introduzca el precio: ");
        double precio = sc.nextDouble();
        System.out.print("Introduzca el stock: ");
        int stock = sc.nextInt();

        Componente componente = new Componente(id, descripcion, precio, stock);
        dao.insertar(componente);
        System.out.println("Componente insertado correctamente");
    }

    /**
     * Registra una compra de dispositivo.
     * 
     * @param empleadoDAO DAO para acceder a datos de empleados
     * @param clienteDAO DAO para acceder a datos de clientes
     * @param dispositivoDAO DAO para acceder a datos de dispositivos
     * @param compraDAO DAO para registrar la compra
     * @param sc Scanner para leer la entrada del usuario
     */
    
    static void realizarCompraDispositivo(EmpleadoDAO empleadoDAO, ClienteDAO clienteDAO, DispositivoDAO dispositivoDAO, CompraDispositivosDAO compraDAO, Scanner sc) {
    	
        System.out.println("\n---------------- REALIZAR COMPRAS DE DISPOSITIVO ----------------");
        System.out.print("Introduzca el ID del dispositivo: ");
        int idDispositivo = sc.nextInt();
        System.out.print("Introduzca el ID del cliente: ");
        int idCliente = sc.nextInt();
        System.out.print("Introduzca el ID del empleado: ");
        int idEmpleado = sc.nextInt();
        System.out.print("Introduzca la Cantidad: ");
        int cantidad = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Introduzca la Fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();

        // Verificar existencia
        Dispositivo dispositivo = dispositivoDAO.buscarPorId(idDispositivo);
        Cliente cliente = clienteDAO.buscarPorId(idCliente);
        Empleado empleado = empleadoDAO.buscarPorId(idEmpleado);

        if (dispositivo == null || cliente == null || empleado == null) {
            System.out.println("Error: Dispositivo, cliente o empleado no encontrado");
            return;
        }

        if (dispositivo.getStock() < cantidad) {
            System.out.println("Error: Stock insuficiente");
            return;
        }

        CompraDispositivos compra = new CompraDispositivos(idDispositivo, idCliente, idEmpleado, fecha, cantidad);
        compraDAO.insertar(compra);
        
        // Actualizar stock
        dispositivo.setStock(dispositivo.getStock() - cantidad);
        dispositivoDAO.actualizar(dispositivo);
        
        System.out.println("Compra registrada correctamente");
    }

    /**
     * Registra una compra de componente.
     * 
     * @param empleadoDAO DAO para acceder a datos de empleados
     * @param clienteDAO DAO para acceder a datos de clientes
     * @param componenteDAO DAO para acceder a datos de componentes
     * @param compraDAO DAO para registrar la compra
     * @param sc Scanner para leer la entrada del usuario
     */
    
    private static void realizarCompraComponente(EmpleadoDAO empleadoDAO, ClienteDAO clienteDAO, ComponenteDAO componenteDAO, CompraComponentesDAO compraDAO, Scanner sc) {
    	
        System.out.println("\n---------------- REALIZAR COMPRAS DE COMPONENTE ----------------");
        System.out.print("Introduzca el ID del componente: ");
        int idComponente = sc.nextInt();
        System.out.print("Introduzca el ID del cliente: ");
        int idCliente = sc.nextInt();
        System.out.print("Introduzca el ID del empleado: ");
        int idEmpleado = sc.nextInt();
        System.out.print("Introduzca la Cantidad: ");
        int cantidad = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Introduzca la Fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();

        // Verificar existencia
        Componente componente = componenteDAO.buscarPorId(idComponente);
        Cliente cliente = clienteDAO.buscarPorId(idCliente);
        Empleado empleado = empleadoDAO.buscarPorId(idEmpleado);

        if (componente == null || cliente == null || empleado == null) {
            System.out.println("Error: Componente, cliente o empleado no encontrado");
            return;
        }

        if (componente.getStock() < cantidad) {
            System.out.println("Error: Stock insuficiente");
            return;
        }

        CompraComponentes compra = new CompraComponentes(idComponente, idCliente, idEmpleado, fecha, cantidad);
        compraDAO.insertar(compra);
        
        // Actualizar stock
        componente.setStock(componente.getStock() - cantidad);
        componenteDAO.actualizar(componente);
        
        System.out.println("Compra registrada correctamente");
    }

    /**
     * Lista todas las compras de dispositivos.
     * 
     * @param dao Objeto CompraDispositivosDAO para acceder a los datos
     */
    
    private static void listarComprasDispositivos(CompraDispositivosDAO dao) {
    	
        ArrayList<CompraDispositivos> compra = dao.listarTodos();
        System.out.println("\n---------------- LISTADO DE COMPRAS DE DISPOSITIVOS ----------------");
        compra.forEach(System.out::println);
    }

    /**
     * Lista todas las compras de componentes.
     * 
     * @param dao Objeto CompraComponentesDAO para acceder a los datos
     */
    
    private static void listarComprasComponentes(CompraComponentesDAO dao) {
    	
        ArrayList<CompraComponentes> compra = dao.listarTodos();
        System.out.println("\n---------------- LISTADO DE COMPRAS DE COMPONENTES ----------------");
        compra.forEach(System.out::println);
    }

    /**
     * Genera un informe de compras agrupadas por departamento.
     * 
     * @param empleadoDAO DAO para acceder a datos de empleados
     * @param compraDispositivoDAO DAO para acceder a compras de dispositivos
     * @param compraComponenteDAO DAO para acceder a compras de componentes
     */
    
    static void generarInformeComprasPorDepartamento(EmpleadoDAO empleadoDAO, CompraDispositivosDAO compraDispositivoDAO, CompraComponentesDAO compraComponenteDAO) {
    	
        System.out.println("\n---------------- INFORME DE COMPRAS POR DEPARTAMENTO ----------------");
        
        // Obtener todas las compras
        ArrayList<CompraDispositivos> comprasDispositivos = compraDispositivoDAO.listarTodos();
        ArrayList<CompraComponentes> comprasComponentes = compraComponenteDAO.listarTodos();
        
        // Procesar compras por departamento
        ArrayList<CantidadComprasPorDepartamento> informe = new ArrayList<>();
        
        // Procesar compras de dispositivos
        for (CompraDispositivos compra : comprasDispositivos) {
            Empleado empleado = empleadoDAO.buscarPorId(compra.getEmpleadoId());
            if (empleado != null) {
                boolean encontrado = false;
                for (CantidadComprasPorDepartamento item : informe) {
                    if (item.getDepartamentoId() == empleado.getDepartamentoId()) {
                        item.incrementarVentas(compra.getCantidad());
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    Departamento dep = empleadoDAO.obtenerDepartamento(empleado.getDepartamentoId());
                    informe.add(new CantidadComprasPorDepartamento(
                        empleado.getDepartamentoId(), 
                        dep.getNombre(), 
                        compra.getCantidad()
                    ));
                }
            }
        }
        
        // Procesar compras de componentes
        for (CompraComponentes compra : comprasComponentes) {
            Empleado empleado = empleadoDAO.buscarPorId(compra.getEmpleadoId());
            if (empleado != null) {
                boolean encontrado = false;
                for (CantidadComprasPorDepartamento item : informe) {
                    if (item.getDepartamentoId() == empleado.getDepartamentoId()) {
                        item.incrementarVentas(compra.getCantidad());
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    Departamento dep = empleadoDAO.obtenerDepartamento(empleado.getDepartamentoId());
                    informe.add(new CantidadComprasPorDepartamento(
                        empleado.getDepartamentoId(), 
                        dep.getNombre(), 
                        compra.getCantidad()
                    ));
                }
            }
        }
        
        // Mostrar resultados
        System.out.println("Departamento\t\tTotal Compras");
        System.out.println("----------------------------------");
        for (CantidadComprasPorDepartamento item : informe) {
            System.out.printf("%-20s\t%d\n", item.getNombreDepartamento(), item.getTotalVentas());
        }
    }
}
