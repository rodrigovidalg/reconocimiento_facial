/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ReporteUsuarioDAO {
    Conexion cn;
    public List<ReporteUsuario> leer() {
        List<ReporteUsuario> listaRegistros = new ArrayList<>();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            Statement statement = cn.conexionDB.createStatement();
            String query = "SELECT  " +
                           "    rau.id, " +
                           "    CONCAT(u.nombres, ' ', u.apellidos) AS nombre_completo, " +
                           "    DATE_FORMAT(rau.fecha_hora, '%Y-%m-%d %H:%i:%s') AS fecha_hora, " +
                           "    d.nombre AS  zona_acceso, " +
                           "    rau.resultado, " +
                           "    m.metodo, " +
                           "    rau.encoding_facial as imagen " +
                           "FROM registros_acceso_usuario AS rau " +
                           "INNER JOIN usuarios AS u ON rau.id_usuario = u.id_usuario " +
                           "INNER JOIN departamentos AS d ON rau.zona_acceso = d.id_departamento " +
                           "INNER JOIN metodos AS m ON rau.id_metodo = m.id_metodo " +
                           "ORDER BY rau.fecha_hora DESC";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ReporteUsuario registro = new ReporteUsuario();
                registro.setId(resultSet.getInt("id"));
                registro.setNombreCompleto(resultSet.getString("nombre_completo"));
                registro.setFechaHora(resultSet.getString("fecha_hora"));
                registro.setZonaAcceso(resultSet.getString("zona_acceso"));
                registro.setResultado(resultSet.getInt("resultado"));
                registro.setMetodo(resultSet.getString("metodo"));
                registro.setImagen(resultSet.getBytes("imagen"));
                listaRegistros.add(registro);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer registros de usuarios: " + ex.getMessage());
        } finally {
            cn.cerrar_conexion();
        }
        return listaRegistros;
    }
}
