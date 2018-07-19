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
    private long id;

    @OneToMany
    private Set<Publicacion> fotos;

    public Album(Set<Publicacion> fotos) {
        this.fotos = fotos;
    }

    public Album() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Publicacion> getFotos() {
        return fotos;
    }

    public void setFotos(Set<Publicacion> fotos) {
        this.fotos = fotos;
    }
}
