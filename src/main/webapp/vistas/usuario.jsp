<%--
  Created by IntelliJ IDEA.
  User: alumno
  Date: 18/2/24
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>SocialTweet - Usuario</title>

    <!-- Link de estilos Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Link Font Awesome -->
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
                <c:choose>
                    <c:when test="${usuarioLogueado.rol == 'admin'}">
                        <li class="nav-item">
                            <a class="nav-link" href="admin">Admin</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="ajustes?id=${usuarioLogueado.idUsuario}">Ajustes</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item">
                    <a class="nav-link" href="guardados">Guardados</a>
                </li>
            </ul>
            <!-- Campo de búsqueda -->
            <input id="searchInput" class="form-control me-2" type="search" placeholder="Buscar Usuario" aria-label="Buscar" disabled>

            <!-- Botón de Logout con color rojo y dinámico -->
            <a class="btn btn-danger" href="logout">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h2 class="mb-4">${accion eq 'crear' ? 'Crear' : 'Editar'} Usuario</h2>

    <!-- Formulario para crear usuario -->
    <form:form method="post" modelAttribute="usuario" action="${accion eq 'crear' ? '/crearUsuarioAdmin' : '/editarUsuarioAdmin'}">

        <!-- Campos del formulario -->
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <form:input type="text" class="form-control" id="nombre" path="nombre" />
        </div>

        <div class="mb-3">
            <label for="apellidos" class="form-label">Apellidos</label>
            <form:input type="text" class="form-control" id="apellidos" path="apellidos" />
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <form:input type="text" class="form-control" id="email" path="email" />
        </div>

        <div class="mb-3">
            <label for="nombreUsuario" class="form-label">Nombre de Usuario</label>
            <form:input type="text" class="form-control" id="nombreUsuario" path="nombreUsuario" />
        </div>

        <div class="mb-3">
            <label for="localidad" class="form-label">Localidad</label>
            <form:input type="text" class="form-control" id="localidad" path="localidad" />
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Contraseña</label>
            <form:password type="password" class="form-control" id="password" path="password" />
        </div>

        <div class="mb-3">
            <label for="rol" class="form-label">Rol</label>
            <form:select class="form-select" id="rol" path="rol">
                <form:option value="normal">Normal</form:option>
                <form:option value="admin">Admin</form:option>
            </form:select>
        </div>

        <!-- Botón de guardar cambios o crear usuario -->
        <button type="submit" class="btn btn-primary">Crear Usuario</button>
    </form:form>
</div>

<!-- Scripts de Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

