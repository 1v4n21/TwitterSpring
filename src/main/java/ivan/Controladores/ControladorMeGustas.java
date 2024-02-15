package ivan.Controladores;

import ivan.Constructores.MeGusta;
import ivan.Constructores.Publicacion;
import ivan.Constructores.Usuario;
import ivan.Servicios.ServicioGuardado;
import ivan.Servicios.ServicioMeGusta;
import ivan.Servicios.ServicioPublicacion;
import ivan.Servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControladorMeGustas {

    @Autowired
    private ServicioUsuario servicioU;

    @Autowired
    private ServicioPublicacion servicioP;

    @Autowired
    private ServicioGuardado servicioG;

    @Autowired
    private ServicioMeGusta servicioM;


    public ControladorMeGustas () {
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
}
