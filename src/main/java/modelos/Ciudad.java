package modelos;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Ciudad {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @ManyToMany
    private Set<Usuario> ciudadanos;

    public Set<Usuario> getCiudadanos() {
        return ciudadanos;
    }

    public void setCiudadanos(Set<Usuario> ciudadanos) {
        this.ciudadanos = ciudadanos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
