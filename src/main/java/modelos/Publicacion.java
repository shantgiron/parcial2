package modelos;

import org.hibernate.annotations.CreationTimestamp;
import services.LikePublicacionServices;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class Publicacion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "text")
    //  private String imagen; revisa esto sarah.. no toy segura
    private String descripcion;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    //el id del usuario dueño del muro
    private Long muro_de;

    @Transient
    private boolean leGusta = false;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Usuario> etiquetas;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Comentario> comentarios;

    private String img;

    private Long album_id = null;

    public long getLikeCount() {
        return LikePublicacionServices.getInstancia().getLikesByPublicacionID(id);
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    private  long likeCount;

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    private String naturaleza = "PUBLICACION";

    public Publicacion(Long id, String descripcion, Date fecha, Set<Usuario> etiquetas, Usuario usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.etiquetas = etiquetas;
        this.usuario = usuario;
    }



    public Publicacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<Usuario> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Usuario> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public void agregarEtiqueta(Usuario etiqueta) {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Long getMuro_de() {
        return muro_de;
    }

    public void setMuro_de(Long muro_de) {
        this.muro_de = muro_de;
    }



    public boolean isLeGusta() {
        return leGusta;
    }

    public void setLeGusta(boolean leGusta) {
        this.leGusta = leGusta;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Long album_id) {
        this.album_id = album_id;
    }
}


