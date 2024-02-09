package ivan.Modelos;

import ivan.Constructores.MeGusta;

import java.util.List;

public interface MeGustaDAO {
    void agregarMeGusta(MeGusta meGusta);
    MeGusta obtenerMeGustaPorId(int idMG);
    List<MeGusta> obtenerTodosLosMeGustas();
    List<MeGusta> obtenerMeGustasPorIdUsuario(int idUsuario);
    List<MeGusta> obtenerMeGustasPorIdPublicacion(int idPublicacion);
    MeGusta obtenerMeGustaPorIdUsuarioYIdPublicacion(int idUsuario, int idPublicacion);
    void actualizarMeGusta(MeGusta meGusta);
    void eliminarMeGusta(int idMG);
}
