package rutas;


import modelos.Usuario;
import services.UsuarioServices;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ManejoRutasShant {
    public ManejoRutasShant() {
        //Usuario admin por defecto
        if (new UsuarioServices().getUsuarioByEmail("shantgiron@gmail.com") == null) {
            Usuario usuario = new Usuario();
            usuario.setPassword("admin");
            usuario.setNombre("Shantall");
            usuario.setApellido("Giron");
            usuario.setCorreo("shantgiron@gmail.com");
            usuario.setLugar_nacimiento("Santiago");
            usuario.setCiudad_residencia("Santiago");

            new UsuarioServices().crearUsuario(usuario);
        }

        get("/inicio", (request, response) -> {
            Map<String, Object> modelo = new HashMap<>();

            String mensaje = "";

            if (request.queryMap().hasKeys() ) {
                if (request.queryMap("register").booleanValue())
                    mensaje = "Usuario registrado satisfactoriamente.";
            }

            modelo.put("mensaje", mensaje);

            return renderThymeleaf(modelo,"/inicio");
        });

        post("/inicio", (request, response) -> {
            boolean iniciarsesion = Boolean.parseBoolean(request.queryParams("iniciarsesion"));

            if (iniciarsesion) {
                String correo = request.queryParams("correo");
                String contrasena = request.queryParams("contrasena");

                Usuario user = new UsuarioServices().autenticarUsuario(correo, contrasena);

                if (user == null) {
                    request.session().invalidate();
                    response.redirect("/inicio");
                } else {
                    request.session(true);
                    request.session().attribute("usuario", user);
                    response.redirect("/index");
                }
            } else {
                Usuario usuario = new Usuario();

                String nombre = request.queryParams("nombre");
                String apellido = request.queryParams("apellido");
                String correo = request.queryParams("correo");
                String contrasena = request.queryParams("contrasena");
                String cumpleanos = request.queryParams("cumpleanos");

                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setCorreo(correo);
                usuario.setPassword(contrasena);
                usuario.setFecha_nacimiento(new SimpleDateFormat("mm/dd/yyyy").parse(cumpleanos));

                new UsuarioServices().crearUsuario(usuario);

                response.redirect("/inicio?register=true");
            }

            return null;
        });

        get("/index", (request, response) -> {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("usuario", request.session().attribute("usuario"));

            return renderThymeleaf(modelo,"/index");
        });

        get("/perfil", (request, response) -> {
            Map<String, Object> modelo = new HashMap<>();

            return renderThymeleaf(modelo,"/perfilUsuario");
        });

        get("/cerrarsesion", (request, response) -> {
            request.session().invalidate();
            response.redirect("/inicio");
            return null;
        });
    }

    // Declaración para simplificar el uso del motor de template Thymeleaf.
    public static String renderThymeleaf(Map<String, Object> model, String templatePath) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}