<%-- 
    Document   : Principal
    Created on : 30 mar 2025, 11:25:43
    Author     : DELL
--%>

<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="Modelo.Empleado"%>
<%@page import="jakarta.servlet.http.HttpSession" %>
<%
    if (session == null || session.getAttribute("empleado") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    Empleado empleado = (Empleado) session.getAttribute("empleado");

    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Página Principal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <style>
        body {
            background-color: #d8eefe;
            overflow-x: hidden;
        }

        /* Botón hamburguesa */
        .menu-toggle {
            font-size: 1.5rem;
            cursor: pointer;
            color: white;
            background: none;
            border: none;
            margin-left: 10px;
        }

        /* Menú lateral */
        .side-menu {
            height: 100%;
            width: 250px;
            position: fixed;
            top: 0;
            left: -250px;
            background-color: #094067;
            padding-top: 60px;
            transition: 0.3s;
            z-index: 1000;
        }

        .side-menu a {
            padding: 15px 25px;
            text-decoration: none;
            font-size: 18px;
            color: white;
            display: block;
            transition: 0.3s;
        }

        .side-menu a:hover {
            background-color: #3c91e6;
        }

        /* Fondo oscuro al abrir menú */
        .overlay {
            position: fixed;
            display: none;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background-color: rgba(0,0,0,0.5);
            z-index: 999;
        }

        /* Contenido principal */
        .content-container {
            max-width: 1200px;
            margin: 80px auto 20px;
            padding: 20px;
            border: 1px solid #094067;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        .navbar {
            background-color: #094067;
        }

        .navbar .dropdown-menu {
            background-color: #5f6c7b;
        }
    </style>
</head>
<body>
    <!-- Menú deslizante -->
    <div id="sideMenu" class="side-menu">
        <a href="Departamento.jsp" target="frame"><i class="bi bi-building"></i> Departamentos</a>
        <a href="Puesto.jsp" target="frame"><i class="bi bi-card-list"></i> Puestos</a>
        <a href="Empleado.jsp" target="frame"><i class="bi bi-people"></i> Empleados</a>
        <a href="Visitante.jsp" target="frame"><i class="bi bi-person-lines-fill"></i> Visitantes</a>
        <a href="Reporte.jsp" target="frame"><i class="bi bi-file-earmark-bar-graph"></i> Reportes</a>
    </div>

    <!-- Fondo oscuro -->
    <div id="overlay" class="overlay" onclick="toggleMenu()"></div>

    <!-- Barra superior -->
    <nav class="navbar navbar-dark fixed-top">
        <div class="container-fluid">
            <button class="menu-toggle" onclick="toggleMenu()">
                <i class="bi bi-list"></i>
            </button>
            <span class="navbar-brand ms-2">Bienvenido, <%= empleado.getNombres() %></span>

            <div class="dropdown ms-auto">
                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" style="background-color: #094067; border: none;">
                    <i class="bi bi-person-circle" style="font-size: 24px;"></i>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="#"><i class="bi bi-person"></i> <%= empleado.getNombres() %></a></li>
                    <li><a class="dropdown-item" href="Logout"><i class="bi bi-box-arrow-right"></i> Salir</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="content-container">
        <iframe name="frame" style="height: 530px; width: 100%; border: none;"></iframe>
    </div>

    <!-- Scripts -->
    <script>
        function toggleMenu() {
            const menu = document.getElementById("sideMenu");
            const overlay = document.getElementById("overlay");

            if (menu.style.left === "0px") {
                menu.style.left = "-250px";
                overlay.style.display = "none";
            } else {
                menu.style.left = "0px";
                overlay.style.display = "block";
            }
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>