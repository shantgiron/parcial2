import modelos.*;
import services.*;

import static java.lang.Math.max;
import static spark.Spark.*;

import spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import transformaciones.JsonTransformer;

import java.util.*;
import java.util.Set;


public class ManejoRutas {


    // Declaración para simplificar el uso del motor de template Thymeleaf.
    public static String renderThymeleaf(Map<String, Object> model, String templatePath) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, templatePath));

    }

    public void rutas() {

        JsonTransformer jsonTransformerTransformer = new JsonTransformer();

        get("/home", (request, response) -> {
            PublicacionServices as = new PublicacionServices();
            Usuario usuario = new Usuario();
            Session session = request.session(true);

            int pagina = Integer.parseInt(request.queryParamOrDefault("pagina", "1"));
            int sz = Integer.parseInt(request.queryParamOrDefault("sz", "5"));
            pagina = max(pagina, 1);
            List<Publicacion> listaPublicacion = as.listaPublicacion(pagina, sz);
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("listaPublicacion", listaPublicacion);
            modelo.put("pagina", pagina);
            //  modelo.put("etiquetas", new EtiquetaServices().getEtiquetas());

            if (request.cookie("usuario") != null) {
                UsuarioServices us = new UsuarioServices();
                usuario = us.getUsuario(Integer.parseInt(request.cookie("usuario")));
                session.attribute("usuario", usuario);
            }

            if (session.attribute("usuario") != null) usuario = session.attribute("usuario");

            modelo.put("registeredUser", usuario);

            return renderThymeleaf(modelo, "/home");
        });


        get("/login", (request, response) -> {
            Map<String, Object> modelo = new HashMap<>();
            return renderThymeleaf(modelo, "/login");
        });

        //En caso de que no pongan Home

        get("",  (request, response) -> {
            response.redirect("/home?pagina=1");
            return "";
        });

        get("/",  (request, response) -> {
            response.redirect("/home?pagina=1");
            return "";
        });



    }
}
