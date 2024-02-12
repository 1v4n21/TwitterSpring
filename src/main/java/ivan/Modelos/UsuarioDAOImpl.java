package ivan.Modelos;

import ivan.Constructores.Usuario;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDAOImpl implements UsuarioDAO {
    private static final String UP = "JPAIvan";
    private final EntityManagerFactory emf;

    public UsuarioDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory(UP);
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario obtenerUsuarioPorId(int idUsuario) {
        EntityManager em = emf.createEntityManager();
        return em.find(Usuario.class, idUsuario);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        EntityManager em = emf.createEntityManager();
        String hql = "from Usuario u";
        Query q = em.createQuery(hql);
        return q.getResultList();
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarUsuario(int idUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, idUsuario);
            em.remove(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario verificarUsuario(String nombreUsuario, String password) {
        EntityManager em = emf.createEntityManager();
        String hql = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.password = :password";
        TypedQuery<Usuario> query = em.createQuery(hql, Usuario.class);
        query.setParameter("nombreUsuario", nombreUsuario);
        query.setParameter ("password", password);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}



