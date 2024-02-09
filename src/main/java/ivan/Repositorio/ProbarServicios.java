package ivan.Repositorio;

import ivan.Constructores.Usuario;
import ivan.Modelos.UsuarioDAO;
import ivan.Servicios.ServicioUsuario;
import ivan.Configuracion.ConfiguracionSpring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProbarServicios {
    public static void main (String[] args) {
        ApplicationContext contenedorSp = new AnnotationConfigApplicationContext(ConfiguracionSpring.class);
        ServicioUsuario servicio = (ServicioUsuario) contenedorSp.getBean("servicio");
        Usuario usuario1 = servicio.getUsuario();


        UsuarioDAO daoUsuario = servicio.getUsuarioDAO();

        usuario1.setNombreUsuario ("martinezzs29");
        usuario1.setNombre ("Ivan");
        usuario1.setApellidos ("Martinez Sanchez");
        usuario1.setLocalidad ("Argamasilla de Alba");
        usuario1.setEmail ("prueba@gmail.com");
        usuario1.setPassword ("1234");
        usuario1.setRol ("admin");

        daoUsuario.agregarUsuario (usuario1);

        daoUsuario.obtenerTodosLosUsuarios ().forEach(r -> {
            System.out.println(r.getIdUsuario () + "--" + r.getNombre ());
        });

        ((AnnotationConfigApplicationContext) contenedorSp).close();
    }
}
