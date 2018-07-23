package modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class LugarTrabajo {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @ManyToMany
    private Set<Usuario> trabajadores;

    public Set<Usuario> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(Set<Usuario> trabajadores) {
        this.trabajadores = trabajadores;
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
