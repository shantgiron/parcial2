package services;

import modelos.Amigo;
import modelos.Notificacion;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AmigoServices extends GestionDb<Amigo> {

    private static AmigoServices instancia;

    public AmigoServices() {
        super( Amigo.class);
    }

    public static AmigoServices getInstancia(){
        if(instancia==null){
            instancia = new AmigoServices();
        }
        return instancia;
    }

    public boolean solicitarAmigo(Usuario usuario1, Usuario usuario2){
        Amigo amigo = new Amigo();

        amigo.setAmigo(usuario2);
        amigo.setConfirmado(false);

        //creo la amistad
        crear( amigo );

        usuario1.getAmigos().add(amigo);
        UsuarioServices.getInstancia().editar(usuario1);

        //notifico
        Notificacion n = new Notificacion();

        n.setDescripcion(usuario1.getNombre() + " " + usuario1.getApellido() +", quiere agregarte." );
        n.setVinculo("/aceptar?amigo=" + usuario1.getId());
        usuario2.getNotificaciones().add(n);
        UsuarioServices.getInstancia().editar(usuario2);

        return true;
    }


    public boolean aceptarAmigo(Usuario usuario1, Usuario usuario2){
        Amigo amigo = new Amigo();

        amigo.setAmigo(usuario2);
        amigo.setConfirmado(true);

        //creo la amistad
        crear( amigo );

        usuario1.getAmigos().add(amigo);
        UsuarioServices.getInstancia().editar(usuario1);

        usuario2.getAmigos().forEach(ami->{
                 if(ami.getAmigo().getId().equals( usuario1.getId() )){
                ami.setConfirmado(true);
                UsuarioServices.getInstancia().editar(usuario2);
            }
        });

        return true;
    }


    public List<Usuario> getAmigosByUsuarioID(long usuarioID){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a from Usuario u JOIN u.amigos a where u.id =:usuarioID and a.confirmado = true");
        query.setParameter("usuarioID", usuarioID);
        List<Usuario> lista = query.getResultList();
        em.close();
        return lista;
    }




}
