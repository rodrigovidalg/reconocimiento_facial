/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ReporteVisitanteDAO {
    Conexion cn;
    
    public List<ReporteVisitante> leer() {
        List<ReporteVisitante> listaRegistros = new ArrayList<>();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            Statement statement = cn.conexionDB.createStatement();
            String query = "SELECT " +
                           "    rav.id, " +
                           "    CONCAT(v.nombres, ' ', v.apellidos) AS nombre_completo, " +
                           "    DATE_FORMAT(rav.fecha_hora, '%Y-%m-%d %H:%i:%s') AS fecha_hora, " +
                           "    d.nombre AS zona_acceso, " +
                           "    rav.resultado, " +
                           "    m.metodo AS metodo, " +
                           "    rav.encoding_facial " +
                           "FROM registros_acceso_visitante AS rav " +
                           "INNER JOIN visitantes AS v ON rav.id_visitante = v.id_visitante " +
                           "INNER JOIN departamentos AS d ON rav.zona_acceso = d.id_departamento " +
                           "INNER JOIN metodos AS m ON rav.id_metodo = m.id_metodo " +
                           "ORDER BY rav.fecha_hora DESC";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ReporteVisitante registro = new ReporteVisitante();
                registro.setId(resultSet.getInt("id"));
                registro.setNombreCompleto(resultSet.getString("nombre_completo"));
                registro.setFechaHora(resultSet.getString("fecha_hora"));
                registro.setZonaAcceso(resultSet.getString("zona_acceso"));
                registro.setResultado(resultSet.getInt("resultado"));
                registro.setMetodo(resultSet.getString("metodo"));
                registro.setImagen(resultSet.getBytes("encoding_facial"));
                listaRegistros.add(registro);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer registros de visitantes: " + ex.getMessage());
        } finally {
            cn.cerrar_conexion();
        }
        return listaRegistros;
    }
}
