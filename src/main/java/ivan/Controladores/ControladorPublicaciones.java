package ivan.Controladores;

import ivan.Constructores.Publicacion;
import ivan.Servicios.ServicioGuardado;
import ivan.Servicios.ServicioMeGusta;
import ivan.Servicios.ServicioPublicacion;
import ivan.Servicios.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ControladorPublicaciones {

    @Autowired
    private ServicioUsuario servicioU;

    @Autowired
    private ServicioPublicacion servicioP;

    @Autowired
    private ServicioGuardado servicioG;

    @Autowired
    private ServicioMeGusta servicioM;


    public ControladorPublicaciones () {
    }

    @RequestMapping({"inicio"})
    public String inicio (Model modelo, HttpSession session) {
        List<Publicacion> publicaciones = servicioP.obtenerTodasLasPublicaciones ();
        modelo.addAttribute ("lasPublicaciones", publicaciones);
        modelo.addAttribute ("usuarioLogueado", session.getAttribute ("usuarioLogueado"));
        return "inicio";
    }
}