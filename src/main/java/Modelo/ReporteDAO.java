/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */


public class ReporteDAO {
    private int id, id_usuario;
    private String fecha_hora;
    private String zona_acceso;
    private String resultado;
    private String metodo;
    private String imagen;
    private Conexion cn;
    
    public ReporteDAO(){}

    public ReporteDAO(int id, int id_usuario, String fecha_hora, String zona_acceso, String resultado, String metodo, String imagen) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.fecha_hora = fecha_hora;
        this.zona_acceso = zona_acceso;
        this.resultado = resultado;
        this.metodo = metodo;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getZona_acceso() {
        return zona_acceso;
    }

    public void setZona_acceso(String zona_acceso) {
        this.zona_acceso = zona_acceso;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    // Método leer
    public List<Reporte> leer() {
        List<Reporte> listaRegistros = new ArrayList<>();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            // Modificamos la consulta para leer los datos de la tabla registros_acceso
            String query = "SELECT r.id, r.id_usuario, u.nombres, u.apellidos, r.fecha_hora, r.zona_acceso, r.resultado, r.metodo, r.imagen " +
                           "FROM registros_acceso AS r " +
                           "INNER JOIN usuarios AS u ON r.id_usuario = u.id_usuario";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            
            // Recorremos el resultado y llenamos la lista
            while (consulta.next()) {
                int id = consulta.getInt("id");
                int id_usuario = consulta.getInt("id_usuario");
                String nombres = consulta.getString("nombres");
                String apellidos = consulta.getString("apellidos");
                String fecha_hora = consulta.getString("fecha_hora");
                String zona_acceso = consulta.getString("zona_acceso");
                String resultado = consulta.getString("resultado");
                String metodo = consulta.getString("metodo");
                String imagen = consulta.getString("imagen");
                
                // Creamos el objeto Reporte y lo agregamos a la lista
                Reporte registro = new Reporte();
                registro.setId(id);
                registro.setId_usuario(id_usuario);
                registro.setFecha_hora(fecha_hora);
                registro.setZona_acceso(zona_acceso);
                registro.setResultado(resultado);
                registro.setMetodo(metodo);
                registro.setImagen(imagen);
                registro.setNombres_usuario(nombres);
                registro.setApellidos_usuario(apellidos);
                listaRegistros.add(registro);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al leer registros de acceso: " + ex.getMessage());
        }
        return listaRegistros;
    }
}
