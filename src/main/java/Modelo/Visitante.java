/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author DELL
 */

public class Visitante {
    private int id, dpi;
    private String nombres, apellidos, motivo_visita, fecha_visita, hora_entrada, hora_salida, imagen, autorizado, estado;
    private boolean genero;
    Conexion cn;
    
    public Visitante() {}
    
    public Visitante(int id, String nombres, String apellidos, int dpi, boolean genero, String motivo_visita, String fecha_visita, String hora_entrada, String hora_salida, String imagen, String autorizado, String estado) {
        this.id = id;
        this.dpi = dpi;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.motivo_visita = motivo_visita;
        this.fecha_visita = fecha_visita;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.imagen = imagen;
        this.autorizado = autorizado;
        this.estado = estado;
        this.genero = genero;
    }

    // Getters y Setters...
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMotivo_visita() {
        return motivo_visita;
    }

    public void setMotivo_visita(String motivo_visita) {
        this.motivo_visita = motivo_visita;
    }

    public String getFecha_visita() {
        return fecha_visita;
    }

    public void setFecha_visita(String fecha_visita) {
        this.fecha_visita = fecha_visita;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }
    
    public int agregar() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO visitantes(nombres, apellidos, dpi, genero, motivo_visita, fecha_visita, hora_entrada, hora_salida, imagen, autorizado, estado) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            parametro.setString(1, nombres);
            parametro.setString(2, apellidos);
            parametro.setInt(3, dpi);
            parametro.setBoolean(4, genero);
            parametro.setString(5, motivo_visita);
            parametro.setString(6, fecha_visita);
            parametro.setString(7, hora_entrada);
            parametro.setString(8, hora_salida);
            parametro.setString(9, imagen);
            parametro.setString(10, autorizado);
            parametro.setString(11, estado);
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en agregar: " + ex.getMessage());
        }
        return retorno;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT * FROM visitantes;";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            String encabezado[] = {"ID", "Nombres", "Apellidos", "DPI", "Género", "Motivo Visita", "Fecha Visita", "Hora Entrada", "Hora Salida", "Imagen", "Autorizado", "Estado"};
            tabla.setColumnIdentifiers(encabezado);
            while (consulta.next()) {
                String datos[] = new String[12];
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("dpi");
                datos[4] = consulta.getBoolean("genero") ? "M" : "F";
                datos[5] = consulta.getString("motivo_visita");
                datos[6] = consulta.getString("fecha_visita");
                datos[7] = consulta.getString("hora_entrada");
                datos[8] = consulta.getString("hora_salida");
                datos[9] = consulta.getString("imagen");
                datos[10] = consulta.getString("autorizado");
                datos[11] = consulta.getString("estado");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en leer: " + ex.getMessage());
        }
        return tabla;
    }

    public int actualizar() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "UPDATE visitantes SET nombres=?, apellidos=?, dpi=?, genero=?, motivo_visita=?, fecha_visita=?, hora_entrada=?, hora_salida=?, imagen=?, autorizado=?, estado=? WHERE id=?;";
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            parametro.setString(1, nombres);
            parametro.setString(2, apellidos);
            parametro.setInt(3, dpi);
            parametro.setBoolean(4, genero);
            parametro.setString(5, motivo_visita);
            parametro.setString(6, fecha_visita);
            parametro.setString(7, hora_entrada);
            parametro.setString(8, hora_salida);
            parametro.setString(9, imagen);
            parametro.setString(10, autorizado);
            parametro.setString(11, estado);
            parametro.setInt(12, id);
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en actualizar: " + ex.getMessage());
        }
        return retorno;
    }

    public int eliminar() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "DELETE FROM visitantes WHERE id=?;";
            PreparedStatement parametro = cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, id);
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en eliminar: " + ex.getMessage());
        }
        return retorno;
    }
    
    public HashMap<String, String[]> drop_Visitantes() {
        HashMap<String, String[]> drop = new HashMap<>();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT id, nombres, apellidos FROM visitantes;";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            while (consulta.next()) {
                String id = consulta.getString("id");
                String nombre = consulta.getString("nombres");
                String apellido = consulta.getString("apellidos");
                drop.put(id, new String[]{nombre, apellido});
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en drop_Visitantes: " + ex.getMessage());
        }
        return drop;
    }
}