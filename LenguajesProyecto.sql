CREATE TABLE Direccion
(
    id_direccion NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    referencias VARCHAR2(80) NOT NULL
);

CREATE TABLE Tipo_Empresa
(
    id_tipo_empresa NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    tipo_empresa VARCHAR2(20) NOT NULL
);

CREATE TABLE Estado_Unidad
(
    id_estado_unidad NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    estado_unidad VARCHAR2(20) NOT NULL
);

CREATE TABLE Empleado
(
    id_empleado NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre_empleado VARCHAR2(40) NOT NULL,
    apellido_empleado VARCHAR2(60) NOT NULL,
    identificacion_empleado NUMBER NOT NULL,
    id_direccion NUMBER,
    CONSTRAINT fk_empleado_direccion FOREIGN KEY (id_direccion) REFERENCES Direccion(id_direccion)
);

CREATE TABLE Empresa
(
    id_empresa NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre_empresa VARCHAR2(60) NOT NULL,
    identificacion VARCHAR2(20) NOT NULL,
    fecha_ingreso DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    direccion VARCHAR2(100) NOT NULL,
    observaciones VARCHAR2(100),
    id_tipo_empresa NUMBER,
    CONSTRAINT fk_empresa_tipo_empresa FOREIGN KEY (id_tipo_empresa) REFERENCES Tipo_Empresa(id_tipo_empresa)
);

CREATE TABLE Unidades
(
    id_placa NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_estado_unidad NUMBER,
    id_empresa NUMBER,
    unidad_year VARCHAR2(10) NOT NULL,
    capacidad_carga VARCHAR2(20) NOT NULL,
    chasis VARCHAR2(30) NOT NULL,
    descripcion VARCHAR2(200) NOT NULL,
    CONSTRAINT fk_unidades_estado FOREIGN KEY (id_estado_unidad) REFERENCES Estado_Unidad(id_estado_unidad),
    CONSTRAINT fk_unidades_empresa FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa)
);

CREATE TABLE Pedidos
(
    id_pedido NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre_empresa VARCHAR2(60) NOT NULL,
    nombre_empresa_cliente VARCHAR2(60) NOT NULL,
    fecha_ingreso DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    limite_entrega DATE NOT NULL,
    id_empresa NUMBER,
    CONSTRAINT fk_pedidos_empresa FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa)
);
----==============CRUDS CON CSP================----
----==========DIRECCION==========----
----CSP ADD Direccion
CREATE SEQUENCE secuencia_direccion;
CREATE OR REPLACE PROCEDURE agregar_direccion(
    p_referencias IN direccion.referencias%TYPE
) AS
    v_id_direccion direccion.id_direccion%TYPE;
BEGIN
    -- id automatico
    SELECT secuencia_direccion.NEXTVAL INTO v_id_direccion FROM dual;

    INSERT INTO direccion (id_direccion, referencias)
    VALUES (v_id_direccion, p_referencias);

    COMMIT;
END;
/
--INPUT/OUTPUT--
DECLARE
    p_referencias direccion.referencias%TYPE;
BEGIN
    -- Asignar el valor de referencias
    p_referencias := 'Ejemplo Direcccion';

    -- Ejecutar el procedimiento almacenado
    agregar_direccion(p_referencias);
    
    -- Confirmar los cambios
    COMMIT;
    
    -- Mostrar mensaje de éxito
    DBMS_OUTPUT.PUT_LINE('Procedimiento almacenado ejecutado correctamente');
EXCEPTION
    WHEN OTHERS THEN
        -- Mostrar mensaje de error
        DBMS_OUTPUT.PUT_LINE('Error al ejecutar el procedimiento almacenado: ' || SQLERRM);
        ROLLBACK;
END;
/
--------------------------------------------------------------------
----CSP READ Direccion
CREATE OR REPLACE PROCEDURE OBTENER_DIRECCIONES(p_resultados OUT SYS_REFCURSOR) AS
BEGIN
    OPEN p_resultados FOR SELECT * FROM direccion;
END;
/
--------------------------------------------------------------------
----CSP UPDATE Direccion
CREATE OR REPLACE PROCEDURE ACTUALIZAR_DIRECCION(
    p_id_direccion IN direccion.id_direccion%TYPE,
    p_referencias IN direccion.referencias%TYPE
) AS
BEGIN
    UPDATE direccion
    SET referencias = p_referencias
    WHERE id_direccion = p_id_direccion;
    COMMIT;
