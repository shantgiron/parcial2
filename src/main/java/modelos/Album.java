package modelos;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Album {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Publicacion> fotos;

    public Album(Set<Publicacion> fotos) {
        this.fotos = fotos;
    }

    public Album() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Publicacion> getFotos() {
        return fotos;
    }

    public void setFotos(Set<Publicacion> fotos) {
        this.fotos = fotos;
    }
}
