
import modelos.Usuario;
import services.UsuarioServices;

import static spark.Spark.before;
import static spark.Spark.halt;

public class Filtros { //para aplicar filtros
    public void aplicarFiltros(){

        before("/comentar",(request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if(usuario == null){
                //parada del request, enviando un codigo.
                response.redirect("/login");
                halt(200, "No tiene permisos para acceder..");
            }
        });

        before("/inicio",(request, response) -> {
            Usuario usuario = UsuarioServices.getLogUser(request);
            if(usuario == null){
                //parada del request, enviando un codigo.
                response.redirect("/login");
                halt(200, "No tiene permisos para acceder..");
            }
        });



    }



}