END;
/
--------------------------------------------------------------------
----CSP DELETE Direccion
CREATE OR REPLACE PROCEDURE ELIMINAR_DIRECCION(p_id_direccion IN direccion.id_direccion%TYPE) AS
BEGIN
    DELETE FROM direccion WHERE id_direccion = p_id_direccion;
    COMMIT;
END;
/
----==========Tipo Empresa==========----
CREATE SEQUENCE secuencia_tipo_empresa;

CREATE OR REPLACE PROCEDURE agregar_tipo_empresa(
    p_tipo_empresa IN tipo_empresa.tipo_empresa%TYPE
) AS
    v_id_tipo_empresa tipo_empresa.id_tipo_empresa%TYPE;
BEGIN
    -- id automatico
    SELECT secuencia_tipo_empresa.NEXTVAL INTO v_id_tipo_empresa FROM dual;

    INSERT INTO tipo_empresa (id_tipo_empresa, tipo_empresa)
    VALUES (v_id_tipo_empresa, p_tipo_empresa);

    COMMIT;
END;
/
--------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE obtener_tipos_empresa(p_resultados OUT SYS_REFCURSOR) AS
BEGIN
    OPEN p_resultados FOR SELECT * FROM tipo_empresa;
END;
/
--------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE actualizar_tipo_empresa(
    p_id_tipo_empresa IN tipo_empresa.id_tipo_empresa%TYPE,
    p_tipo_empresa IN tipo_empresa.tipo_empresa%TYPE
) AS
BEGIN
    UPDATE tipo_empresa
    SET tipo_empresa = p_tipo_empresa
    WHERE id_tipo_empresa = p_id_tipo_empresa;
    COMMIT;
END;
/
--------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE eliminar_tipo_empresa(p_id_tipo_empresa IN tipo_empresa.id_tipo_empresa%TYPE) AS
BEGIN
    DELETE FROM tipo_empresa WHERE id_tipo_empresa = p_id_tipo_empresa;
    COMMIT;
END;
/

----==========Estado Unidad==========----
CREATE SEQUENCE secuencia_estado_unidad;

CREATE OR REPLACE PROCEDURE agregar_estado_unidad(
    p_estado_unidad IN estado_unidad.estado_unidad%TYPE
) AS
    v_id_estado_unidad estado_unidad.id_estado_unidad%TYPE;
BEGIN
    -- id automatico
    SELECT secuencia_estado_unidad.NEXTVAL INTO v_id_estado_unidad FROM dual;

    INSERT INTO estado_unidad (id_estado_unidad, estado_unidad)
    VALUES (v_id_estado_unidad, p_estado_unidad);

    COMMIT;
END;
/
--------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE obtener_estados_unidad(p_resultados OUT SYS_REFCURSOR) AS
BEGIN
    OPEN p_resultados FOR SELECT * FROM estado_unidad;
END;
/
--------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE actualizar_estado_unidad(
    p_id_estado_unidad IN estado_unidad.id_estado_unidad%TYPE,
    p_estado_unidad IN estado_unidad.estado_unidad%TYPE
) AS
BEGIN
    UPDATE estado_unidad
    SET estado_unidad = p_estado_unidad
    WHERE id_estado_unidad = p_id_estado_unidad;
    COMMIT;
END;
/
--------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE eliminar_estado_unidad(p_id_estado_unidad IN estado_unidad.id_estado_unidad%TYPE) AS
BEGIN
    DELETE FROM estado_unidad WHERE id_estado_unidad = p_id_estado_unidad;
    COMMIT;
END;
/
----==========Empleado==========----
CREATE SEQUENCE secuencia_empleado;

CREATE OR REPLACE PROCEDURE agregar_empleado(
    p_nombre_empleado IN empleado.nombre_empleado%TYPE,
    p_apellido_empleado IN empleado.apellido_empleado%TYPE,
    p_identificacion_empleado IN empleado.identificacion_empleado%TYPE,
    p_id_direccion IN empleado.id_direccion%TYPE
) AS
    v_id_empleado empleado.id_empleado%TYPE;
