package crudProyEmpMatInf;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

class AppProyEmpMatInfCRUDTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testMostrarMenu() {
        AppProyEmpMatInfCRUD.mostrarMenu();
        String output = outContent.toString();
        assertTrue(output.contains("MENÚ"));
        assertTrue(output.contains("1. Listar departamentos"));
        assertTrue(output.contains("17. Salir"));
    }

    @Test
    void testInsertarDepartamento() {
        String input = "101\nDepartamento Test\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner sc = new Scanner(System.in);
        
        DepartamentoDAO dao = new DepartamentoDAO();
        AppProyEmpMatInfCRUD.insertarDepartamento(dao, sc);
        
        ArrayList<Departamento> departamentos = dao.listarTodos();
        boolean encontrado = false;
        for (Departamento d : departamentos) {
            if (d.getId() == 101 && d.getNombre().equals("Departamento Test")) {
                encontrado = true;
                break;
            }
        }
        assertTrue(encontrado);
    }

    @Test
    void testListarEmpleados() {
        EmpleadoDAO dao = new EmpleadoDAO();
        // Insertar un empleado de prueba
        Empleado emp = new Empleado(1, "12345678A", "Test", "Empleado", null, "123456789", 1);
        dao.insertar(emp);
        
        AppProyEmpMatInfCRUD.listarEmpleados(dao);
        String output = outContent.toString();
        assertTrue(output.contains("LISTADO DE EMPLEADOS"));
        assertTrue(output.contains("12345678A"));
    }

    @Test
    void testGenerarInformeComprasPorDepartamento() {
        // Configurar datos de prueba
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        CompraDispositivosDAO compraDispositivoDAO = new CompraDispositivosDAO();
        CompraComponentesDAO compraComponenteDAO = new CompraComponentesDAO();
        
        // Insertar departamento y empleado
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        departamentoDAO.insertar(new Departamento(1, "Ventas"));
        empleadoDAO.insertar(new Empleado(1, "11111111A", "Vendedor", "Test", null, "111111111", 1));
        
        // Insertar compras de prueba
        compraDispositivoDAO.insertar(new CompraDispositivos(1, 1, 1, "2023-11-15", 2));
        compraComponenteDAO.insertar(new CompraComponentes(1, 1, 1, "2023-11-15", 3));
        
        AppProyEmpMatInfCRUD.generarInformeComprasPorDepartamento(
            empleadoDAO, compraDispositivoDAO, compraComponenteDAO);
        
        String output = outContent.toString();
        assertTrue(output.contains("INFORME DE COMPRAS POR DEPARTAMENTO"));
        assertTrue(output.contains("Ventas"));
        assertTrue(output.contains("5")); // 2 + 3
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}
