package services;

import modelos.Comentario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComentarioServices extends GestionDb<Comentario>{

    private static ComentarioServices instancia;


    public static ComentarioServices getInstancia(){
        if(instancia==null){
            instancia = new ComentarioServices();
        }
        return instancia;
    }

    public ComentarioServices(){
        super(Comentario.class);
    }

    public boolean crearComentario(String comentario, long usuarioId, long publicacionId){
        Comentario coment = new Comentario();

        coment.setComentario(comentario);

        coment.setPublicacion(PublicacionServices.getInstancia().find(publicacionId));
        coment.setAutor(UsuarioServices.getInstancia().find(usuarioId));

        crear( coment );

        return true;

    }
    public Set<Comentario> getComentarioByPublicacionID(long publicacionId){
        EntityManager em = getEntityManager();
        Query queryList = em.createQuery("select c from Comentario c where c.publicacion.id =:publicacionId order by c.id desc");
        queryList.setParameter("publicacionId", publicacionId );
        List<Comentario> lista = queryList.getResultList();
        em.close();
        Set<Comentario> ans = new HashSet<>(lista);
        return ans;
    }
}
