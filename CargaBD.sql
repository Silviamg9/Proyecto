USE empresaMaterialInformatico;

/* INSERT DEPARTAMENTO */
INSERT INTO DEPARTAMENTO VALUES
(1, 'Informática'),
(2, 'Ventas'),
(3, 'Recursos Humanos'),
(4, 'Soporte Técnico'),
(5, 'Administración'),
(6, 'Marketing');

/* INSERT EMPLEADO */
INSERT INTO EMPLEADO VALUES
(1, '12345678A', 'Ana', 'Gómez', 'Pérez', '600123456', 1),
(2, '87654321B', 'Luis', 'Martínez', 'Sánchez', '600654321', 2),
(3, '22223333E', 'María', 'Gutiérrez', 'López', '601112233', 3),
(4, '44445555F', 'Javier', 'Moreno', 'Hernández', '601223344', 4),
(5, '55556666G', 'Lucía', 'Santos', 'Jiménez', '601334455', 5),
(6, '66667777H', 'Carlos', 'Vega', 'González', '601445566', 6);

/* INSERT CLIENTE */
INSERT INTO CLIENTE VALUES
(1, '11112222C', 'Carlos', 'Fernández', 'Ruiz', '600789123'),
(2, '33334444D', 'Marta', 'López', 'García', '600321987'),
(3, '55556666H', 'Pedro', 'Ramírez', 'Ortiz', '601556677'),
(4, '66667777I', 'Laura', 'Castro', 'Martínez', '601667788'),
(5, '77778888J', 'Sergio', 'Navarro', 'Torres', '601778899'),
(6, '88889999K', 'Isabel', 'Ruiz', 'Gómez', '601889900');

/* INSERT DISPOSITIVO */
INSERT INTO DISPOSITIVO VALUES
(1, 'Ordenador Portátil', 1200.50, 10, 'Nuevo'),
(2, 'Teléfono Inteligente', 750.00, 15, 'Segunda Mano'),
(3, 'Monitor 27"', 300.99, 8, 'Segunda Mano'),
(4, 'Tablet Android', 350.00, 12, 'Segunda Mano'),
(5, 'Impresora Multifunción', 220.00, 5, 'Nuevo'),
(6, 'Auriculares Bluetooth', 90.00, 25, 'Segunda Mano'),
(7, 'Teclado Mecánico', 120.00, 18, 'Nuevo');

/* INSERT COMPONENTES */
INSERT INTO COMPONENTES VALUES
(1, 'Procesador Intel i7', 300.00, 20),
(2, 'Memoria RAM 16GB', 120.00, 30),
(3, 'SSD 1TB', 150.00, 25),
(4, 'Tarjeta gráfica NVIDIA', 450.00, 10),
(5, 'Batería portátil', 60.00, 40),
(6, 'Pantalla Retina 15"', 200.00, 8),
(7, 'Placa base ASUS', 180.00, 15);

/* INSERT CONSTA */
INSERT INTO CONSTA VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 1),
(4, 1, 1),
(2, 2, 1),
(5, 2, 1),
(6, 3, 1),
(2, 3, 2),
(7, 4, 1),
(3, 4, 1),
(5, 5, 1),
(7, 5, 1),
(6, 6, 1),
(5, 6, 1),
(2, 7, 1);

/* INSERT COMPRA_DISPOSITIVOS */
INSERT INTO COMPRA_DISPOSITIVOS VALUES
(1, 2, 3, '2024-05-14', 1),
(2, 5, 1, '2024-11-22', 1),
(3, 1, 4, '2025-02-18', 1),
(4, 3, 2, '2024-07-09', 1),
(5, 4, 5, '2024-08-10', 1),
(2, 2, 2, '2025-03-30', 1),
(1, 5, 1, '2024-12-12', 1),
(5, 1, 3, '2025-04-02', 1),
(3, 4, 5, '2024-06-15', 1),
(4, 2, 1, '2024-10-25', 1),
(2, 3, 4, '2025-01-17', 1),
(5, 5, 2, '2025-03-05', 1),
(1, 1, 5, '2025-02-28', 1),
(3, 2, 1, '2024-05-07', 1),
(4, 4, 3, '2024-12-01', 1);

/* INSERT COMPRA_COMPONENTES */
INSERT INTO COMPRA_COMPONENTES (COMPONENTES_ID_Componente, CLIENTE_ID_Cliente, Cantidad, Fecha, EMPLEADO_ID_Empleado) VALUES
(1, 1, 2, '2024-01-10', 2),
(2, 1, 3, '2024-03-22', 1),
(3, 1, 1, '2024-06-15', 1),
(4, 2, 4, '2024-07-11', 1),
(5, 2, 2, '2024-09-30', 3),
(6, 3, 5, '2024-10-08', 1),
(7, 3, 6, '2024-11-17', 1),
(1, 4, 1, '2025-01-25', 1),
(3, 5, 3, '2025-02-05', 2),
(2, 6, 5, '2025-03-12', 1);

