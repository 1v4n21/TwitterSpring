<%--
  Created by IntelliJ IDEA.
  User: alumno
  Date: 9/2/24
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>SocialTweet - Registro</title>

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
    <!-- Registro de SocialTweet -->
    <div class="wrapper">
        <div class="logo">
            <img src="static/images/icono.png" alt="">
        </div>
        <div class="text-center mt-4 name">
            SocialTweet
        </div>
        <form class="p-3 mt-3">
            <div class="form-field d-flex align-items-center">
                <span class="fa-solid fa-user-pen"></span>
                <input type="text" name="nombre" id="nombre" placeholder="Nombre">
            </div>
            <div class="form-field d-flex align-items-center">
                <span class="fa-solid fa-user-pen"></span>
                <input type="text" name="apellidos" id="apellidos" placeholder="Apellidos">
            </div>
            <div class="form-field d-flex align-items-center">
                <span class="fa-solid fa-building-user"></span>
                <input type="text" name="localidad" id="localidad" placeholder="Localidad">
            </div>
            <div class="form-field d-flex align-items-center">
                <span class="fa-solid fa-envelope"></span>
                <input type="email" name="email" id="email" placeholder="Email">
            </div>
            <div class="form-field d-flex align-items-center">
                <span class="fa-solid fa-user"></span>
                <input type="text" name="userName" id="userName" placeholder="Nombre Usuario">
            </div>
            <div class="form-field d-flex align-items-center">
                <span class="fa-solid fa-key"></span>
                <input type="password" name="password" id="pwd" placeholder="Contraseña">
            </div>
            <button class="btn mt-3">Registrarse</button>
        </form>
        <div class="text-center fs-6">
            <a href="login">Ya tienes cuenta? Inicia Sesion</a>
        </div>
    </div>

    <!-- Script bootsrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Script jquery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
