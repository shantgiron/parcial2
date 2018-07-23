package modelos;

import javax.persistence.*;

@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "text")
    private String comentario;

    @OneToOne(fetch = FetchType.EAGER)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Publicacion publicacion;

    @Transient
    private int likesCount;

    @Transient
    private int dislikesCount;

    public Comentario(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }


    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
}
