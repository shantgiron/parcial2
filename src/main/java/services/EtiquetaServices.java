package services;

import modelos.Etiqueta;
import modelos.Publicacion;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaServices extends GestionDb<Etiqueta> {

    public EtiquetaServices() {
        super(Etiqueta.class);
    }

    public boolean crearEtiqueta(String etiqueta) {
        Etiqueta et = new Etiqueta();
        et.setEtiqueta(etiqueta);
        crear(et);
        return true;
    }

    public List<Etiqueta> getEtiquetaByPublicacionID(long publicacionID) {
        Publicacion publicacion = new PublicacionServices().find(publicacionID);
        return new ArrayList<>(publicacion.getEtiquetas());
    }

    public boolean agregarEtiquetaPublicacion(long etiquetaID, long publicacionID) {
        Publicacion publicacion = new PublicacionServices().find(publicacionID);
        Etiqueta etiqueta = find(etiquetaID);
        publicacion.agregarEtiqueta(etiqueta);
        new PublicacionServices().editar(publicacion);

        return true;
    }

    public Etiqueta getEtiquetaByID(long id) {
        return find(id);
    }


        //si no existe la etiqueta, la crea y la busca,
        //podria tener problemas de concurrencia pera para este caso es OK

        public Etiqueta getEtiquetaByName (String name){
            Etiqueta etiqueta = null;

            EntityManager em = getEntityManager();
            Query query = em.createQuery("select e from Etiqueta e where e.etiqueta =:name");
            query.setParameter("name", name.trim().toUpperCase());
            List<Etiqueta> lista = query.getResultList();
            em.close();

            if (lista.size() > 0) {
                etiqueta = lista.get(0);
            } else {
                etiqueta = new Etiqueta();
                etiqueta.setEtiqueta(name.toUpperCase());
                crear(etiqueta);
                return getEtiquetaByName(name);
            }
            return etiqueta;
        }

        public List<Etiqueta> getEtiquetas () {
            Etiqueta etiqueta = null;
            EntityManager em = getEntityManager();
            Query query = em.createQuery("select e from Etiqueta e");
            List<Etiqueta> lista = query.getResultList();
            em.close();
            return lista;
        }

}


