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
--------------------------------------------------------------------
----CSP ADD Direccion
CREATE SEQUENCE secuencia_direccion;
CREATE OR REPLACE PROCEDURE agregar_direccion(
    p_referencias IN direccion.referencias%TYPE
) AS
    v_id_direccion direccion.id_direccion%TYPE;
BEGIN
    -- Generar el nuevo ID_DIRECCION autom�ticamente
    SELECT secuencia_direccion.NEXTVAL INTO v_id_direccion FROM dual;

    -- Insertar la nueva direcci�n en la tabla
    INSERT INTO direccion (id_direccion, referencias)
    VALUES (v_id_direccion, p_referencias);

    COMMIT;
END;
/
--INPUT/OUTPUT
DECLARE
    p_referencias direccion.referencias%TYPE;
BEGIN
    -- Asignar el valor de referencias
    p_referencias := 'Ejemplo Direcccion';

    -- Ejecutar el procedimiento almacenado
    agregar_direccion(p_referencias);
    
    -- Confirmar los cambios
    COMMIT;
    
    -- Mostrar mensaje de �xito
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
