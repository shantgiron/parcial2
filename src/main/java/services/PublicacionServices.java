package services;

import modelos.Publicacion;
import modelos.Etiqueta;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.max;
import static java.lang.Math.min;



public class PublicacionServices extends GestionDb<Publicacion>{


    private static PublicacionServices instancia;

    public static PublicacionServices getInstancia(){
        if(instancia==null){
            instancia = new PublicacionServices();
        }
        return instancia;
    }

    public PublicacionServices(){
        super(Publicacion.class);
    }



    public List<Publicacion> listaPublicacion() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a from Publicacion a order by a.id desc");
        List<Publicacion> lista = query.getResultList();
        em.close();
        return lista;
    }

    public List<Publicacion> listaPublicacion(int pagina, int sz) {
        pagina = max(pagina,1);
        EntityManager em = getEntityManager();
        Query queryList = em.createQuery("select a from Publicacion a order by a.id desc");
        queryList.setFirstResult((pagina-1)*sz);
        queryList.setMaxResults(sz);
        List<Publicacion> lista = queryList.getResultList();
        em.close();
        return lista;
    }

    public Set<Publicacion> listaPublicacionesByTag(long tagID) {
        EntityManager em = getEntityManager();
        Query queryList = em.createQuery("select e from Etiqueta e where e.id =:tagID");
        queryList.setParameter("tagID", tagID);
        Etiqueta et = (Etiqueta) queryList.getSingleResult();
        em.close();
        return et.getPublicaciones();
    }

    public long getCantidadPublicaciones(){
        EntityManager em = getEntityManager();
        Query queryCount= em.createQuery("select count(a.id) from Publicacion a");
        long cant = (long) queryCount.getSingleResult();
        em.close();
        return cant;
    }

   /* public Publicacion getPublicacion(long id) {
        Publicacion publicacion = find(id);
        publicacion.setComentarios( new ComentarioServices().getComentarioByArticuloID(id)); //debes crear la clase de comentario y servicio
        return publicacion;
    }*/

    public boolean crearPublicacion(String descripcion, long usuarioid, String tags){
        Publicacion publicacion = new Publicacion();
        publicacion.setAutor( new UsuarioServices().getUsuario(usuarioid) );
        publicacion.setDescripcion(descripcion);
       // publicacion.setEtiquetas(generarEtiquetas(tags));
        crear(publicacion);
        return true;
    }

    //Actualizar las publicaciones
    public boolean actualizarPublicacion(long id, String descripcion, long usuarioid, String tags){
        Publicacion publicacion = find(id);
        publicacion.setDescripcion(descripcion);
     //   publicacion.setEtiquetas(generarEtiquetas(tags));
        crear(publicacion);
        return true;
    }

    //Metodo borrar las publicaciones
    public boolean borrarPublicacion(long id, long usuarioid){
        eliminar(id);
        return true;
    }

}















