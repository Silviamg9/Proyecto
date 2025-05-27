
USE empresaMaterialInformatico;

/* 1. CONSULTAS MULTITABLA */

/* Consulta 1: Obtener nombre de cliente, nombre del componente comprado y cantidad (JOIN de tres tablas) */
SELECT c.Nombre AS Cliente, cmp.Descripcion AS Componente, cc.Cantidad
FROM COMPRA_COMPONENTES cc
JOIN CLIENTE c ON cc.CLIENTE_ID_Cliente = c.ID_Cliente
JOIN COMPONENTES cmp ON cc.COMPONENTES_ID_Componente = cmp.ID_Componente;

/* Consulta 2: Mostrar dispositivos comprados con fecha y nombre del empleado que realizó la venta */
SELECT d.Nombre AS Dispositivo, cd.Fecha, e.Nombre AS Empleado
FROM COMPRA_DISPOSITIVOS cd
JOIN DISPOSITIVO d ON cd.DISPOSITIVO_ID_Dispositivo = d.ID_Dispositivo
JOIN EMPLEADO e ON cd.EMPLEADO_ID_Empleado = e.ID_Empleado;

/* Consulta 3: Clientes que han comprado más de 1 componente diferente (uso de GROUP BY y HAVING) */
SELECT c.ID_Cliente, c.Nombre, COUNT(DISTINCT cc.COMPONENTES_ID_Componente) AS TotalComponentes
FROM CLIENTE c
JOIN COMPRA_COMPONENTES cc ON c.ID_Cliente = cc.CLIENTE_ID_Cliente
GROUP BY c.ID_Cliente
HAVING COUNT(DISTINCT cc.COMPONENTES_ID_Componente) > 1;

/* Consulta 4: Dispositivos que contienen más de 1 componente (uso de subconsulta) */
SELECT d.Nombre, d.ID_Dispositivo
FROM DISPOSITIVO d
WHERE d.ID_Dispositivo IN (
    SELECT DISPOSITIVO_ID_Dispositivo
    FROM CONSTA
    GROUP BY DISPOSITIVO_ID_Dispositivo
    HAVING COUNT(*) > 1
);

/* Consulta 5: Total de compras de dispositivos por departamento del empleado que vendió (JOIN + GROUP BY) */
SELECT dep.Nombre AS Departamento, COUNT(*) AS TotalCompras
FROM COMPRA_DISPOSITIVOS cd
JOIN EMPLEADO e ON cd.EMPLEADO_ID_Empleado = e.ID_Empleado
JOIN DEPARTAMENTO dep ON e.DEPARTAMENTO_ID_Departamento = dep.ID_Departamento
GROUP BY dep.Nombre;

/* 2. ACTUALIZACIONES */

/* Actualización 1: Aumentar el stock de componentes con más de 2 compras en total */
UPDATE COMPONENTES
SET Stock = Stock + 5
WHERE ID_Componente IN (
    SELECT COMPONENTES_ID_Componente
    FROM COMPRA_COMPONENTES
    GROUP BY COMPONENTES_ID_Componente
    HAVING COUNT(*) > 2
);

/* Actualización 2: Cambiar estado de dispositivos a 'Nuevo' si su stock es 0 */
UPDATE DISPOSITIVO
SET Estado = 'Nuevo'
WHERE Stock = 0;

/* 3. BORRADO */

/* Borrado 1: Eliminar compras de componentes con cantidad = 1 y fecha anterior a 2024-01-01 */
DELETE FROM COMPRA_COMPONENTES
WHERE Cantidad = 1 AND Fecha < '2024-01-01';

/* 4. VISTAS */

/* Vista 1: Vista de resumen de compras de componentes con nombre cliente y componente */
CREATE OR REPLACE VIEW Vista_Resumen_Compras_Componentes AS
SELECT c.Nombre AS Cliente, cmp.Descripcion AS Componente, cc.Cantidad, cc.Fecha
FROM COMPRA_COMPONENTES cc
JOIN CLIENTE c ON cc.CLIENTE_ID_Cliente = c.ID_Cliente
JOIN COMPONENTES cmp ON cc.COMPONENTES_ID_Componente = cmp.ID_Componente;

/* Vista 2: Vista de empleados y total de ventas de dispositivos */
CREATE OR REPLACE VIEW Vista_Empleados_Ventas_Dispositivos AS
SELECT e.ID_Empleado, e.Nombre, COUNT(cd.DISPOSITIVO_ID_Dispositivo) AS TotalVentas
FROM EMPLEADO e
JOIN COMPRA_DISPOSITIVOS cd ON e.ID_Empleado = cd.EMPLEADO_ID_Empleado
GROUP BY e.ID_Empleado, e.Nombre;

/* 5. PROCEDIMIENTOS Y FUNCIONES */

/* Procedimiento 1: Insertar nueva compra de componente */
DELIMITER //
CREATE PROCEDURE InsertarCompraComponente (
    IN p_ComponenteID INT,
    IN p_ClienteID INT,
    IN p_EmpleadoID INT,
    IN p_Fecha DATE,
    IN p_Cantidad INT
)
BEGIN
    INSERT INTO COMPRA_COMPONENTES VALUES (p_ComponenteID, p_ClienteID, p_EmpleadoID, p_Fecha, p_Cantidad);
END //
DELIMITER ;

/* Procedimiento 2: Mostrar stock actual de un componente */
DELIMITER //
CREATE PROCEDURE MostrarStockComponente (IN p_ID INT)
BEGIN
    SELECT Descripcion, Stock FROM COMPONENTES WHERE ID_Componente = p_ID;
END //
DELIMITER ;

/* Función 1: Obtener total de compras de un cliente */
DELIMITER //
CREATE FUNCTION TotalComprasCliente(p_ID INT) RETURNS INT
READS SQL DATA
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM COMPRA_COMPONENTES WHERE CLIENTE_ID_Cliente = p_ID;
    RETURN total;
END //
DELIMITER ;

/* 6. DISPARADORES */

/* Trigger BEFORE: Validar que el stock del componente sea suficiente antes de la compra */
DELIMITER //
CREATE TRIGGER before_insert_compra_componentes
BEFORE INSERT ON COMPRA_COMPONENTES
FOR EACH ROW
BEGIN
    DECLARE stockActual INT;
    SELECT Stock INTO stockActual FROM COMPONENTES WHERE ID_Componente = NEW.COMPONENTES_ID_Componente;
    IF stockActual < NEW.Cantidad THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Stock insuficiente para la compra';
    END IF;
END //
DELIMITER ;

/* Trigger AFTER: Reducir stock del componente después de la compra */
DELIMITER //
CREATE TRIGGER after_insert_compra_componentes
AFTER INSERT ON COMPRA_COMPONENTES
FOR EACH ROW
BEGIN
    UPDATE COMPONENTES
    SET Stock = Stock - NEW.Cantidad
    WHERE ID_Componente = NEW.COMPONENTES_ID_Componente;
END //
DELIMITER ;
