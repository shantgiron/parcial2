package services;

import modelos.Usuario;
import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class UsuarioServices extends GestionDb<Usuario> {

    private static UsuarioServices instancia;

    public UsuarioServices() {
        super(Usuario.class);
    }

    public static UsuarioServices getInstancia(){
        if(instancia==null){
            instancia = new UsuarioServices();
        }
        return instancia;
    }

    public List<Usuario> usuarios() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from Usuario u");
        List<Usuario> lista = query.getResultList();
        em.close();
        return lista;
    }

    public Usuario getUsuario(long id) {
        Usuario usuario =  find(id);
        return usuario;
    }

    public Usuario getUsuarioByUserName(String username) {
        Usuario usuario = null;
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from Usuario u where u.username =:username");
        query.setParameter("username", username.trim());
        List<Usuario> lista = query.getResultList();
        if(lista.size() > 0) usuario = lista.get(0);
        em.close();
        return usuario;
    }

    public boolean crearUsuario(Usuario usuario){
        //para guardar la contraseña cifrada
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(usuario.getPassword());
        usuario.setPassword(encryptedPassword);

        //creo al usuario
        crear( usuario );

        return true;
    }

    public boolean actualizarUsuario(Usuario usuario){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "update Usuario set nombre=?, USERNAME=?, PASSWORD=?, ADMINISTRADOR=?, apellido=?, correo=?, lugar_nacimiento=?, fecha_nacimiento=?  where id = ?";
            con = DB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, usuario.getNombre());
            prepareStatement.setString(2, usuario.getUsername());
            prepareStatement.setString(3, usuario.getPassword());
            prepareStatement.setBoolean(4, usuario.isAdministrador());
            prepareStatement.setString(5, usuario.getApellido());
            prepareStatement.setString(6, usuario.getCorreo());
            prepareStatement.setString(7, usuario.getLugar_nacimiento());
            prepareStatement.setDate(8, (Date) usuario.getFecha_nacimiento());

            //Indica el where...
            prepareStatement.setLong(9, usuario.getId());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    public boolean borrarUsuario(int id){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "delete from Usuario where id = ?";
            con = DB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);

            //Indica el where...
            prepareStatement.setInt(1, id);
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    public Usuario autenticarUsuario(String userName, String pass ){
        Usuario usuario = getUsuarioByUserName(userName);

        if( usuario == null ){ return  null; }

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        if (passwordEncryptor.checkPassword(pass, usuario.getPassword())) {
            return usuario;
        } else {
            return null;
        }
    }

}


