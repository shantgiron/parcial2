package modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Album {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
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
