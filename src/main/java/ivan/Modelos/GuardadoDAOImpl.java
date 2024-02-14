package ivan.Modelos;

import ivan.Constructores.Guardado;
import java.util.List;

import ivan.Constructores.MeGusta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

@Component
public class GuardadoDAOImpl implements GuardadoDAO {
    private static final String UP = "JPAIvan";
    private final EntityManagerFactory emf;

    public GuardadoDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory(UP);
    }

    @Override
    public void agregarGuardado(Guardado guardado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(guardado);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Guardado obtenerGuardadoPorId(int idGuardado) {
        EntityManager em = emf.createEntityManager();
        return em.find(Guardado.class, idGuardado);
    }

    @Override
    public List<Guardado> obtenerTodosLosGuardados() {
        EntityManager em = emf.createEntityManager();
        String hql = "from Guardado g";
        Query q = em.createQuery(hql);
        return q.getResultList();
    }

    @Override
    public List<Guardado> obtenerGuardadosPorIdUsuario(int idUsuario) {
        EntityManager em = emf.createEntityManager();
        String hql = "from Guardado g where g.usuario.idUsuario = :idUsuario";
        Query q = em.createQuery(hql);
        q.setParameter("idUsuario", idUsuario);
        return q.getResultList();
    }

    @Override
    public List<Guardado> obtenerGuardadosPorIdPublicacion(int idPublicacion) {
        EntityManager em = emf.createEntityManager();
        String hql = "from Guardado g where g.publicacion.idPublicacion = :idPublicacion";
        Query q = em.createQuery(hql);
        q.setParameter("idPublicacion", idPublicacion);
        return q.getResultList();
    }

    public Guardado obtenerGuardadoPorIdUsuarioYIdPublicacion(int idUsuario, int idPublicacion) {
        EntityManager em = emf.createEntityManager();
        String hql = "from Guardado g where g.usuario.idUsuario = :idUsuario and g.publicacion.idPublicacion = :idPublicacion";
        Query q = em.createQuery(hql);
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("idPublicacion", idPublicacion);

        List<Guardado> resultList = q.getResultList();

        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    @Override
    public void actualizarGuardado(Guardado guardado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(guardado);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarGuardado(int idGuardado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Guardado guardado = em.find(Guardado.class, idGuardado);
            em.remove(guardado);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}


