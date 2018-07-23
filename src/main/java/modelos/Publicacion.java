package modelos;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class Publicacion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "text")
    //  private String imagen; revisa esto sarah.. no toy segura
    private String descripcion;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Usuario usuario;



    @OneToMany(fetch = FetchType.EAGER)
    private Set<Usuario> etiquetas;

    private String img;

    public Publicacion(Long id, String descripcion, Date fecha, Set<Usuario> etiquetas, Usuario usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.etiquetas = etiquetas;
        this.usuario = usuario;
    }



    public Publicacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<Usuario> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Usuario> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public void agregarEtiqueta(Usuario etiqueta) {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}


