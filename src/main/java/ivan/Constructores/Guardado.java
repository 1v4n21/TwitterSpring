package ivan.Constructores;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "GUARDADOS")
@Scope("prototype")  //Este componente con alcance prototype
//significa que cada vez que se solicita el bean,
// se crea una nueva instancia del mismo
public class Guardado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guardado_seq")
    @SequenceGenerator(name = "guardado_seq", sequenceName = "GUARDADO_SEQ", allocationSize = 1)
    @Column(name = "idGuardado")
    private int idGuardado;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPublicacion", referencedColumnName = "idPublicacion")
    private Publicacion publicacion;

    public Guardado() {
    }

    public Guardado(Usuario usuario, Publicacion publicacion) {
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

    public int getIdGuardado() {
        return idGuardado;
    }

    public void setIdGuardado(int idGuardado) {
        this.idGuardado = idGuardado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
}
