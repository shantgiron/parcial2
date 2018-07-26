package services;


import modelos.LikePublicacion;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LikePublicacionServices extends GestionDb<LikePublicacion>{

    private static LikePublicacionServices instancia;

    public LikePublicacionServices(){
        super(LikePublicacion.class);
    }

    public static LikePublicacionServices getInstancia() {
        if (instancia == null) {
            instancia = new LikePublicacionServices();
        }
        return instancia;
    }


    //para like val = 1, para dislike val = 2
    public long getLikesByPublicacionID(long publicacionID, int val){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select count(*) from LikePublicacion l where l.publicacionId =:publicacionID and l.val =:val");
        query.setParameter("publicacionID", publicacionID);
        query.setParameter("val", val);

        return (long) query.getSingleResult();
    }

    //para like val = 1
    public int getLikesByPublicacionYUsuarioID(long publicacionID, long usuarioID, int val){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select l from LikePublicacion l where l.publicacionId =:publicacionID and l.val =:val and l.usuarioId =:usuarioID");
        query.setParameter("publicacionID", publicacionID);
        query.setParameter("val", val);
        query.setParameter("usuarioID", usuarioID);

        return  query.getResultList().size();
    }

    //upsert likes
    public void setLikes(long publicacionID, long usuarioID, int val){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select l from LikePublicacion l where l.publicacionId =:publicacionID and l.usuarioId =:usuarioID");
        query.setParameter("publicacionID", publicacionID);
        query.setParameter("usuarioID", usuarioID);

        List<LikePublicacion> lista = query.getResultList();
        if(lista.size() > 0){
            LikePublicacion valoracion = lista.get(0);
            valoracion.setVal(val);
            editar( valoracion  );
        }else{
            LikePublicacion valoracion = new LikePublicacion();
            valoracion.setVal(val);
            valoracion.setPublicacionId(publicacionID);
            valoracion.setUsuarioId(usuarioID);
            crear(valoracion);
        }
        em.close();
        return;
    }
}
