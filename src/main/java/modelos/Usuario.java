package modelos;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class Usuario{

    @Id
    @GeneratedValue
    private long id;


    private String username;
    private String nombre;
    private String password;
    private String apellido;
    private String correo;
    private String lugar_nacimiento;
   // private String ciudad_residencia;


    @Column(columnDefinition =  "boolean default false")
    private boolean administrador;


    @OneToMany(mappedBy = "usuarios", fetch = FetchType.EAGER)
    private Set<CentroEducativo> centrosEducativos;

    @OneToMany(mappedBy = "usuarios", fetch = FetchType.EAGER)
    private Set<LugaresTrabajos> lugaresTrabajos;

    @OneToMany(mappedBy = "usuarios", fetch = FetchType.EAGER)
    private Set<Ciudad> ciudades;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_nacimiento;


    public Usuario(){};


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


   /* public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public void setCiudad_residencia(String ciudad_residencia) {
        this.ciudad_residencia = ciudad_residencia;
    }*/

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

    public Set<CentroEducativo> getCentrosEducativos() {
        return centrosEducativos;
    }

    public void setCentrosEducativos(Set<CentroEducativo> centrosEducativos) {
        this.centrosEducativos = centrosEducativos;
    }

    public Set<LugaresTrabajos> getLugaresTrabajos() {
        return lugaresTrabajos;
    }

    public void setLugaresTrabajos(Set<LugaresTrabajos> lugaresTrabajos) {
        this.lugaresTrabajos = lugaresTrabajos;
    }

    public Set<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(Set<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
}