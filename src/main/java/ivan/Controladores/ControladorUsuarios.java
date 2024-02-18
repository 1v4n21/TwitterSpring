package ivan.Controladores;

import ivan.Constructores.*;
import ivan.Servicios.ServicioGuardado;
import ivan.Servicios.ServicioMeGusta;
import ivan.Servicios.ServicioPublicacion;
import ivan.Servicios.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuario servicioU;

    @Autowired
    private ServicioPublicacion servicioP;

    @Autowired
    private ServicioMeGusta servicioM;

    @Autowired
    private ServicioGuardado servicioG;

    public ControladorUsuarios (){}

    @RequestMapping({"/"})
    public String cargarAdmin(Model modelo) {
        // Verificar si ya existe un usuario admin
        if (servicioU.obtenerUsuarioPorNombreUsuario("admin") == null) {
            // Si no existe, creamos el usuario admin
            Usuario a1 = new Usuario();
            a1.setNombre("Admin");
            a1.setEmail("Admin@gmail.com");
            a1.setNombreUsuario("admin");
            a1.setPassword("root1234");
            a1.setRol("admin");

            servicioU.agregarUsuario(a1);
        }

        return "redirect:/login";
    }

    @GetMapping({"/login"})
    public String mostrarLogin(Model modelo) {
        //Añadimos el modelo de usuario y mandamos a login de nuevo
        modelo.addAttribute("elUsuario", new LoginForm ());
        return "login";
    }

    @PostMapping("/login")
    public String validarLogin(@ModelAttribute("elUsuario") @Valid LoginForm usuarioLogin, BindingResult result, Model model, HttpSession session) {
        if (!result.hasErrors()) {
            //Comprobamos si existe un usuario con ese nombre de usuario y contraseña
            Usuario usuario1 = servicioU.verificarUsuario(usuarioLogin.getNombreUsuario(), usuarioLogin.getPassword());

            if (usuario1 != null) {
                // Almacenar el usuario en la sesión y mandamos a inicio
                session.setAttribute("usuarioLogueado", usuario1);
                return "redirect:/inicio";
            } else {
                // Restablecer el modelo en caso de errores
                model.addAttribute("error", "Credenciales incorrectas. Inténtalo de nuevo.");
                model.addAttribute("hasError", true);
                return "login";
            }
        } else {
            // Obtener el primer error y verificar el campo asociado
            for (FieldError error : result.getFieldErrors()) {
                if (error.getField().equals("nombreUsuario") || error.getField().equals("password")) {
                    model.addAttribute("error", error.getDefaultMessage());
                    break;
                }
            }

            // Restablecer el modelo en caso de errores
            model.addAttribute("hasError", true);
            return "login";
        }
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model modelo) {
        //Añadimos el modelo de usuario y mandamos a registro de nuevo
        modelo.addAttribute("elUsuario", servicioU.getUsuario ());
        return "registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute("elUsuario") @Valid Usuario usuario, BindingResult result, Model model, HttpSession session) {
        if (!result.hasErrors()) {
            //Verificamos si ya existe o no un usuario con este nombre de usuario
            Usuario usuario1 = servicioU.obtenerUsuarioPorNombreUsuario (usuario.getNombreUsuario ());

            if (usuario1 == null){
                //Registramos el usuario en caso de que este no exista
                usuario.setRol ("normal");
                servicioU.agregarUsuario (usuario);

                Usuario usuarioNuevo = servicioU.obtenerUsuarioPorNombreUsuario (usuario.getNombreUsuario ());

                // Almacenar el usuario en la sesión y mandamos a inicio
                session.setAttribute("usuarioLogueado", usuarioNuevo);
                return "redirect:/inicio";
            }else {
                // Restablecer el modelo en caso de errores
                model.addAttribute("error", "Ya existe este nombre de usuario");
                model.addAttribute("hasError", true);
                return "registro";
            }
        } else {
            // Obtener el primer error y verificar el campo asociado
            for (FieldError error : result.getFieldErrors()) {
                if (error.getField().equals("nombreUsuario") || error.getField().equals("password") || error.getField().equals("nombre") || error.getField().equals("email")) {
                    model.addAttribute("error", error.getDefaultMessage());
                    break;
                }
            }

            // Restablecer el modelo en caso de errores
            model.addAttribute("hasError", true);
            return "registro";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Obtener la sesión y invalidarla
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Redirigir a la página de login
        return "redirect:/login";
    }

    @GetMapping("/ajustes")
    public String mostrarEditar(Model modelo, HttpSession session) {
        // Verificar si el usuario ha iniciado sesión
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            // Redirigir a la página de inicio de sesión si no hay usuario en la sesión
            return "redirect:/login"; // Ajusta la URL de inicio de sesión según tu configuración
        }

        // Añadimos el modelo de usuario y mandamos a ajustes de nuevo
        modelo.addAttribute("elUsuario", servicioU.obtenerUsuarioPorId(usuarioLogueado.getIdUsuario()));
        return "ajustes";
    }

    @PostMapping("/ajustes")
    public String procesarEdicion(@ModelAttribute("elUsuario") @Valid Usuario usuario, BindingResult result, Model model, HttpSession session) {
        if (!result.hasErrors()) {
            //Verificamos si ya existe o no un usuario con este nombre de usuario
            Usuario usuario1 = servicioU.obtenerUsuarioPorNombreUsuario (usuario.getNombreUsuario ());

            if (usuario1 == null || usuario1.getIdUsuario () == usuario.getIdUsuario ()){
                //Actualizamos el usuario
                servicioU.actualizarUsuario (usuario);

                Usuario usuarioNuevo = servicioU.obtenerUsuarioPorNombreUsuario (usuario.getNombreUsuario ());

                // Almacenar el usuario en la sesión y mandamos a inicio
                session.setAttribute("usuarioLogueado", usuarioNuevo);
                return "redirect:/inicio";
            }else {
                // Restablecer el modelo en caso de errores
                model.addAttribute("error", "Ya existe este nombre de usuario");
                return "ajustes";
            }
        } else {
            // Obtener el primer error y verificar el campo asociado
            for (FieldError error : result.getFieldErrors()) {
                if (error.getField().equals("nombreUsuario") || error.getField().equals("password") || error.getField().equals("email")) {
                    model.addAttribute("error", error.getDefaultMessage());
                    break;
                }
            }

            // Restablecer el modelo en caso de errores
            return "ajustes";
        }
    }

    @GetMapping("/admin")
    public String admin(@RequestParam(name = "accion", required = false) String accion,
                        Model modelo, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/login";
        }

        if (!usuario.getRol().equals("admin")) {
            return "redirect:/inicio";
        }

        // Acción por defecto (si no se especifica ninguna en la URL)
        if (accion == null || accion.isEmpty()) {
            accion = "usuarios";
        }

        switch (accion) {
            case "usuarios":
                List<Usuario> usuarios = servicioU.obtenerTodosLosUsuarios();
                modelo.addAttribute("usuarios", usuarios);
                break;
            case "publicaciones":
                List<Publicacion> publicaciones = servicioP.obtenerTodasLasPublicaciones();
                modelo.addAttribute("publicaciones", publicaciones);
                break;
            case "megustas":
                List<MeGusta> meGustas = servicioM.obtenerTodosLosMeGustas();
                modelo.addAttribute("megustas", meGustas);
                break;
            case "guardados":
                List<Guardado> guardados = servicioG.obtenerTodosLosGuardados();
                modelo.addAttribute("guardados", guardados);
                break;
            default:
                // Acción no válida, redirigir a la página de administrador con usuarios por defecto
                return "redirect:/admin?accion=usuarios";
        }

        modelo.addAttribute("usuarioLogueado", usuario);
        modelo.addAttribute("accion", accion); // Para usar en la vista y resaltar la pestaña activa

        // Retornar la vista de admin
        return "admin";
    }

    @GetMapping("/borrarUsuarioAdmin")
    @ResponseBody
    public ResponseEntity<String> borrarUsuarioAdmin(@RequestParam int userId, HttpSession session, RedirectAttributes redirectAttributes) {
        // Verificar si el usuario de la sesión es admin
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioSesion == null || !usuarioSesion.getRol().equals("admin")) {
            // Si el usuario no es admin, redirigir y mostrar un mensaje de error
            redirectAttributes.addFlashAttribute("error", "Acceso no autorizado");
            return ResponseEntity.status(403).body("Acceso no autorizado");
        }

        // Obtener el usuario
        Usuario usuario = servicioU.obtenerUsuarioPorId(userId);

        // Verificar si el usuario existe
        if (usuario != null) {
            // Borrar el usuario
            servicioU.eliminarUsuario(usuario.getIdUsuario());

            // Devolver el estado como respuesta
            return ResponseEntity.ok("Usuario borrado exitosamente");
        } else {
            // Si el usuario no existe, devolver un error 404
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/formUsuario")
    public String mostrarFormularioUsuario(Model modelo,
                                           @RequestParam(name = "accion") String accion,
                                           @RequestParam(name = "id", required = false) Integer id,
                                           HttpSession session) {

        // Verificar si el usuario de la sesión es admin
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null || !usuarioLogueado.getRol().equals("admin")) {
            // Si no es admin, redirigir a la página de login
            return "redirect:/login";
        }

        // Lógica para obtener datos del usuario según la acción (crear o editar)
        Usuario usuario;
        if (accion.equals("crear")) {
            usuario = new Usuario();
        } else if (accion.equals("editar")) {
            usuario = servicioU.obtenerUsuarioPorId(id);
        } else {
            return "redirect:/admin";
        }

        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("accion", accion);

        // Devolver la vista del formulario
        return "usuario";
    }

    @PostMapping("/crearUsuarioAdmin")
    public String crearUsuario(@ModelAttribute Usuario usuario) {
        // Lógica para crear un nuevo usuario
        servicioU.agregarUsuario (usuario);
        return "redirect:/admin";
    }

    @PostMapping("/editarUsuarioAdmin")
    public String editarUsuario(@ModelAttribute Usuario usuario) {
        // Lógica para editar un usuario existente
        servicioU.actualizarUsuario (usuario);
        return "redirect:/admin";
    }
}