BEGIN
    -- id automatico
    SELECT secuencia_empleado.NEXTVAL INTO v_id_empleado FROM dual;

    INSERT INTO empleado (id_empleado, nombre_empleado, apellido_empleado, identificacion_empleado, id_direccion)
    VALUES (v_id_empleado, p_nombre_empleado, p_apellido_empleado, p_identificacion_empleado, p_id_direccion);

    COMMIT;
END;
/
--------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE obtener_empleados(p_resultados OUT SYS_REFCURSOR) AS
BEGIN
    OPEN p_resultados FOR SELECT * FROM empleado;
END;
/
--------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE actualizar_empleado(
    p_id_empleado IN empleado.id_empleado%TYPE,
    p_nombre_empleado IN empleado.nombre_empleado%TYPE,
    p_apellido_empleado IN empleado.apellido_empleado%TYPE,
    p_identificacion_empleado IN empleado.identificacion_empleado%TYPE,
    p_id_direccion IN empleado.id_direccion%TYPE
) AS
BEGIN
    UPDATE empleado
    SET nombre_empleado = p_nombre_empleado,
        apellido_empleado = p_apellido_empleado,
        identificacion_empleado = p_identificacion_empleado,
        id_direccion = p_id_direccion
    WHERE id_empleado = p_id_empleado;
    COMMIT;
END;
/
--------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE eliminar_empleado(p_id_empleado IN empleado.id_empleado%TYPE) AS
BEGIN
    DELETE FROM empleado WHERE id_empleado = p_id_empleado;
    COMMIT;
END;
/
----==========EMPRESA==========----
-- CSP ADD Empresa
CREATE SEQUENCE secuencia_empresa;
CREATE OR REPLACE PROCEDURE agregar_empresa(
    p_nombre_empresa IN empresa.nombre_empresa%TYPE,
    p_identificacion IN empresa.identificacion%TYPE,
    p_fecha_ingreso IN empresa.fecha_ingreso%TYPE,
    p_fecha_registro IN empresa.fecha_registro%TYPE,
    p_direccion IN empresa.direccion%TYPE,
    p_observaciones IN empresa.observaciones%TYPE,
    p_id_tipo_empresa IN empresa.id_tipo_empresa%TYPE
) AS
    v_id_empresa empresa.id_empresa%TYPE;
BEGIN
    -- id automatico
    SELECT secuencia_empresa.NEXTVAL INTO v_id_empresa FROM dual;

    INSERT INTO empresa (id_empresa, nombre_empresa, identificacion, fecha_ingreso, fecha_registro, direccion, observaciones, id_tipo_empresa)
    VALUES (v_id_empresa, p_nombre_empresa, p_identificacion, p_fecha_ingreso, p_fecha_registro, p_direccion, p_observaciones, p_id_tipo_empresa);

    COMMIT;
END;
/

-- CSP READ Empresa
CREATE OR REPLACE PROCEDURE obtener_empresas(p_resultados OUT SYS_REFCURSOR) AS
BEGIN
    OPEN p_resultados FOR SELECT * FROM empresa;
END;
/

-- CSP UPDATE Empresa
CREATE OR REPLACE PROCEDURE actualizar_empresa(
    p_id_empresa IN empresa.id_empresa%TYPE,
    p_nombre_empresa IN empresa.nombre_empresa%TYPE,
    p_identificacion IN empresa.identificacion%TYPE,
    p_fecha_ingreso IN empresa.fecha_ingreso%TYPE,
    p_fecha_registro IN empresa.fecha_registro%TYPE,
    p_direccion IN empresa.direccion%TYPE,
    p_observaciones IN empresa.observaciones%TYPE,
    p_id_tipo_empresa IN empresa.id_tipo_empresa%TYPE
) AS
BEGIN
    UPDATE empresa
    SET nombre_empresa = p_nombre_empresa,
        identificacion = p_identificacion,
        fecha_ingreso = p_fecha_ingreso,
        fecha_registro = p_fecha_registro,
        direccion = p_direccion,
        observaciones = p_observaciones,
        id_tipo_empresa = p_id_tipo_empresa
    WHERE id_empresa = p_id_empresa;
    
    COMMIT;
END;
/

-- CSP DELETE Empresa
CREATE OR REPLACE PROCEDURE eliminar_empresa(p_id_empresa IN empresa.id_empresa%TYPE) AS
BEGIN
    DELETE FROM empresa WHERE id_empresa = p_id_empresa;
    COMMIT;
END;
/


