package modelos;

import javax.persistence.*;

@Entity
public class Amigo {

    @Id
    @GeneratedValue
    private Long id;



    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario amigo;


    @Column(columnDefinition =  "boolean default false")
    boolean confirmado;

    public Amigo(){}

    public Usuario getAmigo() {
        return amigo;
    }

    public void setAmigo(Usuario amigo) {
        this.amigo = amigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }
}
