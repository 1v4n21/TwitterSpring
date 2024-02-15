package ivan.Modelos;

import ivan.Constructores.Publicacion;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

@Component
public class PublicacionDAOImpl implements PublicacionDAO {
    private static final String UP = "JPAIvan";
    private final EntityManagerFactory emf;

    public PublicacionDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory(UP);
    }

    @Override
    public void agregarPublicacion(Publicacion publicacion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(publicacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Publicacion obtenerPublicacionPorId(int idPublicacion) {
        EntityManager em = emf.createEntityManager();
        return em.find(Publicacion.class, idPublicacion);
    }

    @Override
    public List<Publicacion> obtenerTodasLasPublicaciones() {
        EntityManager em = emf.createEntityManager();
        String hql = "FROM Publicacion p ORDER BY p.fecha DESC"; // DESC para ordenar de más reciente a más antiguo
        Query q = em.createQuery(hql);
        return q.getResultList();
    }

    @Override
    public List<Publicacion> obtenerPublicacionesPorIdUsuario(int idUsuario) {
        EntityManager em = emf.createEntityManager();
        String hql = "from Publicacion p where p.usuario.idUsuario = :idUsuario";
        Query q = em.createQuery(hql);
        q.setParameter("idUsuario", idUsuario);
        return q.getResultList();
    }

    @Override
    public void actualizarPublicacion(Publicacion publicacion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(publicacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarPublicacion(int idPublicacion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Publicacion publicacion = em.find(Publicacion.class, idPublicacion);
            em.remove(publicacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Publicacion> buscarPublicacionesPorNombreUsuario(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        String hql = "SELECT p FROM Publicacion p WHERE p.usuario.nombreUsuario LIKE :nombreUsuarioPrefix";
        Query q = em.createQuery(hql);
        q.setParameter("nombreUsuarioPrefix", nombreUsuario + "%");
        return q.getResultList();
    }
}


