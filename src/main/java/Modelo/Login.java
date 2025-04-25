/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author DELL
 */
public class Login {
    private int id;
    private String user, pass, rol;
    private Conexion cn;
    private ResultSet rs;

    
    public Login(){}

    public Login(int id, String user, String pass, String rol) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.rol = rol;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public int validarUsuario(String user, String password) {
        int idUsuarioEncontrado = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT id_login FROM login WHERE usuario = ? AND password = ? AND estado = 1;";
            parametro = cn.conexionDB.prepareStatement(query);
            parametro.setString(1, user);
            parametro.setString(2, password);
            ResultSet resultado = parametro.executeQuery();

            if (resultado.next()) {
                idUsuarioEncontrado = resultado.getInt("id_login");
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al validar usuario: " + ex.getMessage());
            idUsuarioEncontrado = 0;
        }
        return idUsuarioEncontrado;
    }

    public String obtenerNombreUsuario(int idUsuario) {
        String nombreUsuario = null;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT usuario FROM login WHERE id_login = ?;";
            parametro = cn.conexionDB.prepareStatement(query);
            parametro.setInt(1, idUsuario);
            ResultSet resultado = parametro.executeQuery();

            if (resultado.next()) {
                nombreUsuario = resultado.getString("usuario");
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error al obtener nombre de usuario: " + ex.getMessage());
        }
        return nombreUsuario;
    }
}
