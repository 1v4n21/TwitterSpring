package ivan.Servicios;

import ivan.Constructores.Publicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import ivan.Modelos.PublicacionDAO;

@Component
public class ServicioPublicacion {

    @Autowired
    private PublicacionDAO publicacionDAO;

    public ServicioPublicacion() {
    }

    public ServicioPublicacion(PublicacionDAO publicacionDAO) {
        this.publicacionDAO = publicacionDAO;
    }

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
}

