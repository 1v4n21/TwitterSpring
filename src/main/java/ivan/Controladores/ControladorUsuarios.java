package ivan.Controladores;

import ivan.Constructores.LoginForm;
import ivan.Constructores.Usuario;
import ivan.Servicios.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuario servicioU;

    public ControladorUsuarios (){}

    @RequestMapping({"/"})
    public String cargarAdmin(Model modelo) {
        //Añadimos el usuario admin
        Usuario a1 = servicioU.getUsuario ();
        a1.setNombre ("Admin1");
        a1.setEmail ("Admin1@gmail.com");
        a1.setNombreUsuario ("admin1");
        a1.setPassword ("root1234");
        a1.setRol ("admin");

        servicioU.agregarUsuario (a1);

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

                // Almacenar el usuario en la sesión y mandamos a inicio
                session.setAttribute("usuarioLogueado", usuario);
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
}
