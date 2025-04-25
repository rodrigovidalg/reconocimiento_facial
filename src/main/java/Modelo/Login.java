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
    
    public Empleado validar(String usuario, String password) {
        Empleado empleado = null;
        String sql = "SELECT * FROM login WHERE usuario = ? AND password = ?";
        
        try {
            cn = new Conexion(); // Inicializa la conexión
            cn.abrir_conexion();
            PreparedStatement pst = cn.conexionDB.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                empleado = new Empleado();
                empleado.setId(rs.getInt("id_login"));
                empleado.setNombres(rs.getString("usuario"));
                empleado.setRol(rs.getString("rol"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en la validación: " + ex.getMessage());
        }
        return empleado; // Retorna null si no se encuentra el usuario
    }
}
