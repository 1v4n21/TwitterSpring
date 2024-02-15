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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @PostMapping("/borrarPost")
    @ResponseBody
    public ResponseEntity<String> borrarPost(@RequestParam int postId) {
        // Obtener la publicación
        Publicacion publicacion = servicioP.obtenerPublicacionPorId(postId);

        // Verificar si la publicación existe
        if (publicacion != null) {
            //Borramos el post
            servicioP.eliminarPublicacion (publicacion.getIdPublicacion ());

            // Devolver el estado como JSON
            return ResponseEntity.ok("{\"respuesta\":\"ok\"}");
        } else {
            // Si la publicación o el usuario no existen, devolver un error 404
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/publicacion")
    public String mostrarFormularioPublicacion(Model modelo, @RequestParam(name = "id", required = false) Integer id) {
        if (id != null) {
            // Si se proporciona un ID, se trata de una edición, por lo que obtenemos la publicación existente
            Publicacion publicacionExistente = servicioP.obtenerPublicacionPorId(id);
            modelo.addAttribute("laPublicacion", publicacionExistente);
            modelo.addAttribute ("form", "Editar");
        } else {
            // Si no se proporciona un ID, es una nueva publicación
            modelo.addAttribute("laPublicacion", new Publicacion());
            modelo.addAttribute ("form", "Crear");
        }

        return "publicacion";
    }

    @PostMapping("/crearPublicacion")
    public String crearPublicacion(@ModelAttribute("laPublicacion") @Valid Publicacion publicacion, BindingResult result, Model model, HttpSession session) {
        if (!result.hasErrors()) {
            // Si es una edición, actualiza la fecha
            if (publicacion.getIdPublicacion() != 0) {
                Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
                publicacion.setUsuario (usuarioLogueado);
                publicacion.setFecha(new Date());
                publicacion.setMensaje (publicacion.getMensaje ()+" (Editado)");
                servicioP.actualizarPublicacion (publicacion);
            } else {
                // Insertar el usuario
                Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
                publicacion.setUsuario(usuarioLogueado);
                publicacion.setFecha(new Date());

                // Guardar la publicación
                servicioP.agregarPublicacion(publicacion);
            }

            // Redirigir a la página de inicio
            return "redirect:/inicio";
        } else {
            // Obtener el primer error y verificar el campo asociado
            for (FieldError error : result.getFieldErrors()) {
                if (error.getField().equals("mensaje")) {
                    model.addAttribute("error", error.getDefaultMessage());
                    break;
                }
            }

            // Restablecer el modelo en caso de errores
            model.addAttribute("hasError", true);
            return "publicacion";
        }
    }

    @PostMapping("/buscarPublicaciones")
    @ResponseBody
    public ResponseEntity<String> buscarPublicaciones(@RequestParam String username, HttpSession session) {
        // Obtener la información del usuario logueado desde la sesión
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        Integer idUsuario = usuarioLogueado.getIdUsuario ();
        String rolUsuario = usuarioLogueado.getRol ();

        // Obtener la publicación
        List<Publicacion> publicaciones = servicioP.buscarPublicacionesPorNombreUsuario(username);

        // Construir manualmente el JSON
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{")
                .append("\"usuarioActual\":{")
                .append("\"id\":").append(idUsuario).append(",")
                .append("\"rol\":\"").append(rolUsuario).append("\"")
                .append("},")
                .append("\"publicaciones\":[");

        for (Publicacion publicacion : publicaciones) {
            jsonBuilder.append("{")
                    .append("\"idPublicacion\":").append(publicacion.getIdPublicacion()).append(",")
                    .append("\"mensaje\":\"").append(publicacion.getMensaje()).append("\",")
                    .append("\"fecha\":\"").append(publicacion.obtenerTiempoTranscurrido ()).append("\",")
                    .append("\"idUsuario\":").append(publicacion.getUsuario().getIdUsuario()).append(",")
                    .append("\"nombreUsuario\":\"").append(publicacion.getUsuario().getNombreUsuario()).append("\",")
                    .append("\"megustas\":").append(publicacion.getMeGustas().size()).append(",")
                    .append("\"guardados\":").append(publicacion.getGuardados().size()).append(",")
                    .append("\"usuarioHaDadoMeGusta\":").append(publicacion.usuarioHaDadoMeGusta(idUsuario)).append(",")
                    .append("\"usuarioHaGuardado\":").append(publicacion.usuarioHaGuardado(idUsuario))
                    .append("},");
        }

        if (!publicaciones.isEmpty()) {
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1); // Eliminar la última coma
        }

        jsonBuilder.append("]}");

        return ResponseEntity.ok(jsonBuilder.toString());
    }
}