package modelos;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class Publicacion {

    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "text")
    //  private String imagen; revisa esto sarah.. no toy segura
    private String descripcion;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Etiqueta> etiquetas;

    public Publicacion(long id, String descripcion, Date fecha, Set<Etiqueta> etiquetas) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.etiquetas = etiquetas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Set<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public void agregarEtiqueta(Etiqueta etiqueta) {
    }

    public void setAutor(Usuario usuario) {
    }
}