----==========Unidad==========----
-- CSP ADD Unidad
CREATE SEQUENCE secuencia_unidad;
CREATE OR REPLACE PROCEDURE agregar_unidad(
    p_id_estado_unidad IN unidades.id_estado_unidad%TYPE,
    p_id_empresa IN unidades.id_empresa%TYPE,
    p_unidad_year IN unidades.unidad_year%TYPE,
    p_capacidad_carga IN unidades.capacidad_carga%TYPE,
    p_chasis IN unidades.chasis%TYPE,
    p_descripcion IN unidades.descripcion%TYPE
) AS
    v_id_placa unidades.id_placa%TYPE;
BEGIN
    -- id automatico
    SELECT secuencia_unidad.NEXTVAL INTO v_id_placa FROM dual;

    INSERT INTO unidades (id_placa, id_estado_unidad, id_empresa, unidad_year, capacidad_carga, chasis, descripcion)
    VALUES (v_id_placa, p_id_estado_unidad, p_id_empresa, p_unidad_year, p_capacidad_carga, p_chasis, p_descripcion);

    COMMIT;
END;
/

-- CSP READ Unidades
CREATE OR REPLACE PROCEDURE obtener_unidades(p_resultados OUT SYS_REFCURSOR) AS
BEGIN
    OPEN p_resultados FOR SELECT * FROM unidades;
END;
/

-- CSP UPDATE Unidad
CREATE OR REPLACE PROCEDURE actualizar_unidad(
    p_id_placa IN unidades.id_placa%TYPE,
    p_id_estado_unidad IN unidades.id_estado_unidad%TYPE,
    p_id_empresa IN unidades.id_empresa%TYPE,
    p_unidad_year IN unidades.unidad_year%TYPE,
    p_capacidad_carga IN unidades.capacidad_carga%TYPE,
    p_chasis IN unidades.chasis%TYPE,
    p_descripcion IN unidades.descripcion%TYPE
) AS
BEGIN
    UPDATE unidades
    SET id_estado_unidad = p_id_estado_unidad,
        id_empresa = p_id_empresa,
        unidad_year = p_unidad_year,
        capacidad_carga = p_capacidad_carga,
        chasis = p_chasis,
        descripcion = p_descripcion
    WHERE id_placa = p_id_placa;

    COMMIT;
END;
/

-- CSP DELETE Unidad
CREATE OR REPLACE PROCEDURE eliminar_unidad(p_id_placa IN unidades.id_placa%TYPE) AS
BEGIN
    DELETE FROM unidades WHERE id_placa = p_id_placa;
    COMMIT;
END;
/

----==============VIEWS, CSP, FUNC & CURSOR================----
--DIRECCION--
----=======================================================----
-- Crear la vista
CREATE OR REPLACE VIEW vw_direccion_info AS
SELECT id_direccion, referencias
FROM Direccion;

-- Crear la función
CREATE OR REPLACE FUNCTION obtener_informacion(parametro IN VARCHAR2) RETURN VARCHAR2 IS
    resultado_info VARCHAR2(100);
BEGIN
    -- Lógica de la función obtener_informacion
    -- Asignar el resultado a la variable resultado_info
    RETURN resultado_info;
END;
/

-- Crear el procedimiento almacenado con función y cursor
CREATE OR REPLACE PROCEDURE obtener_datos_direccion(p_resultados OUT SYS_REFCURSOR) AS
    -- Declarar el cursor explícito
    CURSOR c_datos_direccion IS
        SELECT * FROM vw_direccion_info; -- Utilizar la vista vw_direccion_info en lugar de la tabla directamente
    v_id_direccion Direccion.id_direccion%TYPE; -- Utilizar el tipo de dato de la tabla Direccion
    v_referencias Direccion.referencias%TYPE; -- Utilizar el tipo de dato de la tabla Direccion
    v_informacion VARCHAR2(100); -- Variable para almacenar el resultado de la función obtener_informacion
