<%-- 
    Document   : ReporteUsuario
    Created on : 2/05/2025, 7:05:56 p. m.
    Author     : user
--%>

<%@page import="Modelo.ReporteVisitante"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.ReporteVisitanteDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
      
    </head>
    <body>
    <div class="container mt-5">
        <div class="text-center mb-4">
            <h2>Reporte de Actividades de Visitantes</h2>
            <p class="lead">Visualiza los registros de acceso de los visitantes.</p>
        </div>

        <div class="table-container">
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                    <thead class="table-primary">
                        <tr>
                            <th>Nombre Completo</th>
                            <th>Fecha y Hora</th>
                            <th>Zona de Acceso</th>
                            <th>Resultado</th>
                            <th>Método</th>
                            <th>Reporte</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        ReporteVisitanteDAO reporteVisitanteDAO = new ReporteVisitanteDAO();
                        List<ReporteVisitante> listaRegistros = reporteVisitanteDAO.leer();

                        for (ReporteVisitante registro : listaRegistros) {
                        %>
                        <tr>
                            <td><%= registro.getNombreCompleto()%></td>
                            <td><%= registro.getFechaHora()%></td>
                            <td><%= registro.getZonaAcceso()%></td>
                            <td><%= registro.getResultado() == 1 ? "Exitoso" : (registro.getResultado() == 0 ? "Fallido" : "Desconocido") %></td>
                            <td><%= registro.getMetodo()%></td>
                            <td>
    <a href="http://localhost:8081/db_biometria/reporteIndividualV?id=<%= registro.getId()%>" target="_blank" class="btn btn-sm btn-primary">
        Ver Detalle
    </a>
</td>
                        </tr>
                        <%
                        }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="text-center mt-3">
    <a href="http://localhost:8081/db_biometria/ReporteVisitantes" target="_blank" class="btn btn-sn btn-outline-primary">
        <i class="fas fa-file-pdf"></i> Exportar PDF
    </a>
</div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
