package ivan.Controladores;

import ivan.Constructores.Usuario;
import ivan.Modelos.UsuarioDAO;
import ivan.Servicios.ServicioGuardado;
import ivan.Servicios.ServicioMeGusta;
import ivan.Servicios.ServicioPublicacion;
import ivan.Servicios.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuario servicioU;

    @Autowired
    private ServicioPublicacion servicioP;

    @Autowired
    private ServicioGuardado servicioG;

    @Autowired
    private ServicioMeGusta servicioM;


    public ControladorUsuarios (){}

    @GetMapping({"/login", "/"})
    public String mostrarLogin(Model modelo) {
        modelo.addAttribute("elUsuario", servicioU.getUsuario ());
        return "login";
    }

    @PostMapping("/login")
    public String validarLogin(@ModelAttribute("elUsuario") @Valid Usuario usuario, BindingResult result, Model model, HttpSession session) {
        if (!result.hasErrors()) {
            Usuario usuario1 = servicioU.verificarUsuario(usuario.getNombreUsuario(), usuario.getPassword());

            if (usuario1 != null) {
                // Almacenar el usuario en la sesión
                session.setAttribute("usuarioLogueado", usuario1);
                return "redirect:/inicio";
            } else {
                model.addAttribute("error", "Credenciales incorrectas. Inténtalo de nuevo.");
                model.addAttribute("hasError", true);
                return "login";
            }
        } else {
            // Restablecer el modelo en caso de errores
            model.addAttribute("hasError", true);
            return "login";
        }
    }

    @RequestMapping({"/registro"})
    public String registro(){
        return "registro";
    }

    @RequestMapping({"/usuario"})
    String usuario(){

        try {
            Usuario usuario1 = servicioU.getUsuario();
            UsuarioDAO usuarioDAO = servicioU.getUsuarioDAO();

            usuario1.setNombre("Ivan");
            usuario1.setApellidos("Martinez");

            usuarioDAO.agregarUsuario (usuario1);

            return "login";
        } catch (Exception e) {
            // Manejo de excepciones, por ejemplo, loguear el error
            e.printStackTrace();
            return "error"; // Puedes redirigir a una página de error
        }
    }
}