BEGIN
    -- Abrir el cursor
    OPEN c_datos_direccion;
    
    -- Recorrer el cursor y realizar operaciones
    LOOP
        -- Obtener los datos de la fila actual
        FETCH c_datos_direccion INTO v_id_direccion, v_referencias;
        EXIT WHEN c_datos_direccion%NOTFOUND;  -- Salir del bucle si no hay más filas
        
        -- Lógica adicional y llamada a la función obtener_informacion
        -- Utilizar los datos de la fila y llamar a la función obtener_informacion
        v_informacion := obtener_informacion(v_referencias);
        
        -- Realizar otras operaciones o lógica
        -- ...
        
        -- Imprimir los resultados o hacer lo que necesites con ellos
        -- ...
        
    END LOOP;
    
    -- Cerrar el cursor
    CLOSE c_datos_direccion;
    
    -- Asignar el cursor al parámetro de salida
    OPEN p_resultados FOR SELECT * FROM DUAL;
END;
/

--------------------------------------------------------------------------------------------------------------------------------------
    --VISTAS DE LA BASE DE DATOS (10):
--------------------------------------------------------------------------------------------------------------------------------------
    --VISTAS DE LA BASE DE DATOS (10):
--------------------------------------------------------------------------------------------------------------------------------------
    --VISTAS DE LA BASE DE DATOS (10):
--------------------------------------------------------------------------------------------------------------------------------------
    --VISTAS DE LA BASE DE DATOS (10):
--------------------------------------------------------------------------------------------------------------------------------------

    --EMPLEADOS:
--Vista para ver todos los Empleados (Funciona parecido a Select * FROM)
CREATE VIEW Imprimir_Empleados_Vista AS
SELECT id_empleado, nombre_empleado, apellido_empleado, identificacion_empleado
FROM Empleado;
SELECT * FROM Imprimir_Empleados_Vista;

--Vista para ver la cantidad de registros de la Tabla Empleado:
CREATE VIEW Contador_Empleados_Vista AS
SELECT COUNT(*) AS total_empleados
FROM Empleado;
SELECT total_empleados FROM Contador_Empleados_Vista;

--Vista para acceder a los registros de empleado y a la vez a dirección.
CREATE VIEW Empleados_Direccion_Vista AS
SELECT e.id_empleado, e.nombre_empleado, e.apellido_empleado, e.identificacion_empleado, d.id_direccion, d.referencias
FROM Empleado e
JOIN Direccion d ON e.id_direccion = d.id_direccion;

SELECT id_empleado, nombre_empleado, apellido_empleado, identificacion_empleado, id_direccion, referencias
FROM Empleados_Direccion_Vista;

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

--UNIDADES:
--Vista para ver la cantidad de registros de la Tabla Unidades:
CREATE VIEW Contador_Unidades_Vista AS
SELECT * FROM Unidades;
SELECT COUNT(*) AS contador_unidades FROM Contador_Unidades_Vista;

--Vista para mostrar la Unidad pero también, a la empresa que pertenece y su estado.
CREATE VIEW Unidades_EstadoEmpresa_Vista AS
SELECT u.id_placa, u.descripcion, u.id_empresa, u.id_estado_unidad, eu.estado_unidad
FROM Unidades u
JOIN Empresa e ON u.id_empresa = e.id_empresa
JOIN Estado_Unidad eu ON u.id_estado_unidad = eu.id_estado_unidad;

SELECT id_placa, descripcion, id_empresa, id_estado_unidad, estado_unidad
FROM Unidades_EstadoEmpresa_Vista;

--Vista que permita ver la unidad de mayor a menor capacidad de carga.
CREATE VIEW Unidades_CapacidadCarga_Vista AS
SELECT capacidad_carga
FROM Unidades
ORDER BY capacidad_carga DESC;

SELECT capacidad_carga
FROM Unidades_CapacidadCarga_Vista;

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--Empresa:
--Vista CONTADOR para ver la cantidad de registros de la tabla Empresa:
CREATE VIEW Contador_Empresa_Vista AS
SELECT * FROM Empresa;

SELECT COUNT(*) AS contador_empresa FROM Contador_Empresa_Vista;

--Vista para acceder a los registros de empresa, pero también a los registros de su tabla miscelanea.
CREATE VIEW Empresa_TipoEmpresa_Vista AS
SELECT e.id_empresa, e.nombre_empresa, e.fecha_ingreso, e.fecha_registro, e.observaciones, te.id_tipo_empresa, te.tipo_empresa
FROM Empresa e
JOIN Tipo_Empresa te ON e.id_tipo_empresa = te.id_tipo_empresa;

