package rutas;

import modelos.Usuario;
import services.UsuarioServices;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.*;

import static java.lang.Math.max;
import static spark.Spark.get;
import static spark.Spark.post;


public class ManejoRutasGenerales {

    // Declaración para simplificar el uso del motor de template Thymeleaf.
    public static String renderThymeleaf(Map<String, Object> model, String templatePath) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, templatePath));
    }


    public void rutas(){

    //ruta para el conteo de los likes
       get("/", (request, response) -> {
            return "Hola mundo";
        });
    }

    private static Object procesarParametros(Request request, Response response){
      //  System.out.println("Recibiendo mensaje por el metodo: "+request.requestMethod());
        Set<String> parametros = request.queryParams();
        String salida="";

        for(String param : parametros){
            salida += String.format("Parametro[%s] = %s <br/>", param, request.queryParams(param));
        }

        return salida;
    }



}
