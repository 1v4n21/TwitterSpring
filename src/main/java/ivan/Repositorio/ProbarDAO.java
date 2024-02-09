package ivan.Repositorio;

import ivan.Constructores.Usuario;
import ivan.Constructores.Publicacion;
import ivan.Constructores.MeGusta;
import ivan.Constructores.Guardado;
import ivan.Modelos.*;
import java.util.Scanner;

import java.util.Date;

public class ProbarDAO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Qué tipo de entidad deseas insertar?");
        System.out.println("1. Usuarios");
        System.out.println("2. Publicaciones");
        System.out.println("3. MeGustas");
        System.out.println("4. Guardados");

        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                insertarUsuarios();
                break;
            case 2:
                insertarPublicaciones();
                break;
            case 3:
                insertarMeGustas();
                break;
            case 4:
                insertarGuardados();
                break;
            default:
                System.out.println("Opción no válida");
        }

        scanner.close();
    }

    private static void insertarUsuarios() {
        // Agregar usuarios de prueba
        Usuario usuario1 = new Usuario("usuario1", "Nombre1", "Apellido1", "Localidad1", "email1@example.com", "password1", "rol1");
        Usuario usuario2 = new Usuario("usuario2", "Nombre2", "Apellido2", "Localidad2", "email2@example.com", "password2", "rol2");

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        usuarioDAO.agregarUsuario(usuario1);
        usuarioDAO.agregarUsuario(usuario2);

        usuarioDAO.obtenerTodosLosUsuarios().forEach(u->{
            System.out.println(u.getEmail()+"--"+u.getNombre());
        });
    }

    private static void insertarPublicaciones() {
        // Agregar publicaciones de prueba
        Usuario usuario3 = new Usuario();
        Usuario usuario4 = new Usuario();

        Publicacion publicacion1 = new Publicacion("Mensaje de prueba 1", new Date(), usuario3);
        Publicacion publicacion2 = new Publicacion("Mensaje de prueba 2", new Date(), usuario4);

        PublicacionDAO publicacionDAO = new PublicacionDAOImpl();

        publicacionDAO.agregarPublicacion(publicacion1);
        publicacionDAO.agregarPublicacion(publicacion2);

        publicacionDAO.obtenerTodasLasPublicaciones().forEach(p->{
            System.out.println(p.getMensaje()+"--"+p.getFecha());
        });
    }

    private static void insertarMeGustas() {
        // Agregar MeGustas de prueba
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Publicacion publicacion1 = new Publicacion();
        Publicacion publicacion2 = new Publicacion();

        MeGusta meGusta1 = new MeGusta(usuario1, publicacion2);
        MeGusta meGusta2 = new MeGusta(usuario2, publicacion1);

        MeGustaDAO meGustaDAO = new MeGustaDAOImpl();

        meGustaDAO.agregarMeGusta(meGusta1);
        meGustaDAO.agregarMeGusta(meGusta2);

        meGustaDAO.obtenerTodosLosMeGustas().forEach(m->{
            System.out.println(m.getIdMG()+"--"+m.getPublicacion().getIdPublicacion());
        });
    }

    private static void insertarGuardados() {
        // Agregar Guardados de prueba
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Publicacion publicacion1 = new Publicacion();
        Publicacion publicacion2 = new Publicacion();

        Guardado guardado1 = new Guardado(usuario1, publicacion2);
        Guardado guardado2 = new Guardado(usuario2, publicacion1);

        GuardadoDAO guardadoDAO = new GuardadoDAOImpl();

        guardadoDAO.agregarGuardado(guardado1);
        guardadoDAO.agregarGuardado(guardado2);

        guardadoDAO.obtenerTodosLosGuardados().forEach(g -> {
            System.out.println(g.getIdGuardado() + "--" + g.getPublicacion().getIdPublicacion());
        });
    }
}

