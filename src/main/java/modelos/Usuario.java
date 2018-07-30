package modelos;

import org.hibernate.annotations.CreationTimestamp;
import services.PublicacionServices;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
public class Usuario{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String username;
    private String nombre;
    private String password;
    private String apellido;
    private String correo;
    private String lugar_nacimiento;
    private String ciudad_residencia;
    private String fotoPerfil;
    private String fotoPortada;
    private boolean admin = false;



    @Column(columnDefinition =  "boolean default false")
    private boolean administrador;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_nacimiento;


    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Notificacion> notificaciones;


    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Amigo> amigos;

    public List<Publicacion> getPublicaciones() {
        return PublicacionServices.getInstancia().listaPublicacionByUduarioID(id);
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    @Transient
    private List<Publicacion> publicaciones;



    public Usuario(){};


    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public void setCiudad_residencia(String ciudad_residencia) {
        this.ciudad_residencia = ciudad_residencia;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getLugar_nacimiento() {
        return lugar_nacimiento;
    }

    public void setLugar_nacimiento(String lugar_nacimiento) {
        this.lugar_nacimiento = lugar_nacimiento;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getFotoPortada() {
        return fotoPortada;
    }

    public void setFotoPortada(String fotoPortada) {
        this.fotoPortada = fotoPortada;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Set<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(Set<Notificacion> notificacions) {
        this.notificaciones = notificacions;
    }
    public Set<Amigo> getAmigos() {
        return amigos;
    }

    public void setAmigos(Set<Amigo> amigos) {
        this.amigos = amigos;
    }


}