package ivan.Servicios;

import ivan.Constructores.Usuario;
import ivan.Modelos.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicioUsuario {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private Usuario usuario;

    public ServicioUsuario() {
    }

    public ServicioUsuario(UsuarioDAO usuarioDAO, Usuario usuario) {
        this.usuarioDAO = usuarioDAO;
        this.usuario = usuario;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void agregarUsuario(Usuario u) {
        usuarioDAO.agregarUsuario(u);
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        return usuarioDAO.obtenerUsuarioPorId(idUsuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.obtenerTodosLosUsuarios();
    }

    public void actualizarUsuario(Usuario u) {
        usuarioDAO.actualizarUsuario(u);
    }

    public void eliminarUsuario(int idUsuario) {
        usuarioDAO.eliminarUsuario(idUsuario);
    }
}
