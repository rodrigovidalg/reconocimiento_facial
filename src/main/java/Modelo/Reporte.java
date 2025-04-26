/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author DELL
 */
public class Reporte {
   private int id;
    private int id_usuario;
    private String nombre_completo_usuario; // Nuevo campo para el nombre completo
    private String fecha_hora;
    private String zona_acceso;
    private String resultado;
    private String metodo;
    private String imagen;

    // Constructor vacío
    public Reporte() {}

    public Reporte(int id, int id_usuario, String nombre_completo_usuario, String fecha_hora, String zona_acceso, String resultado, String metodo, String imagen) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.nombre_completo_usuario = nombre_completo_usuario;
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

    public String getNombre_completo_usuario() {
        return nombre_completo_usuario;
    }

    public void setNombre_completo_usuario(String nombre_completo_usuario) {
        this.nombre_completo_usuario = nombre_completo_usuario;
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
    
}