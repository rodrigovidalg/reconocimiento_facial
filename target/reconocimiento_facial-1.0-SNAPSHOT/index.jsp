<%-- 
    Document   : index
    Created on : 30 mar 2025, 11:24:39
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap y estilos -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

    <style>
        body {
            margin: 0;
            height: 100vh;
            background: linear-gradient(135deg, #0f2027, #203a43, #2c5364);
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', sans-serif;
        }

        .login-box {
            text-align: center;
            color: white;
            transition: all 0.5s ease;
            cursor: pointer;
            border: 2px solid #0ff;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 0 25px rgba(0, 255, 255, 0.4);
            background-color: rgba(9, 64, 103, 0.7);
            backdrop-filter: blur(5px);
        }

        .login-box:hover {
            box-shadow: 0 0 35px rgba(0, 255, 255, 0.7);
        }

        .login-form {
            margin-top: 20px;
            display: none;
            animation: fadeIn 1s forwards;
        }

        .form-control {
            background-color: #ffffffd9;
            border: none;
        }

        .form-control:focus {
            box-shadow: 0 0 5px #0ff;
        }

        .btn-login {
            background-color: #ff4c60;
            border: none;
        }

        .btn-login:hover {
            background-color: #ff1c3d;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }
    </style>
</head>
<body>

    <div class="login-box" id="loginBox">
        <h2 id="loginTitle">Login</h2>

        <form class="login-form" id="loginForm" action="sr_cIndex" method="POST">
            <div class="mb-3 text-start text-white">
                <label for="username" class="form-label"><i class="bi bi-person"></i> Usuario</label>
                <input type="text" name="txt_user" id="username" class="form-control" required>
            </div>
            <div class="mb-3 text-start text-white position-relative">
                <label for="password" class="form-label"><i class="bi bi-lock"></i> Contraseña</label>
                <div class="input-group">
                    <input type="password" name="txt_pass" id="password" class="form-control" required>
                    <span class="input-group-text" id="togglePassword" style="cursor: pointer;">
                        <i class="bi bi-eye" id="icon-eye"></i>
                    </span>
                </div>
            </div>
            <div class="d-grid mt-4">
                <button type="submit" name="accion" value="Ingresar" class="btn btn-login text-white">
                    <i class="bi bi-box-arrow-in-right"></i> Ingresar
                </button>
            </div>
        </form>
    </div>

    <!-- JavaScript para animación -->
    <script>
        const loginBox = document.getElementById('loginBox');
        const loginForm = document.getElementById('loginForm');
        const loginTitle = document.getElementById('loginTitle');

        let isExpanded = false;

        loginBox.addEventListener('click', () => {
            if (!isExpanded) {
                loginForm.style.display = 'block';
                isExpanded = true;
            }
        });

        // Mostrar/Ocultar contraseña
        const togglePassword = document.getElementById('togglePassword');
        const passwordInput = document.getElementById('password');
        const iconEye = document.getElementById('icon-eye');

        togglePassword.addEventListener('click', (event) => {
            event.stopPropagation(); // Evita conflicto con evento de expansión

            const isPassword = passwordInput.type === 'password';
            passwordInput.type = isPassword ? 'text' : 'password';

            iconEye.classList.toggle('bi-eye');
            iconEye.classList.toggle('bi-eye-slash');
        });
    </script>

</body>
</html>
