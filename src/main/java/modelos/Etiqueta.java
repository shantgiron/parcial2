package modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Etiqueta implements Serializable{

    @Id
    @GeneratedValue
    private long id;

    private String etiqueta;

    @ManyToMany(mappedBy = "etiquetas", fetch = FetchType.EAGER)
    private Set<Publicacion> publicaciones;

    public Etiqueta(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Set<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicacion(Set<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }


}