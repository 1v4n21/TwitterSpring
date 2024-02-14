<%--
  Created by IntelliJ IDEA.
  User: alumno
  Date: 9/2/24
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>SocialTweet - Inicio</title>

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
            <a class="navbar-brand" href="#">
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
                        <a class="nav-link" href="#guardados">Guardados</a>
                    </li>
                </ul>
                <!-- Campo de búsqueda -->
                <input class="form-control me-2" type="search" placeholder="Buscar Usuario" aria-label="Buscar">
                <!-- Botón de Logout con color rojo y dinámico -->
                <a class="btn btn-danger" href="logout">Logout</a>
            </div>
        </div>
    </nav>

    <br><br>

    <!-- Posts -->
    <div class="container">
        <c:forEach items="${lasPublicaciones}" var="post">
            <div class="post">
                <div class="post-title">@${post.usuario.nombreUsuario}</div>
                <small class="text-muted">${post.fecha}</small>
                <div class="post-content">
                        ${post.mensaje}
                </div>
                <br>
                <div class="post-actions">
                    <c:if test="${post.usuarioHaDadoMeGusta(usuarioLogueado.idUsuario)}">
                        <i class="fa-solid fa-thumbs-up"></i> ${post.meGustas.size()}
                    </c:if>
                    <c:if test="${!post.usuarioHaDadoMeGusta(usuarioLogueado.idUsuario)}">
                        <i class="fa-regular fa-thumbs-up"></i> ${post.meGustas.size()}
                    </c:if>
                    &nbsp;&nbsp;&nbsp;
                    <c:if test="${post.usuarioHaGuardado(usuarioLogueado.idUsuario)}">
                        <i class="fa-solid fa-bookmark"></i> ${post.guardados.size()}
                    </c:if>
                    <c:if test="${!post.usuarioHaGuardado(usuarioLogueado.idUsuario)}">
                        <i class="fa-regular fa-bookmark"></i> ${post.guardados.size()}
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Scripts de Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

