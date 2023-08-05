package com.lenguajes.technomind.Service;

import com.lenguajes.technomind.entity.Empleado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class funcionesOracle {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall obtenerEmpleadosSinDireccionCall;

    @Autowired
    public funcionesOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        obtenerEmpleadosSinDireccionCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("obtener_empleados_sin_direccion")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlOutParameter("RESULTADO", Types.REF_CURSOR)
                );
    }

    public List<Empleado> obtenerEmpleadosSinDireccion() {
        List<Empleado> empleados = jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall("{ ? = call obtener_empleados_sin_direccion() }");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            List<Empleado> result = new ArrayList<>();
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getLong("id_empleado"));
                empleado.setNombreEmpleado(rs.getString("nombre_empleado"));
                empleado.setApellidoEmpleado(rs.getString("apellido_empleado"));
                empleado.setIdentificacionEmpleado(rs.getLong("identificacion_empleado"));
                // No mapeamos el campo id_direccion para evitar el error
                result.add(empleado);
            }
            return result;
        });
        return empleados;
    }

}
