package modelos;

import javax.persistence.*;

@Entity
public class Amigo {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario1;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario2;

    @Column(columnDefinition =  "boolean default false")
    boolean confirmado;

    public Amigo(){}

    public Amigo(Usuario usuario1, Usuario usuario2, boolean confirmado) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.confirmado = confirmado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario) {
        this.usuario1 = usuario;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario) {
        this.usuario2 = usuario;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }
}
