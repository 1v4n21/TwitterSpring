<%--
  Created by IntelliJ IDEA.
  User: alumno
  Date: 9/2/24
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>SocialTweet - Login</title>

    <!-- Link de estilos bootsrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Link fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <!-- Hoja de estilos -->
    <link rel="stylesheet" href="static/estilos/LoginRegistro.css">

    <!-- Icono -->
    <link rel="icon" type="image/x-icon" href="static/images/favicon.ico">
</head>
<body>
    <!-- Login de registro SocialTweet -->
    <div class="wrapper">
        <div class="logo">
            <img src="static/images/icono.png" alt="">
        </div>
        <div class="text-center mt-4 name">
            SocialTweet
        </div>

        <form:form action="login" method="post" modelAttribute="elUsuario" id="loginForm">

            <!-- Nombre de Usuario -->
            <div class="form-field d-flex align-items-center">
                <span class="fa-solid fa-user"></span>
                <form:input path="nombreUsuario" id="userName" placeholder="Nombre Usuario" />
            </div>

            <!-- Contraseña -->
            <div class="form-field d-flex align-items-center">
                <span class="fa-solid fa-key"></span>
                <form:password path="password" id="pwd" placeholder="Contraseña" />
            </div>

            <!-- Boton de envio de formulario -->
            <button type="submit" class="btn mt-3">Iniciar Sesión</button>
        </form:form>

        <!-- Limpiar los input en caso de error -->
        <c:if test="${hasError}">
            <script>
                document.getElementById("userName").value = "";
                document.getElementById("pwd").value = "";
            </script>
        </c:if>

        <!-- Link para realizar el registro -->
        <div class="text-center fs-6">
            <a href="registro">No tienes cuenta? Registrate</a>
        </div>

        <!-- mostrar el mensaje de error -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">
                <p><c:out value="${error}" /></p>
            </div>
        </c:if>
    </div>

    <!-- Script bootsrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Script jquery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
