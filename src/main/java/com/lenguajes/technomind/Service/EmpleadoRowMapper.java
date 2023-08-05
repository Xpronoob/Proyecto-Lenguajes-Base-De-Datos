package com.lenguajes.technomind.Service;

import com.lenguajes.technomind.entity.Empleado;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class EmpleadoRowMapper implements RowMapper<Empleado> {

    @Override
    public Empleado mapRow(ResultSet rs, int rowNum) throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(rs.getLong("id_empleado"));
        empleado.setNombreEmpleado(rs.getString("nombre_empleado"));
        empleado.setApellidoEmpleado(rs.getString("apellido_empleado"));
        empleado.setIdentificacionEmpleado(rs.getLong("identificacion_empleado"));
        // No mapeamos el campo id_direccion para evitar el error
        return empleado;
    }
}
