package ivan.Constructores;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Entity
@Table(name = "PUBLICACIONES")
@Scope("singleton")  //Este componente con alcance singleton
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publicacion_seq")
    @SequenceGenerator(name = "publicacion_seq", sequenceName = "PUBLICACION_SEQ", allocationSize = 1)
    @Column(name = "idPublicacion")
    private int idPublicacion;

    @NotBlank(message = "El campo mensaje no puede estar vacío")
    @Size(max = 200, message = "El campo mensaje no puede tener mas de 200 caracteres")
    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    private List<MeGusta> meGustas = new ArrayList<>();

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    private List<Guardado> guardados = new ArrayList<>();

    public Publicacion() {
    }

    public Publicacion(String mensaje, Usuario usuario) {
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<MeGusta> getMeGustas() {
        return meGustas;
    }

    public void setMeGustas(List<MeGusta> meGustas) {
        this.meGustas = meGustas;
    }

    public List<Guardado> getGuardados() {
        return guardados;
    }

    public void setGuardados(List<Guardado> guardados) {
        this.guardados = guardados;
    }

    public boolean usuarioHaDadoMeGusta(int idUsuario) {
        // Comprueba si hay un "Me gusta" del usuario
        return meGustas.stream().anyMatch(m -> m.getUsuario().getIdUsuario() == idUsuario);
    }

    public boolean usuarioHaGuardado(int idUsuario) {
        // Comprueba si hay un "Guardado" del usuario
        return guardados.stream().anyMatch(g -> g.getUsuario().getIdUsuario() == idUsuario);
    }

    public String obtenerTiempoTranscurrido() {
        if (this.fecha != null) {
            long tiempoPublicacion = this.fecha.getTime(); // Obtener el tiempo en milisegundos de la fecha de publicación
            long tiempoActual = new Date().getTime(); // Obtener el tiempo actual en milisegundos

            long diferencia = tiempoActual - tiempoPublicacion;
            long segundos = diferencia / 1000;
            long minutos = segundos / 60;
            long horas = minutos / 60;
            long dias = horas / 24;

            if (dias > 0) {
                return dias + (dias == 1 ? " día" : " días") + " atrás";
            } else if (horas > 0) {
                return horas + (horas == 1 ? " hora" : " horas") + " atrás";
            } else if (minutos > 0) {
                return minutos + (minutos == 1 ? " minuto" : " minutos") + " atrás";
            } else {
                return segundos + (segundos == 1 ? " segundo" : " segundos") + " atrás";
            }
        } else {
            return "Fecha no disponible";
        }
    }
}
