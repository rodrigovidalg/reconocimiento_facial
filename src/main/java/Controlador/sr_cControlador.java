/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Modelo.Empleado;
import Modelo.Puesto;
import Modelo.Departamento;
import jakarta.servlet.annotation.WebServlet;

/**
 *
 * @author DELL
 */
@WebServlet(name = "sr_cControlador", urlPatterns = {"/sr_cControlador"})
public class sr_cControlador extends HttpServlet {

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
        String menu = request.getParameter("menu");
        String action = request.getParameter("action");

        switch (menu) {
            case "Principal":
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
                break;

            /*case "Usuario":
                // Obtener los parámetros del formulario
                String idStr = request.getParameter("txt_id");
                int id = 0; // Valor por defecto
                if (idStr != null && !idStr.isEmpty()) {
                    try {
                        id = Integer.parseInt(idStr);
                    } catch (NumberFormatException e) {
                        response.sendRedirect("Empleado.jsp"); // Redirigir en caso de error
                        return;
                    }
                }

                String nombres = request.getParameter("txt_nombres");
                String apellidos = request.getParameter("txt_apellidos");
                String direccion = request.getParameter("txt_direccion");
                String telefono = request.getParameter("txt_telefono");
                String dpi = request.getParameter("txt_dpi");
                String generoStr = request.getParameter("txt_genero");
                boolean genero = "M".equalsIgnoreCase(generoStr); // Suponiendo 'M' para masculino y cualquier otra cosa como femenino.
                String fechaNacimiento = request.getParameter("txt_fn");

                // Validar y parsear el puesto seleccionado
                String puestoStr = request.getParameter("drop_puesto");
                int puestos = 0; // Valor por defecto
                if (puestoStr != null && !puestoStr.isEmpty()) {
                    try {
                        puestos = Integer.parseInt(puestoStr);
                    } catch (NumberFormatException e) {
                        response.sendRedirect("Empleado.jsp");
                        return;
                    }
                }

                String fechaInicioLabores = request.getParameter("txt_fl");
                String fechaIngreso = request.getParameter("txt_fi");

                // Validar que los campos requeridos no estén vacíos
                if (nombres == null || nombres.isEmpty() || 
                    apellidos == null || apellidos.isEmpty() ||
                    direccion == null || direccion.isEmpty() || 
                    telefono == null || telefono.isEmpty() ||
                    dpi == null || dpi.isEmpty() || 
                    fechaNacimiento == null || fechaNacimiento.isEmpty() ||
                    puestos == 0 || 
                    fechaInicioLabores == null || fechaInicioLabores.isEmpty() || 
                    fechaIngreso == null || fechaIngreso.isEmpty()) {

                    request.setAttribute("errorMessage", "Todos los campos son obligatorios."); // Mensaje de error
                    request.getRequestDispatcher("Empleado.jsp").forward(request, response); // Mantener la vista
                    return;
                }

                // Crear instancia de Empleado con los datos del formulario
                Empleado usuario = new Empleado(id, nombres, apellidos, direccion, telefono, dpi, genero, fechaNacimiento, puestos, fechaInicioLabores, fechaIngreso);

                // Validar la acción (agregar, actualizar, eliminar)
                switch (action) { 
                    case "agregarE":
                        if (usuario.agregar() > 0) {
                            response.sendRedirect("Empleado.jsp");
                        } else {
                            request.setAttribute("errorMessage", "Error al agregar empleado.");
                            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                        }
                        break;

                    case "actualizarE":
                        if (usuario.actualizar() > 0) {
                            response.sendRedirect("Empleado.jsp");
                        } else {
                            request.setAttribute("errorMessage", "Error al actualizar empleado.");
                            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                        }
                        break;

                    case "eliminarE":
                        if (usuario.eliminar() > 0) {
                            response.sendRedirect("Empleado.jsp");
                        } else {
                            request.setAttribute("errorMessage", "Error al eliminar empleado.");
                            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                        }
                        break;

                    default:
                        request.setAttribute("errorMessage", "Acción no válida.");
                        request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                        break;
                }
                break;*/


            case "Puesto":
                // Obtener los parámetros del formulario
                String idStrP = request.getParameter("txt_id_puesto");
                int idPuesto = 0; // Valor por defecto
                if (idStrP != null && !idStrP.isEmpty()) {
                    try {
                        idPuesto = Integer.parseInt(idStrP);
                    } catch (NumberFormatException e) {
                        response.sendRedirect("Puesto.jsp"); // Redirigir en caso de error
                        return;
                    }
                }

                String nombrePuesto = request.getParameter("txt_nombre_puesto");

                // Validar que el nombre del puesto no esté vacío
                if (nombrePuesto == null || nombrePuesto.isEmpty()) {
                    request.getRequestDispatcher("Puesto.jsp").forward(request, response); // Mantener la vista
                    return;
                }

                // Crear instancia de Puesto
                Puesto puesto = new Puesto(idPuesto, nombrePuesto); // Asegúrate de que el constructor de Puesto acepte estos parámetros

                // Validar la acción (agregar, actualizar, eliminar)
                switch (action) {
                    case "agregarP":
                        if (puesto.agregar() > 0) {
                            response.sendRedirect("Puesto.jsp");
                        } else {
                            response.getWriter().println("<h1>Error al agregar puesto</h1>");
                        }
                        break;

                    case "actualizarP":
                        if (puesto.actualizar() > 0) {
                            response.sendRedirect("Puesto.jsp");
                        } else {
                            response.getWriter().println("<h1>Error al actualizar puesto</h1>");
                        }
                        break;

                    case "eliminarP":
                        if (puesto.eliminar() > 0) {
                            response.sendRedirect("Puesto.jsp");
                        } else {
                            response.getWriter().println("<h1>Error al eliminar puesto</h1>");
                        }
                        break;

                    default:
                        response.sendRedirect("Puesto.jsp");
                        break;
                }
                break;
                
            case "Departamento":
                // Obtener los parámetros del formulario
                String idStrD = request.getParameter("txt_id_departamento");
                int idDepartamento = 0; // Valor por defecto
                if (idStrD != null && !idStrD.isEmpty()) {
                    try {
                        idDepartamento = Integer.parseInt(idStrD);
                    } catch (NumberFormatException e) {
                        response.sendRedirect("Departamento.jsp"); // Redirigir en caso de error
                        return;
                    }
                }

                String nombreDepartamento = request.getParameter("txt_nombre_departamento");

                // Validar que el nombre del Departamento no esté vacío
                if (nombreDepartamento == null || nombreDepartamento.isEmpty()) {
                    request.getRequestDispatcher("Departamento.jsp").forward(request, response); // Mantener la vista
                    return;
                }

                // Crear instancia de Departamento
                Departamento departamento = new Departamento(idDepartamento, nombreDepartamento); // Asegúrate de que el constructor de Departamento acepte estos parámetros

                // Validar la acción (agregar, actualizar, eliminar)
                switch (action) {
                    case "agregarD":
                        if (departamento.agregar() > 0) {
                            response.sendRedirect("Departamento.jsp");
                        } else {
                            response.getWriter().println("<h1>Error al agregar departamento</h1>");
                        }
                        break;

                    case "actualizarD":
                        if (departamento.actualizar() > 0) {
                            response.sendRedirect("Departamento.jsp");
                        } else {
                            response.getWriter().println("<h1>Error al actualizar departamento</h1>");
                        }
                        break;

                    case "eliminarD":
                        if (departamento.eliminar() > 0) {
                            response.sendRedirect("Departamento.jsp");
                        } else {
                            response.getWriter().println("<h1>Error al eliminar departamento</h1>");
                        }
                        break;

                    default:
                        response.sendRedirect("Departamento.jsp");
                        break;
                }
                break;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
