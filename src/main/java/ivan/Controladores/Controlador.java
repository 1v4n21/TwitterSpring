package ivan.Controladores;

import ivan.Constructores.Usuario;
import ivan.Modelos.UsuarioDAO;
import ivan.Servicios.ServicioGuardado;
import ivan.Servicios.ServicioMeGusta;
import ivan.Servicios.ServicioPublicacion;
import ivan.Servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controlador {

    @Autowired
    private ServicioUsuario servicioU;

    @Autowired
    private ServicioPublicacion servicioP;

    @Autowired
    private ServicioGuardado servicioG;

    @Autowired
    private ServicioMeGusta servicioM;


    public Controlador(){}

    @RequestMapping({"/", "/login"})
    public String saludo(){
        return "login";  //.jsp
    }

    @RequestMapping({"/registro"})
    public String registro(){
        return "registro";  //.jsp
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
