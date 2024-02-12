package ivan.Modelos;

import ivan.Constructores.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void agregarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(int idUsuario);
    List<Usuario> obtenerTodosLosUsuarios();
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(int idUsuario);
    Usuario verificarUsuario(String nombreUsuario, String password);
}

