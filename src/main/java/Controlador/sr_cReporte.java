/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Reporte;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author DELL
 */
public class sr_cReporte extends HttpServlet {

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
        String accion = request.getParameter("accion");
        if ("ExportarReporteAcceso".equalsIgnoreCase(accion)) {
            exportarReporteAcceso(request, response);
        } else {
            response.setContentType("text/plain");
            response.getWriter().println("Acción no reconocida: " + accion);
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

    private void exportarReporteAcceso(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        try {
            InputStream logoEmpresa = this.getServletConfig()
                    .getServletContext()
                    .getResourceAsStream("Reportes/img/Logo.jpg"),
                    reporteAcceso = this.getServletConfig()
                            .getServletContext()
                            .getResourceAsStream("Reportes/ReporteAcceso.jasper");
            if (logoEmpresa != null && reporteAcceso != null) {
                String jsonReporte = request.getParameter("lista"); //OJO
                Gson gson = new Gson();
                List<Reporte> reportesAcceso = new ArrayList<>();
                List<Reporte> reportesAcceso2 = new ArrayList<>();

                reportesAcceso.add(new Reporte());
                reportesAcceso2 = gson.fromJson(jsonReporte, new TypeToken<List<Reporte>>() {}.getType());
                reportesAcceso.addAll(reportesAcceso2);

                JasperReport report = (JasperReport) JRLoader.loadObject(reporteAcceso);
                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(reportesAcceso.toArray());

                Map<String, Object> parameters = new HashMap();
                parameters.put("ds", ds);
                parameters.put("LogoEmpresa", logoEmpresa);
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "inline; filename=ReportesAccesos.pdf");
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
                out.flush();
                out.close();
            } else {
                response.setContentType("text/plain");
                out.println("no se pudo generar el reporte");
                out.println("esto puede debrse a que la lista de datos no fue recibida o el archivo plantilla del reporte no se ha encontrado");
                out.println("contacte a soporte");
            }
        } catch (Exception e) {
            response.setContentType("text/plain");
            out.print("ocurrió un error al intentar generar el reporte: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
