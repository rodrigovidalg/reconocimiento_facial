/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Login;
import Modelo.Empleado;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@WebServlet(name = "sr_cIndex", urlPatterns = {"/sr_cIndex"})
public class sr_cIndex extends HttpServlet {
Empleado empleado = new Empleado();
Login login= new Login();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_cIndex</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sr_cIndex at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String user = request.getParameter("txt_user");
            String pass = request.getParameter("txt_pass");

            int idUsuarioValidado = login.validarUsuario(user, pass);

            if (idUsuarioValidado > 0) {
                // Obtener el nombre de usuario desde la base de datos
                String nombreUsuario = login.obtenerNombreUsuario(idUsuarioValidado);

                if (nombreUsuario != null && !nombreUsuario.isEmpty()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioId", idUsuarioValidado);
                    session.setAttribute("nombreUsuario", nombreUsuario); // Guardar el nombre en la sesión
                    response.sendRedirect("Principal.jsp");
                } else {
                    request.setAttribute("error", "Error al obtener el nombre de usuario.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
     @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.sendRedirect("index.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
