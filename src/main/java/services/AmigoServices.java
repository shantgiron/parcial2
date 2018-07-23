package services;

import modelos.Amigo;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AmigoServices extends GestionDb<Amigo> {

    private static UsuarioServices instancia;

    public AmigoServices() {
        super(Amigo.class);
    }

    public static UsuarioServices getInstancia(){
        if(instancia==null){
            instancia = new UsuarioServices();
        }
        return instancia;
    }

    public boolean solicitarAmigo(Usuario usuario1, Usuario usuario2){
        Amigo amigo = new Amigo(usuario1, usuario2, false);
        //creo la amistad
        crear( amigo );
        return true;
    }


    public List<Usuario> getAmigosByUsuarioID(long usuarioID){

        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a.usuario2 from Amigo a where a.usuario1_id =:usuarioID");
        query.setParameter("usuarioID", usuarioID);
        List<Usuario> lista = query.getResultList();
        em.close();

        return lista;
    }




}