SELECT id_empresa, nombre_empresa, fecha_ingreso, fecha_registro, observaciones, id_tipo_empresa, tipo_empresa
FROM Empresa_TipoEmpresa_Vista;

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--Pedidos:
--Vista para ver la cantidad de registros de la tabla Empresa:
CREATE VIEW Contador_Pedidos_Vista AS
SELECT * FROM Pedidos;

SELECT COUNT(*) AS contador_pedidos FROM Contador_Pedidos_Vista;

--Vista que para ver la fecha de ingreso más reciente de los registros con id.
--Además, la fecha en otra columna de la fecha limite.

CREATE VIEW Pedidos_Fechas_Vista AS
SELECT id_pedido, MAX(fecha_ingreso) AS fecha_ingreso_actual, limite_entrega AS Entrega_Limite
FROM Pedidos
GROUP BY id_pedido, limite_entrega;

SELECT id_pedido, fecha_ingreso_actual, Entrega_Limite
FROM Pedidos_Fechas_Vista;

--BASE DE DATOS (VISTA GENERAL):
CREATE VIEW BaseDeDatos_VistaGeneral AS
SELECT 'Direccion' AS tabla, id_direccion AS id, referencias AS informacion FROM Direccion
UNION ALL
SELECT 'Tipo_Empresa' AS tabla, id_tipo_empresa AS id, tipo_empresa AS informacion FROM Tipo_Empresa
UNION ALL
SELECT 'Estado_Unidad' AS tabla, id_estado_unidad AS id, estado_unidad AS informacion FROM Estado_Unidad
UNION ALL
SELECT 'Empleado' AS tabla, id_empleado AS id, nombre_empleado || ' ' || apellido_empleado AS informacion FROM Empleado
UNION ALL
SELECT 'Empresa' AS tabla, id_empresa AS id, nombre_empresa || ', Identificación: ' || identificacion AS informacion FROM Empresa
UNION ALL
SELECT 'Unidades' AS tabla, id_placa AS id, 'Año: ' || unidad_year || ', Capacidad de carga: ' || capacidad_carga AS informacion FROM Unidades
UNION ALL
SELECT 'Pedidos' AS tabla, id_pedido AS id, 'Cliente: ' || nombre_empresa_cliente || ', Fecha ingreso: ' || fecha_ingreso || ', Fecha límite: ' || limite_entrega AS informacion FROM Pedidos;

SELECT *
FROM BaseDeDatos_VistaGeneral;

--Renombrar Vistas
RENAME Vista_Antigua TO Vista_Nueva;

--Borrar vistas
DROP VIEW NOMBRE_VISTA;


--FINALIZAN LAS VISTAS
--------------------------------------------------------------------------------------------------------------------------------------
--FINALIZAN LAS VISTAS
--------------------------------------------------------------------------------------------------------------------------------------
--FINALIZAN LAS VISTAS
--------------------------------------------------------------------------------------------------------------------------------------
--FINALIZAN LAS VISTAS
--------------------------------------------------------------------------------------------------------------------------------------


-

--------------------------------------------------------------------------------------------------------------------------------------
    --FUNCIONES (15):

    --INSERTAR DIRECCION
    CREATE OR REPLACE FUNCTION insertar_direccion(p_referencias VARCHAR2) RETURN NUMBER IS
    v_id_direccion NUMBER;
BEGIN
    INSERT INTO Direccion(referencias) VALUES (p_referencias)
    RETURNING id_direccion INTO v_id_direccion;

    RETURN v_id_direccion;
END;
/
    
--INSERTAR TIPO EMPRESA
CREATE OR REPLACE FUNCTION insertar_tipo_empresa(p_tipo_empresa VARCHAR2) RETURN NUMBER IS
    v_id_tipo_empresa NUMBER;
BEGIN
    INSERT INTO Tipo_Empresa(tipo_empresa) VALUES (p_tipo_empresa)
    RETURNING id_tipo_empresa INTO v_id_tipo_empresa;

    RETURN v_id_tipo_empresa;
END;
/
    
--INSERTAR ESTADO UNIDAD
CREATE OR REPLACE FUNCTION insertar_estado_unidad(p_estado_unidad VARCHAR2) RETURN NUMBER IS
    v_id_estado_unidad NUMBER;
BEGIN
    INSERT INTO Estado_Unidad(estado_unidad) VALUES (p_estado_unidad)
    RETURNING id_estado_unidad INTO v_id_estado_unidad;

    RETURN v_id_estado_unidad;
