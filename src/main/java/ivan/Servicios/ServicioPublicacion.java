package ivan.Servicios;

import ivan.Constructores.Guardado;
import ivan.Constructores.Publicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import ivan.Modelos.PublicacionDAO;

@Component
public class ServicioPublicacion {
    @Autowired
    private Publicacion publicacion;

    @Autowired
    private PublicacionDAO publicacionDAO;

    @Autowired
    private ServicioGuardado servicioG;

    public ServicioPublicacion() {
    }

    public ServicioPublicacion(PublicacionDAO publicacionDAO, Publicacion publicacion) {
        this.publicacionDAO = publicacionDAO;
        this.publicacion = publicacion;
    }

    public Publicacion getPublicacion() { return publicacion; }
    public PublicacionDAO getPublicacionDAO() {
        return publicacionDAO;
    }

    public void setPublicacionDAO(PublicacionDAO publicacionDAO) {
        this.publicacionDAO = publicacionDAO;
    }

    public void agregarPublicacion(Publicacion publicacion) {
        publicacionDAO.agregarPublicacion(publicacion);
    }

    public Publicacion obtenerPublicacionPorId(int idPublicacion) {
        return publicacionDAO.obtenerPublicacionPorId(idPublicacion);
    }

    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return publicacionDAO.obtenerTodasLasPublicaciones();
    }

    public List<Publicacion> obtenerPublicacionesPorIdUsuario(int idUsuario) {
        return publicacionDAO.obtenerPublicacionesPorIdUsuario(idUsuario);
    }

    public void actualizarPublicacion(Publicacion publicacion) {
        publicacionDAO.actualizarPublicacion(publicacion);
    }

    public void eliminarPublicacion(int idPublicacion) {
        publicacionDAO.eliminarPublicacion(idPublicacion);
    }

    public List<Publicacion> obtenerPublicacionesGuardadasPorUsuario(int idUsuario) {
        // Suponiendo que tienes un método en tu ServicioGuardado para obtener publicaciones guardadas por un usuario
        List<Guardado> guardados = servicioG.obtenerGuardadosPorIdUsuario(idUsuario);

        // Extraer los objetos Publicacion de las publicaciones guardadas
        List<Publicacion> publicacionesGuardadas = new ArrayList<>();
        for (Guardado guardado : guardados) {
            publicacionesGuardadas.add(guardado.getPublicacion());
        }

        // Ordenar la lista de publicaciones guardadas por fecha (de más reciente a más antiguo)
        publicacionesGuardadas.sort(Comparator.comparing(Publicacion::getFecha).reversed());

        return publicacionesGuardadas;
    }

}

