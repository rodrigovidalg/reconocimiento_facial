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
public class Departamento {
     private int id_departamento; 
    private String nombre_departamento; 
    private Conexion cn; 

    public Departamento() {}

    public Departamento(int id_departamento, String nombre_departamento) {
        this.id_departamento = id_departamento;
        this.nombre_departamento = nombre_departamento;
    }
    
    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }
    
    public HashMap drop_departamento(){
        HashMap<String, String> drop = new HashMap();
        try{
            cn = new Conexion();
            String query = "SELECT id_departamento as id, nombre FROM departamentos;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("nombre"));
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }

    public int agregar() {
        int retorno =0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO departamentos (nombre) VALUES (?);";
            parametro = cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getNombre_departamento());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error al agregar departamento: " + e.getMessage());
            retorno =0;
        }
        return retorno;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT * FROM departamentos;";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            String[] encabezado = {"ID Departamento", "Nombre del Departamento"};
            tabla.setColumnIdentifiers(encabezado);
            String[] datos = new String[2];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_departamento");
                datos[1] = consulta.getString("nombre");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al leer departamento: " + ex.getMessage());
        }
        return tabla;
    }

    public int actualizar() {
        int retorno =0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "UPDATE departamentos SET nombre = ? WHERE id_departamento = ?;";
            parametro = cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getNombre_departamento());
            parametro.setInt(2, getId_departamento());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error al actualizar departamento: " + e.getMessage());
            retorno =0;
        }
        return retorno;
    }

    // Método para eliminar un puesto
    public int eliminar() {
        int retorno =0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "DELETE FROM departamentos WHERE id_departamento = ?;";
            parametro = cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, getId_departamento());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error al eliminar puesto: " + e.getMessage());
            retorno =0;
        }
        return retorno;
    }
}