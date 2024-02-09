package ivan.Modelos;

import ivan.Constructores.MeGusta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

public class MeGustaDAOImpl implements MeGustaDAO {
    private static final String UP = "JPAIvan";
    private final EntityManagerFactory emf;

    public MeGustaDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory(UP);
    }

    @Override
    public void agregarMeGusta(MeGusta meGusta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(meGusta);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public MeGusta obtenerMeGustaPorId(int idMG) {
        EntityManager em = emf.createEntityManager();
        return em.find(MeGusta.class, idMG);
    }

    @Override
    public List<MeGusta> obtenerTodosLosMeGustas() {
        EntityManager em = emf.createEntityManager();
        String hql = "from MeGusta mg";
        Query q = em.createQuery(hql);
        return q.getResultList();
    }

    @Override
    public List<MeGusta> obtenerMeGustasPorIdUsuario(int idUsuario) {
        EntityManager em = emf.createEntityManager();
        String hql = "from MeGusta mg where mg.usuario.idUsuario = :idUsuario";
        Query q = em.createQuery(hql);
        q.setParameter("idUsuario", idUsuario);
        return q.getResultList();
    }

    @Override
    public List<MeGusta> obtenerMeGustasPorIdPublicacion(int idPublicacion) {
        EntityManager em = emf.createEntityManager();
        String hql = "from MeGusta mg where mg.publicacion.idPublicacion = :idPublicacion";
        Query q = em.createQuery(hql);
        q.setParameter("idPublicacion", idPublicacion);
        return q.getResultList();
    }

    @Override
    public MeGusta obtenerMeGustaPorIdUsuarioYIdPublicacion(int idUsuario, int idPublicacion) {
        EntityManager em = emf.createEntityManager();
        String hql = "from MeGusta mg where mg.usuario.idUsuario = :idUsuario and mg.publicacion.idPublicacion = :idPublicacion";
        Query q = em.createQuery(hql);
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("idPublicacion", idPublicacion);
        return (MeGusta) q.getSingleResult();
    }

    @Override
    public void actualizarMeGusta(MeGusta meGusta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(meGusta);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarMeGusta(int idMG) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            MeGusta meGusta = em.find(MeGusta.class, idMG);
            em.remove(meGusta);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}


