<%--
  Created by IntelliJ IDEA.
  User: alumno
  Date: 18/2/24
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>SocialTweet - Admin</title>

    <!-- Link de estilos bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Link fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <!-- Estilos específicos para la página de admin -->
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
                    <a class="nav-link" href="admin">Admin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="guardados">Guardados</a>
                </li>
            </ul>
            <input id="searchInput" class="form-control me-2" type="search" placeholder="Buscar Usuario" aria-label="Buscar" disabled>
            <a class="btn btn-danger" href="logout">Logout</a>
        </div>
    </div>
</nav>

<!-- Nombre de usuario -->
<br>
<p class="text-center display-6">Usuario: <span class="text-primary">@${usuarioLogueado.nombreUsuario}</span></p>
<br>

<!-- Menú de administrador -->
<div class="container">
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link ${accion eq 'usuarios' ? 'active' : ''}" href="admin?accion=usuarios">Usuarios</a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${accion eq 'publicaciones' ? 'active' : ''}" href="admin?accion=publicaciones">Publicaciones</a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${accion eq 'megustas' ? 'active' : ''}" href="admin?accion=megustas">Me Gusta</a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${accion eq 'guardados' ? 'active' : ''}" href="admin?accion=guardados">Guardados</a>
        </li>
    </ul>
</div>

<!-- Lista de elementos según la acción -->
<div class="container mt-3">
    <c:choose>
        <c:when test="${accion eq 'usuarios'}">
            <!-- Lista de usuarios -->
            <ul class="list-group">
                <c:forEach var="usuario" items="${usuarios}">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                            ${usuario.nombreUsuario}
                        <div class="btn-group" role="group">
                            <c:choose>
                                <c:when test="${usuario.rol ne 'admin'}">
                                    <!-- Mostrar botones solo si el usuario no es admin -->
                                    <a href="formUsuario?id=${usuario.idUsuario}&accion=editar" class="btn btn-warning btn-sm">Editar</a>
                                    <a href="borrarUsuarioAdmin?userId=${usuario.idUsuario}" class="btn btn-danger btn-sm">Eliminar</a>
                                </c:when>
                                <c:otherwise>
                                    <!-- Puedes agregar un mensaje de depuración o simplemente dejar vacío si prefieres -->
                                    <span>Usuario admin, no se pueden realizar cambios</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </li>
                </c:forEach>
            </ul>

            <!-- Añadir usuario -->
            <div class="fixed-logo">
                <a href="formUsuario?accion=crear">
                    <i class="fa-solid fa-square-plus fa-2x"></i>
                </a>
            </div>
        </c:when>
        <c:when test="${accion eq 'publicaciones'}">
            <!-- Lista de publicaciones -->
            <ul class="list-group">
                <c:forEach var="publicacion" items="${publicaciones}">
                    <li class="list-group-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <strong>${publicacion.usuario.nombreUsuario}</strong>
                                <p>${publicacion.mensaje}</p>
                            </div>
                            <div class="btn-group" role="group">
                                <a href="publicacion?id=${publicacion.idPublicacion}" type="button" class="btn btn-warning btn-sm">Editar</a>
                                <a href="borrarPostAdmin?postId=${publicacion.idPublicacion}" type="button" class="btn btn-danger btn-sm">Eliminar</a>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>

            <!-- Añadir post -->
            <div class="fixed-logo">
                <a href="publicacion">
                    <i class="fa-solid fa-square-plus fa-2x"></i>
                </a>
            </div>
        </c:when>
        <c:when test="${accion eq 'megustas'}">
            <!-- Lista de Me Gusta -->
            <ul class="list-group">
                <c:forEach var="megusta" items="${megustas}">
                    <li class="list-group-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <strong>${megusta.usuario.nombreUsuario}</strong>
                                <p>${megusta.publicacion.mensaje}</p>
                            </div>
                            <div class="btn-group" role="group">
                                <a href="borrarMeGustaAdmin?meGustaId=${megusta.idMG}" type="button" class="btn btn-danger btn-sm">Eliminar</a>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:when test="${accion eq 'guardados'}">
            <!-- Lista de elementos guardados -->
            <ul class="list-group">
                <c:forEach var="guardado" items="${guardados}">
                    <li class="list-group-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <strong>${guardado.usuario.nombreUsuario}</strong>
                                <p>${guardado.publicacion.mensaje}</p>
                            </div>
                            <div class="btn-group" role="group">
                                <a href="borrarGuardadoAdmin?guardadoId=${guardado.idGuardado}" type="button" class="btn btn-danger btn-sm">Eliminar</a>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
    </c:choose>
</div>

<!-- Scripts de Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

