package ivan.Repositorio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Date;
import ivan.Constructores.MeGusta;
import ivan.Constructores.Publicacion;
import ivan.Constructores.Guardado;
import ivan.Constructores.Usuario;

public class ProbarORM {
    public static void main(String[] args) {
        // Crear EntityManagerFactory
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("JPAIvan");
        // Crear EntityManager
        EntityManager em = enf.createEntityManager();

        try {
            // Inicio de transacción
            em.getTransaction().begin();

            // Crear instancias de entidades
            Usuario usuario1 = new Usuario("usuario1", "Nombre1", "Apellido1", "Localidad1", "email1@example.com", "password1", "rol1");
            Usuario usuario2 = new Usuario("usuario2", "Nombre2", "Apellido2", "Localidad2", "email2@example.com", "password2", "rol2");

            Publicacion publicacion1 = new Publicacion("Mensaje de prueba 1", usuario1);
            publicacion1.setFecha (new Date ());
            Publicacion publicacion2 = new Publicacion("Mensaje de prueba 2", usuario2);
            publicacion2.setFecha (new Date ());

            MeGusta meGusta1 = new MeGusta(usuario1, publicacion2);
            MeGusta meGusta2 = new MeGusta(usuario2, publicacion1);

            Guardado guardado1 = new Guardado(usuario1, publicacion2);
            Guardado guardado2 = new Guardado(usuario2, publicacion1);

            // Persistir objetos en la base de datos
            em.persist(usuario1);
            em.persist(usuario2);
            em.persist(publicacion1);
            em.persist(publicacion2);
            em.persist(meGusta1);
            em.persist(meGusta2);
            em.persist(guardado1);
            em.persist(guardado2);

            // Commit de la transacción
            em.getTransaction().commit();

            System.out.println("Datos insertados en la base de datos con éxito.");
        } catch (Exception e) {
            // Manejar excepciones
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar EntityManager
            em.close();
            // Cerrar EntityManagerFactory
            enf.close();
        }
    }
}

