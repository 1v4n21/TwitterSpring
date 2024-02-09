package ivan.Modelos;

import ivan.Constructores.Guardado;

import java.util.List;

public interface GuardadoDAO {
    void agregarGuardado(Guardado guardado);
    Guardado obtenerGuardadoPorId(int idGuardado);
    List<Guardado> obtenerTodosLosGuardados();
    List<Guardado> obtenerGuardadosPorIdUsuario(int idUsuario);
    List<Guardado> obtenerGuardadosPorIdPublicacion(int idPublicacion);
    Guardado obtenerGuardadoPorIdUsuarioYIdPublicacion(int idUsuario, int idPublicacion);
    void actualizarGuardado(Guardado guardado);
    void eliminarGuardado(int idGuardado);
}