END;
/
    
--INSERTAR EMPLEADO
CREATE OR REPLACE FUNCTION insertar_empleado(p_nombre_empleado VARCHAR2, p_apellido_empleado VARCHAR2, p_identificacion_empleado NUMBER, p_id_direccion NUMBER) RETURN NUMBER IS
    v_id_empleado NUMBER;
BEGIN
    INSERT INTO Empleado(nombre_empleado, apellido_empleado, identificacion_empleado, id_direccion)
    VALUES (p_nombre_empleado, p_apellido_empleado, p_identificacion_empleado, p_id_direccion)
    RETURNING id_empleado INTO v_id_empleado;

    RETURN v_id_empleado;
END;
/
    
--OBTENER DIRECCION POR ID
CREATE OR REPLACE FUNCTION obtener_direccion_por_id(p_id_direccion NUMBER) RETURN Direccion%ROWTYPE IS
    v_direccion Direccion%ROWTYPE;
BEGIN
    SELECT * INTO v_direccion FROM Direccion WHERE id_direccion = p_id_direccion;

    RETURN v_direccion;
END;
/
    
--OBTENER TIPO EMPRESA POR ID
CREATE OR REPLACE FUNCTION obtener_tipo_empresa_por_id(p_id_tipo_empresa NUMBER) RETURN Tipo_Empresa%ROWTYPE IS
    v_tipo_empresa Tipo_Empresa%ROWTYPE;
BEGIN
    SELECT * INTO v_tipo_empresa FROM Tipo_Empresa WHERE id_tipo_empresa = p_id_tipo_empresa;

    RETURN v_tipo_empresa;
END;
/
    
--OBTENER ESTADO UNIDAD POR ID
CREATE OR REPLACE FUNCTION obtener_estado_unidad_por_id(p_id_estado_unidad NUMBER) RETURN Estado_Unidad%ROWTYPE IS
    v_estado_unidad Estado_Unidad%ROWTYPE;
BEGIN
    SELECT * INTO v_estado_unidad FROM Estado_Unidad WHERE id_estado_unidad = p_id_estado_unidad;

    RETURN v_estado_unidad;
END;
/
    
--OBTENER EMPLEADO POR ID
CREATE OR REPLACE FUNCTION obtener_empleado_por_id(p_id_empleado NUMBER) RETURN Empleado%ROWTYPE IS
    v_empleado Empleado%ROWTYPE;
BEGIN
    SELECT * INTO v_empleado FROM Empleado WHERE id_empleado = p_id_empleado;

    RETURN v_empleado;
END;
/
    
--OBTENER EMPRESA POR ID
CREATE OR REPLACE FUNCTION obtener_empresa_por_id(p_id_empresa NUMBER) RETURN Empresa%ROWTYPE IS
    v_empresa Empresa%ROWTYPE;
BEGIN
    SELECT * INTO v_empresa FROM Empresa WHERE id_empresa = p_id_empresa;

    RETURN v_empresa;
END;
/
    
--OBTENER PEDIDO POR ID
CREATE OR REPLACE FUNCTION obtener_pedido_por_id(p_id_pedido NUMBER) RETURN Pedidos%ROWTYPE IS
    v_pedido Pedidos%ROWTYPE;
BEGIN
    SELECT * INTO v_pedido FROM Pedidos WHERE id_pedido = p_id_pedido;

    RETURN v_pedido;
END;
/
    
--ELIMINAR DIRECCION POR ID
CREATE OR REPLACE FUNCTION eliminar_direccion_por_id(p_id_direccion NUMBER) RETURN BOOLEAN IS
BEGIN
    DELETE FROM Direccion WHERE id_direccion = p_id_direccion;

    RETURN TRUE;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/
    
--ACTUALIZAR DIRECCION DE EMPLEADO
CREATE OR REPLACE FUNCTION actualizar_direccion_empleado(p_id_empleado NUMBER, p_id_direccion NUMBER) RETURN BOOLEAN IS
BEGIN
    UPDATE Empleado SET id_direccion = p_id_direccion WHERE id_empleado = p_id_empleado;

    RETURN TRUE;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/
    
--OBTENER PROMEDIO DE CAPACIDAD DE CARGA
CREATE OR REPLACE FUNCTION obtener_promedio_capacidad_carga RETURN NUMBER IS
    v_promedio_capacidad_carga NUMBER;
