/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author user
 */
public class ReporteUsuario {
    private int id;
    private String nombreCompleto;
    private String fechaHora;
    private String zonaAcceso;
    private int resultado;
    private String metodo;
    private byte[] imagen; // Se mantendrá como byte[] por el BLOB en la base

    public ReporteUsuario(){}
    public ReporteUsuario(int id, String nombreCompleto, String fechaHora, String zonaAcceso, int resultado, String metodo, byte[] imagen) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.fechaHora = fechaHora;
        this.zonaAcceso = zonaAcceso;
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getZonaAcceso() {
        return zonaAcceso;
    }

    public void setZonaAcceso(String zonaAcceso) {
        this.zonaAcceso = zonaAcceso;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    
}
