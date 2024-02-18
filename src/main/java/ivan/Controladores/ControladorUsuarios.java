package ivan.Controladores;

import ivan.Constructores.LoginForm;
import ivan.Constructores.Usuario;
import ivan.Servicios.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuario servicioU;

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
    public String mostrarEditar(Model modelo, @RequestParam(name = "id") Integer id) {
        //Añadimos el modelo de usuario y mandamos a ajustes de nuevo
        modelo.addAttribute("elUsuario", servicioU.obtenerUsuarioPorId (id));
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
                if (error.getField().equals("nombreUsuario") || error.getField().equals("password") || error.getField().equals("nombre") || error.getField().equals("email")) {
                    model.addAttribute("error", error.getDefaultMessage());
                    break;
                }
            }

            // Restablecer el modelo en caso de errores
            return "ajustes";
        }
    }
}
