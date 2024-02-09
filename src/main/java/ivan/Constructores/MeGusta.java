package ivan.Constructores;


import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "MEGUSTAS")
@Scope("prototype")  //Este componente con alcance prototype
//significa que cada vez que se solicita el bean,
// se crea una nueva instancia del mismo
public class MeGusta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "megusta_seq")
    @SequenceGenerator(name = "megusta_seq", sequenceName = "MEGUSTA_SEQ", allocationSize = 1)
    @Column(name = "idMG")
    private int idMG;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPublicacion", referencedColumnName = "idPublicacion")
    private Publicacion publicacion;

    public MeGusta() {
    }

    public MeGusta(Usuario usuario, Publicacion publicacion) {
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

    public int getIdMG() {
        return idMG;
    }

    public void setIdMG(int idMG) {
        this.idMG = idMG;
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
