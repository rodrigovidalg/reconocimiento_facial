/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author DELL
 */

public class Empleado{
    private int id, id_puesto, id_departamento;
    private String nombres, apellidos, dpi, encoding_facial, imagen, estado, usuario, rol;
    private boolean genero;
    private Conexion cn;

    // Constructor por defecto
    public Empleado() {}

    // Constructor sobrecargado
    public Empleado(int id, String nombres, String apellidos, String dpi, boolean genero, int id_departamento, int id_puesto, String encoding_facial, String imagen, String estado, String usuario,String rol) {
        this.id = id;
        this.id_puesto = id_puesto;
        this.id_departamento = id_departamento;
        this.dpi = dpi;
        this.genero = genero;
        this.encoding_facial = encoding_facial;
        this.imagen = imagen;
        this.estado = estado;
        this.usuario = usuario;
        this.rol = rol;
    }

    // Getters y setters}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public String getDpi() {
        return dpi;
    }
    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public boolean getGenero() {
        return genero;
    }
    public void setGenero(boolean genero) {
        this.genero = genero;
    }
    
    public int getId_departamento() {
        return id_departamento;
    }
    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }
    
    public int getId_puesto() {
        return id_puesto;
    }
    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getEncoding_facial() {
        return encoding_facial;
    }
    public void setEncoding_facial(String encoding_facial) {
        this.encoding_facial = encoding_facial;
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Método agregar

    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO usuarios(nombres, apellidos, dpi, genero, id_departamento, id_puesto, encoding_facial, imagen, estado) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDpi());
            parametro.setBoolean(4, getGenero());
            parametro.setInt(5, getId_departamento());
            parametro.setInt(6, getId_puesto());
            parametro.setString(7, getEncoding_facial());
            parametro.setString(8, getImagen());
            parametro.setString(9, getEstado());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    // Método leer
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT u.id_usuario as id, u.nombres, u.apellidos, u.dpi, u.genero, u.estado, u.encoding_facial, u.imagen, p.puesto, d.nombre " +
                           "FROM usuarios as u INNER JOIN puestos as p ON u.id_puesto = p.id_puesto " +
                           "INNER JOIN departamentos as d ON u.id_departamento = d.id_departamento;";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            String encabezado[] = {"id", "nombres", "apellidos", "dpi", "genero", "departamento", "puesto", "encoding_facial", "imagen", "estado"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[10];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_usuario");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("dpi");
                datos[4] = consulta.getString("genero");
                datos[5] = consulta.getString("id_departamento");
                datos[6] = consulta.getString("id_puesto");
                datos[7] = consulta.getString("encoding_facial");
                datos[8] = consulta.getString("imagen");
                datos[9] = consulta.getString("estado");
                tabla.addRow(datos);
            }
            System.out.println("Filas en la tabla: " + tabla.getRowCount());
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al leer empleados: " + ex.getMessage());
        }
        return tabla;
    }

    // Método actualizar
    public int actualizar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE usuarios SET nombres=?, apellidos=?, dpi=?, genero=?, id_departamento=?, id_puesto=?, encoding_facial=?, imagen=?, estado=? WHERE id_empleado = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDpi());
            parametro.setBoolean(4, getGenero());
            parametro.setInt(5, getId_departamento());
            parametro.setInt(6, getId_puesto());
            parametro.setString(7, getEncoding_facial());
            parametro.setString(8, getImagen());
            parametro.setString(9, getEstado());
            parametro.setInt(10, getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar empleado: " + ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    // Método eliminar
    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE FROM usuarios WHERE id_empleado = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    // Método drop_empleado
    public HashMap drop_empleado() {
        HashMap<String, String> drop = new HashMap<>();
        try {
            cn = new Conexion();
            String query = "SELECT id_empleado as id, nombres FROM usuarios;";
            cn.abrir_conexion();
            
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            while (consulta.next()) {
                drop.put(consulta.getString("id"), consulta.getString("nombres"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return drop;
    }
}
