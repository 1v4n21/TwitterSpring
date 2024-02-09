package ivan.Configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// Indica que esta clase es una fuente de configuración de Spring.
@Configuration

// Habilita la configuración específica de Spring MVC.
@EnableWebMvc

// Escanea el paquete base "ivan" y sus subpaquetes en busca de componentes de Spring.
@ComponentScan(basePackages = {"ivan"})

// Implementa la interfaz WebMvcConfigurer para personalizar la configuración de Spring MVC.
public class ConfiguracionSpring implements WebMvcConfigurer {

    // Constructor por defecto.
    public ConfiguracionSpring() {
    }

    // Método anotado con @Bean que configura el InternalResourceViewResolver.
    @Bean
    public InternalResourceViewResolver viewResolver() {
        // Crea un objeto InternalResourceViewResolver.
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        // Configura el prefijo para las vistas JSP.
        resolver.setPrefix("/vistas/");

        // Configura el sufijo para las vistas JSP.
        resolver.setSuffix(".jsp");

        // Devuelve el objeto configurado.
        return resolver;
    }

    // Método para configurar manejadores de recursos estáticos.
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Agrega un manejador de recursos para las URL que coinciden con "/misRecursosEstaticos/**".
        // Asocia estas URL con la ubicación "/recursosEstaticos/" en la aplicación.
        registry.addResourceHandler(new String[]{"/misRecursosEstaticos/**"}).addResourceLocations(new String[]{"/RecursosEstaticos/"});
    }

    // Método anotado con @Bean que configura el ResourceBundleMessageSource para manejar mensajes internacionalizados.
    @Bean
    public ResourceBundleMessageSource messageSource() {
        // Crea un objeto ResourceBundleMessageSource.
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();

        // Configura el nombre base ("messages") para buscar archivos de propiedades con mensajes internacionalizados.
        source.setBasename("messages");

        // Devuelve el objeto configurado.
        return source;
    }
}

