<%-- 
    Document   : Empleado
    Created on : 30 mar 2025, 15:10:50
    Author     : DELL
--%>

<%@page import="Modelo.Puesto"%>
<%@page import="Modelo.Empleado"%>
<%@page import="Modelo.Departamento"%>
<%@page import="Modelo.Visitante"%>
<%@page import="java.util.HashMap"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <title>Página Visitantes</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body>
        <div class="container mt-4" style="max-width: 1200px;">
            <!-- Panel de Control -->
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
                    <h3 class="mb-0"><i class="bi bi-people-fill me-2"></i> Panel de Control de Visitantes</h3>
                    <button type="button" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#modal_visitante" onclick="limpiarVisitante();">
                        <i class="bi bi-person-plus-fill"></i> Visitante Nuevo
                    </button>
                </div>
                <div class="card-body">
                    <!-- Buscador de empleados y botón de acceso a Visitantes -->
                    <div class="d-flex align-items-center mb-4">
                        <a href="Puesto.jsp" class="btn btn-secondary me-3"><i class="bi bi-briefcase-fill"></i> Puestos</a>
                        <a href="Departamento.jsp" class="btn btn-secondary me-3"><i class="bi bi-briefcase-fill"></i> Departamentos</a>
                        <a href="Empleado.jsp" class="btn btn-secondary me-3"><i class="bi bi-briefcase-fill"></i> Nuevo Empleado</a>
                        <div class="input-group ms-auto" style="width: 300px;">
                            <span class="input-group-text"><i class="bi bi-search"></i></span>
                            <input type="text" id="searchField" class="form-control" placeholder="Buscar visitante..." aria-label="Buscar Visitante">
                            <button class="btn btn-primary" onclick="buscar()"><i class="bi bi-search"></i></button>
                        </div>
                    </div>
                    
                    <!-- Tabla de Visitantes -->
                    <div class="table-responsive">
                        <table class="table table-striped table-hover custom-table">
                            <thead class="table-primary">
                                <tr class="text-center">
                                    <th>ID</th><th>Nombres</th><th>Apellidos</th><th>DPI</th><th>Género</th>
                                    <th>Motivo de Visita</th><th>Fecha Visita</th><th>Hora Entrada</th><th>Hora Salida</th><th>Foto</th>
                                    <th>Autorizado</th><th>Estado</th>
                                </tr>
                            </thead>
                            <tbody id="tbl_visitante" style="font-size: 13px;">
                                <%
                                    DefaultTableModel tabla = new DefaultTableModel();
                                    tabla = empleado.leer();
                                    for (int t = 0; t < tabla.getRowCount(); t++) {
                                        out.println("<tr>");
                                        out.println("<td>" + tabla.getValueAt(t, 0) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 3) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 8) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 9) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 10) + "</td>");
                                        out.println("<td>" + tabla.getValueAt(t, 11) + "</td>");
                                        out.println("</tr>");
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!-- Modal de visitante -->
            <div class="modal fade" id="modal_visitante" tabindex="-1" aria-labelledby="modal_visitanteLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header bg-primary text-white">
                            <h5 class="modal-title" id="modal_visitanteLabel"><i class="bi bi-person-badge-fill"></i> Gestión de Visitantes</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="sr_cVisitante?menu=Visitante" method="post" class="form-group" enctype="multipart/form-data">
                                <input type="hidden" name="txt_id" id="txt_id" value="0">

                                <!-- Campos del Formulario -->
                                <div class="mb-3">
                                    <label for="txt_nombres" class="form-label">Nombres</label>
                                    <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Ej. Juan Pedro" required>
                                </div>
                                <div class="mb-3">
                                    <label for="txt_apellidos" class="form-label">Apellidos</label>
                                    <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Ej. González Rojas" required>
                                </div>
                                <div class="mb-3">
                                    <label for="txt_dpi" class="form-label">DPI</label>
                                    <input type="number" name="txt_dpi" id="txt_dpi" class="form-control" placeholder="Ej. 3027405800101" required>
                                </div>
                                <div class="mb-3">
                                    <label for="txt_genero" class="form-label">Género</label>
                                    <select name="txt_genero" id="txt_genero" class="form-select" required>
                                        <option value="" selected>Seleccione género</option>
                                        <option value="M">Masculino</option>
                                        <option value="F">Femenino</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="drop_departamento" class="form-label">Departamento</label>
                                    <select name="drop_departamento" id="drop_departamento" class="form-select" required>
                                        <%
                                            Departamento departamento = new Departamento();
                                            HashMap<String, String> dropD = departamento.drop_departamento();
                                            for (String i : dropD.keySet()) {
                                                out.write("<option value='" + i + "'>" + dropD.get(i) + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="drop_puesto" class="form-label">Puesto</label>
                                    <select name="drop_puesto" id="drop_puesto" class="form-select" required>
                                        <%
                                            Puesto puesto = new Puesto();
                                            HashMap<String, String> drop = puesto.drop_puesto();
                                            for (String i : drop.keySet()) {
                                                out.write("<option value='" + i + "'>" + drop.get(i) + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="imagen" class="form-label">Foto</label>
                                    <input type="file" class="form-control" id="imagen" name="imagen" accept="image/*" onchange="previewImage(event)">
                                    <img id="previewImagen" src="" alt="Previsualización de la Imagen" class="img-thumbnail mt-2" style="display: none;">
                                </div>

                                <!-- Botón para abrir el modal de captura de foto -->
                                <div class="mb-3">
                                    <label for="fotoEmpleado" class="form-label">Captura de Foto</label>
                                    <button type="button" class="btn btn-info w-100" data-bs-toggle="modal" data-bs-target="#capturaModal">
                                        <i class="bi bi-camera-fill"></i> Capturar Foto
                                    </button>
                                    <!-- Imagen de vista previa -->
                                    <img id="previewImagen" src="" alt="Previsualización de la Imagen" class="img-thumbnail mt-2" style="display: none;">
                                </div>

                                <div class="mb-3">
                                    <label for="txt_encoding_facial" class="form-label">Encoding Facial</label>
                                    <input type="text" name="txt_encoding_facial" id="txt_encoding_facial" class="form-control" placeholder="Ingrese el encoding facial" required>
                                </div>

                                <div class="mb-3">
                                    <label for="txt_estado" class="form-label">Estado</label>
                                    <select name="txt_estado" id="txt_estado" class="form-select" required>
                                        <option value="Activo" selected>Activo</option>
                                        <option value="Inactivo">Inactivo</option>
                                    </select>
                                </div>

                                <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                                    <button type="submit" name="action" value="agregarE" class="btn btn-primary w-100 mb-2"><i class="bi bi-save-fill"></i> Guardar</button>
                                    <button type="submit" name="action" value="actualizarE" class="btn btn-success w-100 mb-2"><i class="bi bi-arrow-repeat"></i> Actualizar</button>
                                    <button type="submit" name="action" value="eliminarE" class="btn btn-danger w-100 mb-2" onclick="return confirm('¿Desea eliminar este empleado?');"><i class="bi bi-trash-fill"></i> Eliminar</button>
                                    <button type="button" class="btn btn-secondary w-100" data-bs-dismiss="modal"><i class="bi bi-x-lg"></i> Cerrar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal para capturar imagen -->
            <div class="modal fade" id="capturaModal" tabindex="-1" aria-labelledby="capturaModalLabel" aria-hidden="true">
              <div class="modal-dialog modal-sm"> <!-- Aquí se agrega la clase modal-sm -->
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="capturaModalLabel">Captura una Foto</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <!-- Video de la cámara -->
                    <video id="video" width="100%" height="auto" autoplay></video>
                    <canvas id="canvas" style="display: none;"></canvas>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="button" class="btn btn-primary" id="capturarBtn">Capturar Foto</button>
                  </div>
                </div>
              </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
        <script>
                function limpiarEmpleado() {
                    $("#txt_id").val(0);
                    $("#txt_nombres").val('');
                    $("#txt_apellidos").val('');
                    $("#txt_dpi").val('');
                    $("#txt_genero").val('');
                    $("#drop_departamento").val('');
                    $("#drop_puesto").val('');
                    $("#encoding_facial").val('');
                    $("#imagen").val('');
                    $("#estado").val('');
                }


               // Evento para manejar clics en las filas de la tabla
            $('#tbl_empleados').on('click', 'tr', function () {
                // Obtener los datos de la fila seleccionada
                var target, id, nombres, apellidos, dpi, genero, departamento, puesto, encoding_facial, imagen, estado;
                id = $(this).find('td:eq(0)').text();
                nombres = $(this).find('td:eq(1)').text();
                apellidos = $(this).find('td:eq(2)').text();
                dpi = $(this).find('td:eq(3)').text();
                genero = $(this).find('td:eq(4)').text();
                departamento = $(this).find('td:eq(5)').text();
                puesto = $(this).find('td:eq(6)').text();
                encoding_facial = $(this).find('td:eq(7)').text();
                imagen = $(this).find('td:eq(8) img').attr('src'); // Obtener el atributo src de la imagen
                estado = $(this).find('td:eq(9)').text();

                // Asignar los datos a los campos del formulario del modal
                $('#txt_id').val(id);
                $('#txt_nombres').val(nombres);
                $('#txt_apellidos').val(apellidos);
                $('#txt_dpi').val(dpi);
                $('#txt_genero').val(genero);
                $('#drop_departamento option').each(function() {
                    if ($(this).text() === puesto) { // Comparar el nombre del departamento
                        $('#drop_deprtamento').val($(this).val()); // Asignar el valor del ID correspondiente
                        return false; // Salir del bucle
                    }
                });
                $('#drop_puesto option').each(function() {
                    if ($(this).text() === puesto) { // Comparar el nombre del puesto
                        $('#drop_puesto').val($(this).val()); // Asignar el valor del ID correspondiente
                        return false; // Salir del bucle
                    }
                });
                $('#encoding_facial').val(encoding_facial);
                // Mostrar la imagen en la previsualización si hay una URL
                if (imagen) {
                    $('#previewImagen').attr('src', imagen).show();
                } else {
                    $('#previewImagen').hide();
                }
                $('#estado').val(estado);

                // Abrir el modal de empleado
                var modal = new bootstrap.Modal(document.getElementById("modal_empleado"));
                modal.show();
            });
        </script>
        
        <script>
            function buscar() {
                const searchTerm = document.getElementById('searchField').value.toLowerCase();
                let found = false;

                // Filtrar empleados
                $('#tbl_empleados tr').filter(function() {
                    const isMatch = $(this).text().toLowerCase().indexOf(searchTerm) > -1;
                    $(this).toggle(isMatch);
                    if (isMatch) found = true; // Actualiza found si hay coincidencias
                });
                
                // Mostrar alerta si no se encontraron resultados
                if (!found) {
                    alert("No se encontraron resultados.");
                }
            }
        </script>}
        
        <script>
            // Función para previsualizar la imagen seleccionada
            function previewImage(event) {
                var reader = new FileReader();
                reader.onload = function(){
                    var output = document.getElementById('previewImagen');
                    output.src = reader.result;
                    output.style.display = 'block';
                };
                reader.readAsDataURL(event.target.files[0]);
            }
        </script>
        
        <script>
        // Variables para la cámara y la imagen capturada
        const video = document.getElementById('video');
        const canvas = document.getElementById('canvas');
        const ctx = canvas.getContext('2d');
        const previewImagen = document.getElementById('previewImagen');
        const capturarBtn = document.getElementById('capturarBtn');

        // Acceder a la cámara del usuario
        navigator.mediaDevices.getUserMedia({ video: true })
          .then((stream) => {
            video.srcObject = stream;
          })
          .catch((err) => {
            console.error("Error al acceder a la cámara: ", err);
          });

        // Capturar la imagen cuando se presiona el botón
        capturarBtn.addEventListener('click', () => {
          // Establecer el tamaño del canvas según el tamaño del video
          canvas.width = video.videoWidth;
          canvas.height = video.videoHeight;

          // Dibujar la imagen del video en el canvas
          ctx.drawImage(video, 0, 0, canvas.width, canvas.height);

          // Obtener la imagen en formato Base64
          const dataUrl = canvas.toDataURL('image/png');

          // Mostrar la imagen en la vista previa
          previewImagen.src = dataUrl;
          previewImagen.style.display = 'block';  // Mostrar la imagen
          previewImagen.classList.add('img-thumbnail');

          // Detener la cámara después de capturar la foto
          let stream = video.srcObject;
          let tracks = stream.getTracks();
          tracks.forEach(track => track.stop());
          video.srcObject = null;  // Detener la cámara
        });
        </script>

    </body>
</html>