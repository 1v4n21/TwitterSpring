<%--
  Created by IntelliJ IDEA.
  User: alumno
  Date: 17/2/24
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>SocialTweet - Ajustes</title>

    <!-- Link de estilos bootsrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Link fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <!-- Hoja de estilos -->
    <link rel="stylesheet" href="static/estilos/Inicio.css">

    <!-- Icono -->
    <link rel="icon" type="image/x-icon" href="static/images/favicon.ico">
</head>
<body>
<!-- Cabecera -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" href="inicio">
            SocialTweet
            <img src="static/images/icono.png" alt="Logo de SocialTweet">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fa-solid fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#ajustes">Ajustes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="guardados">Guardados</a>
                </li>
            </ul>
            <!-- Campo de búsqueda -->
            <input id="searchInput" class="form-control me-2" type="search" placeholder="Buscar Usuario" aria-label="Buscar">

            <!-- Botón de Logout con color rojo y dinámico -->
            <a class="btn btn-danger" href="logout">Logout</a>
        </div>
    </div>
</nav>

<!-- Nombre de usuario -->
<br>
<p class="text-center display-6">Usuario: <span class="text-primary">@${usuarioLogueado.nombreUsuario}</span></p>
<br>

<!-- Formulario de Ajustes -->
<div class="container mt-4">
    <h2 class="mb-4">Ajustes de Usuario</h2>

    <!-- Formulario de ajustes de usuario -->
    <form:form id="ajustes" action="ajustes" method="post" modelAttribute="elUsuario" class="p-3 mt-3">
        <form:input path="idUsuario" type="hidden" name="idUsuario" value="${elUsuario.idUsuario}" />
        <form:input path="nombre" type="hidden" name="nombre" value="${elUsuario.nombre}" />
        <form:input path="apellidos" type="hidden" name="apellidos" value="${elUsuario.apellidos}" />
        <form:input path="localidad" type="hidden" name="localidad" value="${elUsuario.localidad}" />
        <form:input path="rol" type="hidden" name="rol" value="${elUsuario.rol}" />

        <!-- Nuevo Nombre de Usuario -->
        <div class="mb-3">
            <label for="nombreUsuario" class="form-label">Nuevo Nombre de Usuario</label>
            <form:input type="text" class="form-control" id="nombreUsuario" path="nombreUsuario" />
        </div>

        <!-- Nuevo Email -->
        <div class="mb-3">
            <label for="email" class="form-label">Nuevo Email</label>
            <form:input type="email" class="form-control" id="email" path="email" />
        </div>

        <!-- Nueva Contraseña -->
        <div class="mb-3">
            <label for="password" class="form-label">Nueva Contraseña</label>
            <form:input type="password" class="form-control" id="password" path="password" />
        </div>

        <!-- Botón de guardar cambios -->
        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
    </form:form>

    <!-- Limpiar los input en caso de error -->
    <c:if test="${hasError}">
        <script>
            document.getElementById("nombreUsuario").value = "";
            document.getElementById("password").value = "";
            document.getElementById("email").value = "";
        </script>
    </c:if>

    <!-- mostrar el mensaje de error -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">
            <p><c:out value="${error}" /></p>
        </div>
    </c:if>
</div>

<!-- Scripts de Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

