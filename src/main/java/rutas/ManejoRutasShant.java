package rutas;


import modelos.Publicacion;
import modelos.Usuario;
import services.PublicacionServices;
import services.UsuarioServices;
import spark.ModelAndView;
import spark.Session;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ManejoRutasShant {
    public ManejoRutasShant() {
        File uploadDir = new File("fotos");
        uploadDir.mkdir();


        //Usuario admin por defecto
        if (new UsuarioServices().getUsuarioByEmail("admin@admin.com") == null) {
            Usuario usuario = new Usuario();
            usuario.setPassword("admin");
            usuario.setNombre("Shantall");
            usuario.setApellido("Giron");
            usuario.setCorreo("admin@admin.com");
            usuario.setLugar_nacimiento("Santiago");
            usuario.setCiudad_residencia("Santiago");
            usuario.setFotoPerfil("/img/badge4.png");
            usuario.setFotoPortada("img/top-header1.jpg");
            usuario.setAdmin(true);

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
                    try {
                        if("on".equalsIgnoreCase(request.queryParams("recordar"))){
                            response.cookie("/", "usuario", Long.toString(user.getId()), 7*24*60*60*1000, false);
                            response.cookie("/", "pass", user.getPassword(), 7*24*60*60*1000, false);
                        }else{
                            response.cookie("/", "usuario", Long.toString(user.getId()), 0, false);
                            response.cookie("/", "pass", Long.toString(user.getId()), 0, false);
                        }
                    }catch (Exception e){  }

                    Session session = request.session(true);
                    session.attribute("usuario", user);
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

                usuario.setFotoPerfil("/img/badge3.png");
                usuario.setFotoPortada("/img/top-header1.jpg");
                usuario.setAdmin(true);

                new UsuarioServices().crearUsuario(usuario);

                response.redirect("/inicio?register=true");
            }

            return null;
        });

        get("/index", (request, response) -> {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("publicaciones", new PublicacionServices().listaPublicacion());
            Usuario u = UsuarioServices.getLogUser(request);
            modelo.put("usuario", u);
            return renderThymeleaf(modelo,"/index");
        });

        post("/index", (request, response) -> {
            String descripcion = request.queryParams("descripcion");

            Publicacion publicacion = new Publicacion();
            //publicacion.setAutor(request.attribute("usuario"));
            publicacion.setDescripcion(descripcion);
          //  System.out.println(UsuarioServices.getLogUser(request).getId());
            publicacion.setUsuario(UsuarioServices.getLogUser(request));
            publicacion.setFecha(new Date());
            publicacion.setImg("");

            PublicacionServices.getInstancia().crear(publicacion);

            response.redirect("/index");

            return "";
        });

        get("/perfil", (request, response) -> {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("usuario", UsuarioServices.getLogUser(request));

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