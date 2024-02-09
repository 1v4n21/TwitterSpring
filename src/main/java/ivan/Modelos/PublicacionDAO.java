package ivan.Modelos;

import ivan.Constructores.Publicacion;

import java.util.List;

public interface PublicacionDAO {
    void agregarPublicacion(Publicacion publicacion);
    Publicacion obtenerPublicacionPorId(int idPublicacion);
    List<Publicacion> obtenerTodasLasPublicaciones();
    List<Publicacion> obtenerPublicacionesPorIdUsuario(int idUsuario);
    void actualizarPublicacion(Publicacion publicacion);
    void eliminarPublicacion(int idPublicacion);
}

