package ivan;

import ivan.Configuracion.ConfiguracionSpring;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Clase para inicializar y configurar la aplicación web Spring MVC
public class InicializarSpring extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Constructor por defecto
    public InicializarSpring() {
    }

    // Configuración global de la aplicación (relacionada con el contexto raíz)
    // En este caso, no hay configuración adicional a nivel de contexto raíz, por lo que se retorna null.
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // Configuración específica de la servlet de Spring MVC
    // Aquí se especifica la clase de configuración de Spring para la servlet.
    // En este caso, se espera que ConfiguracionSpring contenga la configuración de controladores, vistas, etc.
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ConfiguracionSpring.class};
    }

    // Mapeo de las URL manejadas por el DispatcherServlet de Spring MVC
    // En este caso, todas las URL se manejarán a través del DispatcherServlet y se mapearán a la raíz del contexto web ("/").
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

