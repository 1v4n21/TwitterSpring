package ivan.Controladores;

import ivan.Constructores.Guardado;
import ivan.Constructores.Publicacion;
import ivan.Constructores.Usuario;
import ivan.Constructores.MeGusta;
import ivan.Servicios.ServicioGuardado;
import ivan.Servicios.ServicioMeGusta;
import ivan.Servicios.ServicioPublicacion;
import ivan.Servicios.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/inicio")
    public String inicio(Model modelo, HttpSession session) {
        // Verificar si la sesión ha sido iniciada
        if (session.getAttribute("usuarioLogueado") == null) {
            // Si no se ha iniciado sesión, redirigir a la página de login
            return "redirect:/login";
        }

        // Obtener todas las publicaciones
        List<Publicacion> publicaciones = servicioP.obtenerTodasLasPublicaciones();

        // Agregar las publicaciones y el usuario logueado al modelo
        modelo.addAttribute("lasPublicaciones", publicaciones);
        modelo.addAttribute("usuarioLogueado", session.getAttribute("usuarioLogueado"));

        // Retornar la vista de inicio
        return "inicio";
    }

    @PostMapping("/darLike")
    @ResponseBody
    public ResponseEntity<String> darLike(@RequestParam int postId, @RequestParam int userId) {
        // Obtener la publicación y el usuario
        Publicacion publicacion = servicioP.obtenerPublicacionPorId(postId);
        Usuario usuario = servicioU.obtenerUsuarioPorId(userId);

        // Verificar si la publicación y el usuario existen
        if (publicacion != null && usuario != null) {

            // Obtener el "me gusta" para la publicación y el usuario
            MeGusta meGusta = servicioM.obtenerMeGustaPorIdUsuarioYIdPublicacion(userId, postId);

            // Realizar la acción de "me gusta" o "no me gusta" según la situación actual
            if (meGusta != null) {
                // Si ya existe un "me gusta", eliminarlo
                servicioM.eliminarMeGusta(meGusta.getIdMG ());
            } else {
                // Si no existe un "me gusta", agregarlo
                servicioM.agregarMeGusta(new MeGusta(usuario, publicacion));
            }

            // Obtener el nuevo recuento de "me gusta" para la publicación
            int nuevoRecuentoMeGusta = publicacion.getMeGustas ().size ();

            // Devolver el nuevo recuento de "me gusta" como JSON
            return ResponseEntity.ok("{\"liked\":" + (meGusta == null) + ", \"likeCount\":" + nuevoRecuentoMeGusta + "}");
        } else {
            // Si la publicación o el usuario no existen, devolver un error 404
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardarPost")
    @ResponseBody
    public ResponseEntity<String> guardarPost(@RequestParam int postId, @RequestParam int userId) {
        // Obtener la publicación y el usuario
        Publicacion publicacion = servicioP.obtenerPublicacionPorId(postId);
        Usuario usuario = servicioU.obtenerUsuarioPorId(userId);

        // Verificar si la publicación y el usuario existen
        if (publicacion != null && usuario != null) {

            // Obtener el "me gusta" para la publicación y el usuario
            Guardado guardado = servicioG.obtenerGuardadoPorIdUsuarioYIdPublicacion (userId, postId);

            // Realizar la acción de guardar o desguardar según la situación actual
            if (guardado != null) {
                // Si el usuario ya ha guardado el post, desguardarlo
                servicioG.eliminarGuardado(guardado.getIdGuardado ());
            } else {
                // Si el usuario no ha guardado el post, guardarlo
                servicioG.agregarGuardado(new Guardado(usuario, publicacion));
            }

            // Obtener el nuevo recuento de guardados para la publicación
            int nuevoRecuentoGuardados = publicacion.getGuardados().size();

            // Devolver el nuevo recuento de guardados como JSON
            return ResponseEntity.ok("{\"saved\":" + (guardado == null) + ", \"saveCount\":" + nuevoRecuentoGuardados + "}");
        } else {
            // Si la publicación o el usuario no existen, devolver un error 404
            return ResponseEntity.notFound().build();
        }
    }

}