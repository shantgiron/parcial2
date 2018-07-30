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
    private String website;
    private String telefono;
    private String pais;
    private String provincia;
    private String ciudad;
    private String descripcion_personal;
    private String genero;
    private String religion;
    private String lugar_de_nacimiento;
    private String ocupacion;
    private String inclinacion_politica;
    private String facebook;
    private String twitter;
    private String spotify;




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


    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion_personal() {
        return descripcion_personal;
    }

    public void setDescripcion_personal(String descripcion_personal) {
        this.descripcion_personal = descripcion_personal;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getLugar_de_nacimiento() {
        return lugar_de_nacimiento;
    }

    public void setLugar_de_nacimiento(String lugar_de_nacimiento) {
        this.lugar_de_nacimiento = lugar_de_nacimiento;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getInclinacion_politica() {
        return inclinacion_politica;
    }

    public void setInclinacion_politica(String inclinacion_politica) {
        this.inclinacion_politica = inclinacion_politica;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }
}
