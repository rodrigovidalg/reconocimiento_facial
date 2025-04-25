<%-- 
    Document   : Reporte
    Created on : 6 abr 2025, 20:48:40
    Author     : DELL
--%>
<%@page import="java.util.List"%>
<%@page import="Modelo.ReporteDAO"%>
<%@page import="Modelo.Reporte"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulario Reporte</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
                font-family: Arial, sans-serif;
            }
            .container {
                margin-top: 50px;
            }
            .form-container {
                background-color: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            }
            .table th, .table td {
                text-align: center;
                vertical-align: middle;
            }
            .btn-custom {
                background-color: #007bff;
                color: white;
                border-radius: 5px;
            }
            .btn-custom:hover {
                background-color: #0056b3;
            }
            .table-container {
                margin-top: 30px;
            }
        </style>
    </head>
    <body>

        <!-- Título del reporte -->
        <div class="text-center mb-4">
            <h2>Reporte de Actividades</h2>
            <p class="lead">Visualiza los reportes generados por el sistema.</p>
        </div>

        <!-- Tabla de reportes -->
        <div class="table-container">
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                    <thead class="table-primary">
                        <tr>
                            <th>ID</th>
                            <th>ID Usuario</th>
                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>Fecha y Hora</th>
                            <th>Zona de Acceso</th>
                            <th>Resultado</th>
                            <th>Metodo</th>
                            <th>Foto</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Crear objeto Reporte y obtener los registros
                            ReporteDAO reporte = new ReporteDAO();
                            List<Reporte> listaRegistros = reporte.leer();

                            // Iterar sobre la lista de registros
                            for (Reporte registro : listaRegistros) {
                        %>
                        <tr>
                            <td><%= registro.getId()%></td>
                            <td><%= registro.getId_usuario()%></td>
                            <td><%= registro.getNombres_usuario()%></td>
                            <td><%= registro.getApellidos_usuario()%></td>
                            <td><%= registro.getFecha_hora()%></td>
                            <td><%= registro.getZona_acceso()%></td>
                            <td><%= registro.getResultado()%></td>
                            <td><%= registro.getMetodo()%></td>
                            <td><img src="<%= registro.getImagen()%>" alt="Foto" class="img-fluid" width="50"></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Botones para exportar -->
        <a href="http://localhost:8080/db_biometria/report" target="_blank" class="btn btn-sn btn-outline-primary">
            <i class="fas fa-file-pdf"></i> Exportar PDF
        </a>



        <!-- Bootstrap JS, Popper.js, y jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

        <script>
            function abrirReportePDF() {
                // Abrimos el reporte en una nueva ventana
                window.open('/db_biometria/report', '_blank');
            }
        </script>

    </body>
</html>