BEGIN
    SELECT AVG(capacidad_carga) INTO v_promedio_capacidad_carga FROM Unidades;

    RETURN v_promedio_capacidad_carga;
END;
/
    
--ELIMINAR PEDIDO POR ID
CREATE OR REPLACE FUNCTION eliminar_pedido_por_id(p_id_pedido NUMBER) RETURN BOOLEAN IS
BEGIN
    DELETE FROM Pedidos WHERE id_pedido = p_id_pedido;

    RETURN TRUE;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/
    
--OBTENER NOMBRE COMPLETO DE EMPLEADO
CREATE OR REPLACE FUNCTION obtener_nombre_completo_empleado(p_id_empleado NUMBER) RETURN VARCHAR2 IS
    v_nombre_completo VARCHAR2(100);
BEGIN
    SELECT nombre_empleado || ' ' || apellido_empleado INTO v_nombre_completo FROM Empleado WHERE id_empleado = p_id_empleado;

    RETURN v_nombre_completo;
END;
/


--FINALIZA FUNCIONES
--------------------------------------------------------------------------------------------------------------------------------------
--FUNCION CON CURSOR--------------------------------------------------------------------------------------------------------------------------------------

    CREATE OR REPLACE FUNCTION ObtenerInformacionTablas RETURN SYS_REFCURSOR IS
  c_result SYS_REFCURSOR;
BEGIN
  OPEN c_result FOR
    SELECT 'Direccion' AS tabla, id_direccion AS id, referencias AS informacion FROM Direccion
    UNION ALL
    SELECT 'Tipo_Empresa' AS tabla, id_tipo_empresa AS id, tipo_empresa AS informacion FROM Tipo_Empresa
    UNION ALL
    SELECT 'Estado_Unidad' AS tabla, id_estado_unidad AS id, estado_unidad AS informacion FROM Estado_Unidad
    UNION ALL
    SELECT 'Empleado' AS tabla, id_empleado AS id, nombre_empleado || ' ' || apellido_empleado AS informacion FROM Empleado
    UNION ALL
    SELECT 'Empresa' AS tabla, id_empresa AS id, nombre_empresa || ', Identificación: ' || identificacion AS informacion FROM Empresa
    UNION ALL
    SELECT 'Unidades' AS tabla, id_placa AS id, CONCAT('Año: ', unidad_year, ', Capacidad de carga: ', capacidad_carga) AS informacion FROM Unidades
    UNION ALL
    SELECT 'Pedidos' AS tabla, id_pedido AS id, CONCAT('Cliente: ', nombre_empresa_cliente, ', Fecha ingreso: ', fecha_ingreso, ', Fecha límite: ', limite_entrega) AS informacion FROM Pedidos;

  RETURN c_result;
END;
/


    DECLARE
  v_cursor SYS_REFCURSOR;
  v_tabla VARCHAR2(100);
  v_id NUMBER;
  v_informacion VARCHAR2(4000);
BEGIN
  v_cursor := ObtenerInformacionTablas;

  LOOP
    FETCH v_cursor INTO v_tabla, v_id, v_informacion;
    EXIT WHEN v_cursor%NOTFOUND;
    -- Realiza las operaciones necesarias con los datos obtenidos
    -- Aquí puedes imprimir los valores, almacenarlos en variables, etc.
    DBMS_OUTPUT.PUT_LINE('Tabla: ' || v_tabla || ', ID: ' || v_id || ', Información: ' || v_informacion);
  END LOOP;

  CLOSE v_cursor;
END;
/

------------------------------
    --FINALIZA FUNCIONES
------------------------------
    --FINALIZA FUNCIONES
------------------------------
    --FINALIZA FUNCIONES
    



------------OLD---------------OLD------------------OLD-----------------------OLD
select * from EMPLEADO;
SELECT * FROM DIRECCION;
SELECT * FROM EMPRESA;
SELECT * FROM UNIDAD;

CREATE OR REPLACE PROCEDURE agregar_estado_unidad(p_estado_unidad IN VARCHAR2) AS
BEGIN
  INSERT INTO Estado_Unidad (estado_unidad) VALUES (p_estado_unidad);
  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    RAISE;
END;
/

BEGIN
  agregar_estado_unidad('Nuevo Estado');
END;
/

select * from ESTADO_UNIDAD;

