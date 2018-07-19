package modelos;

import javax.persistence.*;

@Entity
public class Amigo {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Usuario usuario;

    @Column(columnDefinition =  "boolean default false")
    boolean confirmado;

    public Amigo(){}

    public Amigo(Usuario usuario, boolean confirmado) {
        this.usuario = usuario;
        this.confirmado = confirmado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }
}
