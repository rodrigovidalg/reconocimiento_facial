<%-- 
    Document   : Departamento
    Created on : 30 mar 2025, 16:57:09
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Departamento"%>
<%@page import="java.util.HashMap"%>
<%@page import="javax.swing.table.DefaultTableModel"%>

<%@page import="Modelo.Empleado"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    
    if (session == null || session.getAttribute("empleado") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    Empleado empleado = (Empleado) session.getAttribute("empleado");
%>
<%
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Departamentos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <!-- Sección Izquierda: Datos del Puesto -->
                <div class="col-md-3">
                    <div class="card text-center shadow-sm border-0 rounded-lg">
                        <div class="card-header bg-dark text-light">
                            <h5><i class="bi bi-briefcase-fill"></i> Control de Departamentos</h5>
                        </div>
                        <div class="card-body">
                            <i class="bi bi-person-workspace" style="font-size: 80px; color: #6c757d; margin-bottom: 20px;"></i>
                            <button type="button" class="btn btn-dark mb-3" data-bs-toggle="modal" data-bs-target="#modal_departamento" onclick="limpiarDepartamento();">
                                <i class="bi bi-plus-circle"></i> Departamento Nuevo
                            </button>
                            <a href="Puesto.jsp" class="btn btn-primary btn-block mt-2"><i class="bi bi-person-lines-fill"></i> Gestionar Puestos</a>
                            <a href="Empleado.jsp" class="btn btn-primary btn-block mt-2"><i class="bi bi-person-lines-fill"></i> Gestionar Empleados</a>
                        </div>
                    </div>
                </div>

                <!-- Sección Derecha: Tabla y búsquedas -->
                <div class="col-md-9">
                    <div class="card shadow-sm border-0 rounded-lg">
                        <div class="card-header bg-dark text-light">
                            <h5><i class="bi bi-list-check"></i> Información de los Departamentos</h5>
                        </div>
                        <div class="card-body">
                            <!-- Buscador de Puestos -->
                            <div class="input-group mb-3">
                                <input type="text" id="searchField" class="form-control" placeholder="Buscar departamento...">
                                <button class="btn btn-outline-primary" type="button" onclick="buscar()">
                                    <i class="bi bi-search"></i> Buscar
                                </button>
                            </div>

                            <!-- Tabla de todos los puestos ingresados -->
                            <div class="table-responsive">
                                <table class="table table-striped table-hover align-middle">
                                    <thead class="table-dark">
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre del Departamento</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbl_departamentos">
                                        <%
                                            Departamento departamento = new Departamento();
                                            DefaultTableModel tabla = departamento.leer(); 
                                            for (int t = 0; t < tabla.getRowCount(); t++) {
                                                out.println("<tr data-id='" + tabla.getValueAt(t, 0) + "'>");
                                                out.println("<td>" + tabla.getValueAt(t, 0) + "</td>");
                                                out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                                                out.println("</tr>");
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal Puesto -->
            <div class="modal fade" id="modal_departamento" tabindex="-1" role="dialog" aria-labelledby="modal_departamentoLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header bg-dark text-light">
                            <h5 class="modal-title"><i class="bi bi-pencil-square"></i> Administrar Departamento</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="sr_cControlador?menu=Departamento" method="post">
                                <div class="mb-3">
                                    <label for="txt_id_departamento" class="form-label">ID</label>
                                    <input type="text" name="txt_id_departamento" id="txt_id_departamento" class="form-control" value="0" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="txt_nombre_departamento" class="form-label">Nombre del Departamento</label>
                                    <input type="text" name="txt_nombre_departamento" id="txt_nombre_departamento" class="form-control" required placeholder="Ingrese el nombre del departamento">
                                </div>
                                <div class="d-flex justify-content-around">
                                    <button type="submit" name="action" value="agregarD" class="btn btn-primary"><i class="bi bi-plus-circle"></i> Agregar</button>
                                    <button type="submit" name="action" value="actualizarD" class="btn btn-success"><i class="bi bi-pencil"></i> Actualizar</button>
                                    <button type="submit" name="action" value="eliminarD" class="btn btn-danger" onclick="return confirm('¿Desea eliminar este puesto?')"><i class="bi bi-trash"></i> Eliminar</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="bi bi-x-circle"></i> Cerrar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
        <script>
            function limpiarDepartamento() {
                $("#txt_nombre_departamento").val('');
                $("#txt_id_departamento").val('0'); // Reseteamos a 0 al limpiar
                $("#modal_departamento").val('');
            }

            $('#tbl_departamentos').on('click', 'tr', function () {
                // Capturamos el ID de la fila que se ha clicado
                var id = $(this).data('id'); // Obtener el ID de la fila
                var nombre_departamento = $(this).find("td").eq(1).text(); // Obtener el nombre del puesto de la segunda celda

                // Asignamos los valores a los inputs del modal
                $("#txt_id_departamento").val(id);
                $("#txt_nombre_departamento").val(nombre_departamento);

                // Abrimos el modal
                var modal = new bootstrap.Modal(document.getElementById("modal_departamento"));
                modal.show();
            });
        </script>
        
        <script>
            function buscar() {
                const searchTerm = document.getElementById('searchField').value.toLowerCase();
                let found = false;

                // Filtrar puestos
                $('#tbl_departamentos tr').filter(function() {
                    const isMatch = $(this).text().toLowerCase().indexOf(searchTerm) > -1;
                    $(this).toggle(isMatch);
                    if (isMatch) found = true; // Actualiza found si hay coincidencias
                });

                // Mostrar alerta si no se encontraron resultados
                if (!found) {
                    alert("No se encontraron resultados.");
                }
            }
        </script>
    </body>
</html>